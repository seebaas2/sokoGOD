package sokoban.juego.funcionalidadDelJuego.mapeo.bloques;

public class Suelo extends Bloque {
    public Suelo() {
        esTraspasable = true;
        direcciónSkin = getClass().getResource("/Imágenes/Bloques/suelo.jpg");
    }
}
