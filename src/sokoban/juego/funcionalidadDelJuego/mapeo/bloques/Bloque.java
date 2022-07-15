package sokoban.juego.funcionalidadDelJuego.mapeo.bloques;

import java.io.Serializable;
import java.net.URL;

public abstract class Bloque implements Serializable {
    protected URL direcciónSkin;
    public boolean esTraspasable;

    public Bloque() {

    }

    public URL getDirecciónSkin() {
        return direcciónSkin;
    }
}
