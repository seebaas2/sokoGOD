package sokoban.interfazGráfica;

import sokoban.background.Fondo;
import sokoban.juego.Sokoban;

import javax.swing.*;

public class MenúPrincipal extends JFrame {
    private JButton btnJugar;
    private JButton btnCréditos;
    private JButton btnSalir;
    private JButton btnInstrucciones;
    private JPanel pnlMenúPrincipal;
    private JPanel pnlTítulo;
    private JPanel pnlBotones;
    private JPanel pnlLogo;
    private JPanel pnlBajo;
    static Sokoban sokoban = new Sokoban();

    public MenúPrincipal() {
        this.setTitle("Menú");

        btnJugar.addActionListener(e1 -> {
            MenuNiveles niveles = new MenuNiveles();
            dispose();
            niveles.crearFrame();
        });

        btnCréditos.addActionListener(e1 -> {
            Créditos frameCréditos = new Créditos();
            dispose();
            frameCréditos.crearFrame();
        });

        btnInstrucciones.addActionListener(e -> {
            Instrucciones frameInstrucciones = new Instrucciones();
            dispose();
            frameInstrucciones.crearFrame();
        });

        btnSalir.addActionListener(e1 -> System.exit(0));
    }

    public void crearFrame() {
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(new Fondo("/Imágenes/Fondos/fondoInicio.png"));
        add(pnlMenúPrincipal);
        setVisible(true);
    }
}

