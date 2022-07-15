package sokoban.juego.funcionalidadDelJuego.mapeo.bloques;

public class Caja extends Bloque {
    public boolean laCajaEstaPresionandoUnBotón;

    public Caja() {
        laCajaEstaPresionandoUnBotón = false;
        esTraspasable = false;
        direcciónSkin = getClass().getResource("/Imágenes/Bloques/caja.png");
    }

    public void setLaCajaEstaPresionandoUnBotón(boolean laCajaEstaPresionandoUnBotón) {
        this.laCajaEstaPresionandoUnBotón = laCajaEstaPresionandoUnBotón;
    }
}
