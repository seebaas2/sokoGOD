package sokoban.background;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Fondo extends JPanel {
    private final String rutaDeLaImagen;

    public Fondo(String rutaDeLaImagen) {
        this.rutaDeLaImagen = rutaDeLaImagen;
    }

    public void paintComponent(Graphics g) {
        Dimension dimension = getSize();
        Image imagenFondo = new ImageIcon(Objects.requireNonNull(getClass().getResource(rutaDeLaImagen))).getImage();
        g.drawImage(imagenFondo, 0, 0, dimension.width, dimension.height, null);
        setOpaque(false);
        //super.paintChildren(g);
    }
}
