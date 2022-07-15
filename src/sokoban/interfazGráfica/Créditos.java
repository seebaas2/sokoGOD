package sokoban.interfazGráfica;

import sokoban.background.Fondo;

import javax.swing.*;

public class Créditos extends JFrame {
    private JButton btnVolverC;
    private JPanel pnlCréditos;
    private JButton btnJhordy;
    private JButton btnKarina;
    private JButton btnKevin;
    private JButton btnSebas;
    private JButton btnJorge;
    private JPanel pnlIntegrantes;

    public Créditos() {
        setTitle("Créditos");
        btnVolverC.addActionListener(e1 -> {
            MenúPrincipal menúPrincipal = new MenúPrincipal();
            menúPrincipal.crearFrame();
            dispose();
        });
    }

    public void crearFrame() {
        setContentPane(new Fondo("/Imágenes/Fondos/fondoCréditos.png"));
        add(pnlCréditos);
        setSize(1000, 700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
