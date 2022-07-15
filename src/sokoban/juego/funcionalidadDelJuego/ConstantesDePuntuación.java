package sokoban.juego.funcionalidadDelJuego;

public enum ConstantesDePuntuación {
    PUNTUACIÓN_MAX(10000),
    PUNTUACIÓN_MIN(0),
    COEFICIENTE_DE_RESTA(150);

    private final int puntos;

    ConstantesDePuntuación(int puntos) {
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }
}
