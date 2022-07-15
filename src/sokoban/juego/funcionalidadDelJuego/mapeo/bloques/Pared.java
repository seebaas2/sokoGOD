package sokoban.juego.funcionalidadDelJuego.mapeo.bloques;

public class Pared extends Bloque {
    public Pared() {
        esTraspasable = false;
        direcciónSkin = getClass().getResource("/Imágenes/Bloques/pared.jpg");
    }
}
