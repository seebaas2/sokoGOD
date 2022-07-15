package sokoban.interfazGráfica;

import sokoban.background.Fondo;

import javax.swing.*;

public class Instrucciones extends JFrame {
    private JPanel pnlInstrucciones;
    private JButton btnVolver;

    public Instrucciones() {
        setTitle("Instrucciones");
        btnVolver.addActionListener(e -> {
            MenúPrincipal menúPrincipal = new MenúPrincipal();
            menúPrincipal.crearFrame();
            dispose();
        });
    }

    public void crearFrame() {
        setContentPane(new Fondo("/Imágenes/Fondos/fondoInstrucciones.png"));
        add(pnlInstrucciones);
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
