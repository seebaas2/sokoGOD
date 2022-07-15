package sokoban.juego;

import sokoban.juego.funcionalidadDelJuego.Nivel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.*;
import java.util.Objects;

public class Sokoban {
    private int numeroDeNivelSeleccionado;
    private static Nivel[] niveles;
    private final int numeroDeNiveles;
    private static File últimaPartida;

    public Sokoban() {
        últimaPartida = new File("UltimaPartidaGuardada");
        numeroDeNiveles = contarNivelesEncontrados();
        niveles = new Nivel[numeroDeNiveles];
        niveles = iniciarNiveles();
    }

    public Nivel[] iniciarNiveles() {
        Nivel[] auxNiveles = new Nivel[numeroDeNiveles];
        for (int i = 0; i < numeroDeNiveles; i++) {
            auxNiveles[i] = new Nivel(i + 1);
        }
        return auxNiveles;
    }

    public void iniciarNivel(KeyEvent evento) {
        niveles[numeroDeNivelSeleccionado].teclaPresionada(evento);
    }

    public void setNumeroDeNivelSeleccionado(int numeroDeNivel) {
        numeroDeNivelSeleccionado = numeroDeNivel;
    }

    public void reiniciarNivel() {
        niveles[numeroDeNivelSeleccionado].reiniciar();
    }

    public Nivel enviarNivelParaElPintado() {
        return niveles[numeroDeNivelSeleccionado];
    }

    public int contarNivelesEncontrados() {
        File rutaDeLosNiveles = new File("rsrc/Niveles");
        String[] files = rutaDeLosNiveles.list();
        return Objects.requireNonNull(files).length;
    }

    public static void cargarÚltimaPartidaJugada() {
        try {
            FileInputStream flujoDeEntrada = new FileInputStream(últimaPartida);
            ObjectInputStream manejadorDeLectura = new ObjectInputStream(flujoDeEntrada);
            niveles = (Nivel[]) manejadorDeLectura.readObject();
            manejadorDeLectura.close();
        } catch (StreamCorruptedException ex) {
            JOptionPane.showMessageDialog(null, "Modificaste el archivo guardado, guarda otra partida");
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se logró cargar el nivel, inicia una nueva partida");
        }
    }

    public static void guardarPartida() {
        try {
            FileOutputStream flujoDeSalida = new FileOutputStream(últimaPartida);
            ObjectOutputStream manejadorDeEscritura = new ObjectOutputStream(flujoDeSalida);
            manejadorDeEscritura.writeObject(niveles);
            manejadorDeEscritura.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se logró guardar el nivel");
        }
    }
}
