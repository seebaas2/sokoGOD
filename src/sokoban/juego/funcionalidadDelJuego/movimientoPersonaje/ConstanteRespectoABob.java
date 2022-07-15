package sokoban.juego.funcionalidadDelJuego.movimientoPersonaje;

public enum ConstanteRespectoABob {
    UN_BLOQUE_ARRIBA(0, -1),
    UN_BLOQUE_ABAJO(0, 1),
    UN_BLOQUE_IZQUIERDA(-1, 0),
    UN_BLOQUE_DERECHA(1, 0),
    DOS_BLOQUES_ARRIBA(0, -2),
    DOS_BLOQUES_ABAJO(0, 2),
    DOS_BLOQUES_IZQUIERDA(-2, 0),
    DOS_BLOQUES_DERECHA(2, 0);

    private final int direcciónX;
    private final int direcciónY;

    ConstanteRespectoABob(int direcciónX, int direcciónY) {
        this.direcciónX = direcciónX;
        this.direcciónY = direcciónY;
    }

    public int getDirecciónX() {
        return direcciónX;
    }

    public int getDirecciónY() {
        return direcciónY;
    }
}
