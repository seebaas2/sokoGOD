package sokoban.juego.funcionalidadDelJuego.mapeo.bloques;

public class Vacío extends Bloque {
    public Vacío() {
        esTraspasable = false;
        direcciónSkin = getClass().getResource("/Imágenes/Bloques/blank.png");
    }
}
