package sokoban.juego.funcionalidadDelJuego.mapeo;

import sokoban.juego.funcionalidadDelJuego.mapeo.bloques.*;

import javax.swing.*;
import java.io.*;

public class Mapa implements Serializable {
    private final File rutaDelNivel;
    private int[][] diseñoInicial;
    private final Bloque[][] diseñoEnBloques;
    private final int númeroDePasosParaPuntuaciónPerfecta;

    public Mapa(File rutaDelNivel) {
        this.rutaDelNivel = rutaDelNivel;
        númeroDePasosParaPuntuaciónPerfecta = númeroDePasosParaPuntuaciónPerfecta();
        tomarMapaNuméricoDelTexto();
        diseñoEnBloques = new Bloque[diseñoInicial.length][diseñoInicial[0].length];
        transformarMapaNuméricoABloques();
    }

    public void verificarBloque(int f, int c) throws BloqueInexistenteException {
        if (diseñoInicial[f][c] >= CódigoDeBloque.values().length) {
            throw new BloqueInexistenteException("El nivel " + rutaDelNivel + " tiene un diseño imposible de concretar!!!");
        }
    }

    public void transformarMapaNuméricoABloques() {
        try {
            for (int f = 0; f < diseñoInicial.length; f++) {
                for (int c = 0; c < diseñoInicial[f].length; c++) {
                    verificarBloque(f, c);
                    if (diseñoInicial[f][c] == CódigoDeBloque.SUELO.ordinal()) {
                        diseñoEnBloques[f][c] = new Suelo();
                    }
                    if (diseñoInicial[f][c] == CódigoDeBloque.PARED.ordinal()) {
                        diseñoEnBloques[f][c] = new Pared();
                    }
                    if (diseñoInicial[f][c] == CódigoDeBloque.CAJA.ordinal()) {
                        diseñoEnBloques[f][c] = new Caja();
                    }
                    if (diseñoInicial[f][c] == CódigoDeBloque.BOTÓN.ordinal()) {
                        diseñoEnBloques[f][c] = new Botón();
                    }
                    if (diseñoInicial[f][c] == CódigoDeBloque.VACÍO.ordinal()) {
                        diseñoEnBloques[f][c] = new Vacío();
                    }
                }
            }
        } catch (BloqueInexistenteException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        }
    }

    public Bloque[][] getDiseñoEnBloques() {
        return diseñoEnBloques;
    }

    public int contarBotones() {
        int contador = 0;
        for (int f = 0; f < diseñoInicial.length; f++) {
            for (int c = 0; c < diseñoInicial[f].length; c++) {
                if (diseñoEnBloques[f][c] instanceof Botón) contador++;
            }
        }
        return contador;
    }

    public void tomarMapaNuméricoDelTexto() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaDelNivel));
            String cadena;
            int filaAnchoYLargoDelMapaEnMapaNumérico = 1;
            int filas = 0;
            int columnas = 0;
            for (int i = 0; i < 2; i++) {
                cadena = bufferedReader.readLine();
                if (i == filaAnchoYLargoDelMapaEnMapaNumérico) {
                    filas = Integer.parseInt(cadena.substring(0, 2).replaceAll("\\s+", ""));
                    columnas = Integer.parseInt(cadena.substring(2).replaceAll("\\s+", ""));
                    diseñoInicial = new int[filas][columnas];
                }
            }
            cadena = bufferedReader.readLine();
            for (int f = 0; f < filas; f++) {
                cadena = bufferedReader.readLine().replaceAll("\\s+", "");
                if (cadena.length() == columnas) {
                    for (int c = 0; c < columnas; c++) {
                        diseñoInicial[f][c] = Integer.parseInt(cadena.substring(c, c + 1));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error de carga del mapa");
        }
    }

    private int númeroDePasosParaPuntuaciónPerfecta() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaDelNivel));
            String cadena;
            int filaNúmeroDePasosEnElMapaNumérico = 2;
            for (int i = 0; true; i++) {
                cadena = bufferedReader.readLine();
                if (i == filaNúmeroDePasosEnElMapaNumérico) {
                    return Integer.parseInt(cadena.replaceAll("\\s+", ""));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNúmeroDePasosParaPuntuaciónPerfecta() {
        return númeroDePasosParaPuntuaciónPerfecta;
    }
}
