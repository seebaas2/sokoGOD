package sokoban.juego.funcionalidadDelJuego.movimientoPersonaje;

public enum DirecciónDeSkinSegúnElMovimiento {
    CAMINANDO_ARRIBA("/Imágenes/Bob/bobBack.png"),
    CAMINANDO_ABAJO("/Imágenes/Bob/bob.png"),
    CAMINANDO_IZQUIERDA("/Imágenes/Bob/bobLeft.png"),
    CAMINANDO_DERECHA("/Imágenes/Bob/bobRight.png");

    private final String dirección;

    DirecciónDeSkinSegúnElMovimiento(String dirección) {
        this.dirección = dirección;
    }

    public String getDirección() {
        return dirección;
    }
}
