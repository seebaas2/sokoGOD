package sokoban.juego.funcionalidadDelJuego.mapeo.bloques;

public class Botón extends Bloque {
    public Botón() {
        esTraspasable = true;
        direcciónSkin = getClass().getResource("/Imágenes/Bloques/boton.png");
    }
}
