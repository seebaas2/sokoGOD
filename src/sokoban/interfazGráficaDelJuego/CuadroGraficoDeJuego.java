package sokoban.interfazGráficaDelJuego;

import sokoban.juego.funcionalidadDelJuego.Nivel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class CuadroGraficoDeJuego extends JPanel {
    private final Nivel nivel;
    private final URL direcciónDelFondo;
    private final int encuadreEnY;
    private final int encuadreEnX;
    private JPanel pnlCuadroGraficoDeJuego;

    public CuadroGraficoDeJuego(Nivel nivel) {
        this.nivel = nivel;
        direcciónDelFondo = getClass().getResource("/Imágenes/Fondos/fondo.gif");
        encuadreEnY = (10 - nivel.getMapa().getDiseñoEnBloques().length) / 2;
        encuadreEnX = (15 - nivel.getMapa().getDiseñoEnBloques()[0].length) / 2;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (nivel.isElNivelEstáCompleto()) {
            pintarFondo(g2d);
            pintarPalabras(g2d);
        } else {
            pintarNivel(g2d);
        }
        repaint(100);
    }

    public void pintarNivel(Graphics2D g) {
        pintarFondo(g);
        pintarMapa(g);
        pintarBob(g);
        pintarPuntaje(g);
    }

    public void pintarFondo(Graphics2D g) {
        ImageIcon fondo = new ImageIcon(direcciónDelFondo);
        g.drawImage(fondo.getImage(), 0, 0, 1000, 700, null);
    }

    public void pintarMapa(Graphics2D g) {
        for (int f = 0; f < nivel.getMapa().getDiseñoEnBloques().length; f++) {
            for (int c = 0; c < nivel.getMapa().getDiseñoEnBloques()[0].length; c++) {
                pintarBloque(g, f, c);
            }
        }
    }

    public void pintarBob(Graphics2D g) {
        ImageIcon skin = new ImageIcon(nivel.getBob().getDirecciónSkin());
        g.drawImage(skin.getImage(), (nivel.getBob().posiciónEnX() + encuadreEnX) * 64, (nivel.getBob().posiciónEnY() + encuadreEnY) * 64, 64, 64, null);
    }

    public void pintarPuntaje(Graphics2D g) {
        Font puntajes = crearFuente("/Fuentes/dd.ttf", 22);
        g.setFont(puntajes);
        g.setColor(Color.white);
        g.drawString("Número de pasos: " + nivel.getBob().getNumeroDePasos(), 145, 600);
        g.drawString("Número de empujones: " + nivel.getBob().getNumeroDeEmpujones(), 485, 600);
    }

    public void pintarBloque(Graphics2D g, int fila, int columna) {
        ImageIcon skin = new ImageIcon(nivel.getMapa().getDiseñoEnBloques()[fila][columna].getDirecciónSkin());
        g.drawImage(skin.getImage(), (columna + encuadreEnX) * 64, (fila + encuadreEnY) * 64, 64, 64, null);
    }

    public void pintarPalabras(Graphics2D g) {
        Font fontTamaño1 = crearFuente("/Fuentes/dd.ttf", 90);
        Font fontTamaño2 = crearFuente("/Fuentes/dd.ttf", 45);
        Font fontTamaño3 = crearFuente("/Fuentes/dd.ttf", 30);
        g.setFont(fontTamaño1);
        g.setColor(Color.white);
        g.drawString("Congratulations", 40, 300);
        g.setFont(fontTamaño2);
        g.drawString("Tu puntuación fue de " + nivel.obtenerPuntuación(), 105, 400);
        g.setFont(fontTamaño3);
        g.drawString("Presiona el boton BACK y selecciona un nivel", 65, 500);
    }

    public Font crearFuente(String nombreDeLaFuente, int tamaño) {
        InputStream is = getClass().getResourceAsStream(nombreDeLaFuente);
        Font font = new Font("Arial", Font.BOLD, 20);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(is)).deriveFont(Font.PLAIN, tamaño);
        } catch (FontFormatException e) {
            System.out.println("ErrorDeCargaDeLaFuente");
        } catch (IOException e) {
            System.out.println("ErrorDeI/ODeFuente");
        }
        return font;
    }
}
