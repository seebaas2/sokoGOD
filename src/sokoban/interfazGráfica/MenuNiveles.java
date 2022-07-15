package sokoban.interfazGráfica;

import javax.swing.*;

import sokoban.background.Fondo;
import sokoban.juego.ConstantesNiveles;

import static sokoban.interfazGráfica.MenúPrincipal.sokoban;

public class MenuNiveles extends JFrame {
    private JButton btnNivel1;
    private JButton btnNivel2;
    private JButton btnNivel3;
    private JButton btnNivel4;
    private JButton btnVolver;
    private JButton btnCargar;
    private JPanel pnlMenuNiveles;
    private VentanaSokoban ventanaSokoban;

    public MenuNiveles() {
        setTitle("Menú Niveles");
        ventanaSokoban = new VentanaSokoban();

        btnNivel1.addActionListener(e -> {
            dispose();
            sokoban.setNumeroDeNivelSeleccionado(ConstantesNiveles.NIVEL_1.ordinal());
            ventanaSokoban.iniciar("rsrc/Canciones/DoomEternal.wav");
        });

        btnNivel2.addActionListener(e -> {
            dispose();
            sokoban.setNumeroDeNivelSeleccionado(ConstantesNiveles.NIVEL_2.ordinal());
            ventanaSokoban.iniciar("rsrc/Canciones/Amateur.wav");
        });

        btnNivel3.addActionListener(e -> {
            dispose();
            sokoban.setNumeroDeNivelSeleccionado(ConstantesNiveles.NIVEL_3.ordinal());
            ventanaSokoban.iniciar("rsrc/Canciones/Darker.wav");
        });

        btnNivel4.addActionListener(e -> {
            dispose();
            sokoban.setNumeroDeNivelSeleccionado(ConstantesNiveles.NIVEL_4.ordinal());
            ventanaSokoban.iniciar("rsrc/Canciones/Phantom.wav");
        });

        btnVolver.addActionListener(e -> {
            MenúPrincipal menúPrincipal = new MenúPrincipal();
            menúPrincipal.crearFrame();
            dispose();
        });

        btnCargar.addActionListener(e -> {
            sokoban.cargarÚltimaPartidaJugada();
        });
    }

    public void crearFrame() {
        setContentPane(new Fondo("/Imágenes/Fondos/fondoNiveles.png"));
        add(pnlMenuNiveles);
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}