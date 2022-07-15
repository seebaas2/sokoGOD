package sokoban.juego.funcionalidadDelJuego;

import sokoban.juego.funcionalidadDelJuego.mapeo.Mapa;
import sokoban.juego.funcionalidadDelJuego.movimientoPersonaje.*;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.Serializable;
import java.util.Objects;

public class Nivel implements Serializable {
    private Bob bob;
    private final int numeroDeNivel;
    private Mapa mapa;
    private boolean elNivelEstáCompleto;
    private File rutaDelNivel;
    private int puntuación;

    public Nivel(int numeroDeNivel) {
        this.numeroDeNivel = numeroDeNivel;
        rutaDelNivel = obtenerRutaDelNivel();
        mapa = new Mapa(rutaDelNivel);
        bob = new Bob(rutaDelNivel, mapa);
        elNivelEstáCompleto = false;
    }

    public File obtenerRutaDelNivel() {
        File rutaDeNiveles = new File("rsrc/Niveles");
        String[] niveles = rutaDeNiveles.list();
        return new File(rutaDeNiveles, Objects.requireNonNull(niveles)[numeroDeNivel - 1]);
    }

    public boolean isElNivelEstáCompleto() {
        return elNivelEstáCompleto;
    }

    public void teclaPresionada(KeyEvent evento) {
        bob.interactuarConElMapa(evento);
        if (mapa.contarBotones() == 0) {
            elNivelEstáCompleto = true;
        }
    }

    public void reiniciar() {
        mapa.transformarMapaNuméricoABloques();
        bob.reiniciarBob();
        elNivelEstáCompleto = false;
    }

    public int obtenerPuntuación() {
        try {
            puntuación = ConstantesDePuntuación.PUNTUACIÓN_MAX.getPuntos() - (ConstantesDePuntuación.COEFICIENTE_DE_RESTA.getPuntos() * (bob.getNumeroDePasos() - mapa.getNúmeroDePasosParaPuntuaciónPerfecta()));
            verificarPuntuación();
        } catch (PuntuaciónMenorACeroException e) {
            puntuación = 0;
        }
        return puntuación;
    }

    private void verificarPuntuación() throws PuntuaciónMenorACeroException {
        if (puntuación < 0) {
            throw new PuntuaciónMenorACeroException();
        }
    }

    public Bob getBob() {
        return bob;
    }

    public Mapa getMapa() {
        return mapa;
    }
}