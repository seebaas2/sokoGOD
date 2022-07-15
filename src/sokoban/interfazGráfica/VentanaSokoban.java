package sokoban.interfazGráfica;

import sokoban.soundtrack.SoundTrack;
import sokoban.interfazGráficaDelJuego.CuadroGraficoDeJuego;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static sokoban.interfazGráfica.MenúPrincipal.sokoban;

public class VentanaSokoban extends JFrame {
    private JButton btnVolver;
    private JButton btnReiniciar;
    private JButton btnGuardar;
    private JPanel pnlContent;
    private JPanel pnlSokoban;
    private SoundTrack canción;
    private String canciónActual;
    private CuadroGraficoDeJuego cuadroGraficoDeJuego;

    public VentanaSokoban() {
        setTitle("Sokoban");
        cuadroGraficoDeJuego = escogerCuadroGraficoDeJuego();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e1) {

            }

            @Override
            public void keyPressed(KeyEvent e1) {
                sokoban.iniciarNivel(e1);
            }

            @Override
            public void keyReleased(KeyEvent e1) {

            }
        });
        setFocusable(true);

        btnVolver.addActionListener(e -> {
            MenuNiveles menuNiveles = new MenuNiveles();
            menuNiveles.crearFrame();
            canción.pararCanción(canción.getCanción());
            dispose();
        });

        btnReiniciar.addActionListener(e -> {
            sokoban.reiniciarNivel();
            canción.pararCanción(canción.getCanción());
            canción.reiniciarCanción(canción.getCanción());
            iniciar(canciónActual);
        });

        btnGuardar.addActionListener(e -> {
            sokoban.guardarPartida();
            canción.pararCanción(canción.getCanción());
            iniciar(canciónActual);
        });
    }

    public void iniciar(String nombreCanción) {
        this.setContentPane(pnlSokoban);
        pnlContent.removeAll();
        pnlContent.add(escogerCuadroGraficoDeJuego());
        pnlContent.revalidate();
        pnlContent.repaint();
        this.setSize(1000, 700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        canción = new SoundTrack(nombreCanción);
        canción.reproducir(canción.getCanción());
        canciónActual = nombreCanción;
    }

    public CuadroGraficoDeJuego escogerCuadroGraficoDeJuego() {
        cuadroGraficoDeJuego = new CuadroGraficoDeJuego(sokoban.enviarNivelParaElPintado());
        return cuadroGraficoDeJuego;
    }
}

