package sokoban.juego.funcionalidadDelJuego.movimientoPersonaje;

import sokoban.juego.funcionalidadDelJuego.mapeo.Mapa;
import sokoban.juego.funcionalidadDelJuego.mapeo.bloques.*;

import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;

public class Bob implements Serializable {
    private int posiciónEnX;
    private int posiciónEnY;
    private URL direcciónSkin = getClass().getResource("/Imágenes/Bob/bob.png");
    private final File rutaDelNivel;
    private Mapa mapa;
    private int numeroDePasos;
    private int numeroDeEmpujones;

    public Bob(File rutaDelNivel, Mapa mapa) {
        this.rutaDelNivel = rutaDelNivel;
        this.mapa = mapa;
        posiciónEnX = tomarPosiciónEnXDelArchivo();
        posiciónEnY = tomarPosiciónEnYDelArchivo();
        numeroDePasos = 0;
        numeroDeEmpujones = 0;
    }

    //Tomar y preguntar que bloque es
    public boolean elBloqueEsUnaCaja(ConstanteRespectoABob constanteRespectoABob) {
        return devolverElBloqueRespectoABob(constanteRespectoABob) instanceof Caja;
    }

    public boolean elBloqueEsUnBotón(ConstanteRespectoABob constanteRespectoABob) {
        return devolverElBloqueRespectoABob(constanteRespectoABob) instanceof Botón;
    }

    public boolean elBloqueEsUnSuelo(ConstanteRespectoABob constanteRespectoABob) {
        return devolverElBloqueRespectoABob(constanteRespectoABob) instanceof Suelo;
    }

    public boolean elBloqueEsTraspasable(ConstanteRespectoABob constanteRespectoABob) {
        return devolverElBloqueRespectoABob(constanteRespectoABob).esTraspasable;
    }

    public Bloque devolverElBloqueRespectoABob(ConstanteRespectoABob constanteRespectoABob) {
        return mapa.getDiseñoEnBloques()[posiciónEnY() + constanteRespectoABob.getDirecciónY()][posiciónEnX() + constanteRespectoABob.getDirecciónX()];
    }

    //Pregunta la posibilidad de empujar una caja
    public boolean puedeEmpujarCajaParticular(ConstanteRespectoABob constanteRespectoABob1, ConstanteRespectoABob constanteRespectoABob2) {
        if (elBloqueEsUnaCaja(constanteRespectoABob1)) {
            return elBloqueEsTraspasable(constanteRespectoABob2);
        }
        return false;
    }

    public boolean puedeEmpujarCajaGeneral(KeyEvent evento) {
        //Arriba
        if (evento.getKeyCode() == KeyEvent.VK_UP) {
            return puedeEmpujarCajaParticular(ConstanteRespectoABob.UN_BLOQUE_ARRIBA, ConstanteRespectoABob.DOS_BLOQUES_ARRIBA);
        }
        //Abajo
        if (evento.getKeyCode() == KeyEvent.VK_DOWN) {
            return puedeEmpujarCajaParticular(ConstanteRespectoABob.UN_BLOQUE_ABAJO, ConstanteRespectoABob.DOS_BLOQUES_ABAJO);
        }
        //Izquierda
        if (evento.getKeyCode() == KeyEvent.VK_LEFT) {
            return puedeEmpujarCajaParticular(ConstanteRespectoABob.UN_BLOQUE_IZQUIERDA, ConstanteRespectoABob.DOS_BLOQUES_IZQUIERDA);
        }
        //Derecha
        if (evento.getKeyCode() == KeyEvent.VK_RIGHT) {
            return puedeEmpujarCajaParticular(ConstanteRespectoABob.UN_BLOQUE_DERECHA, ConstanteRespectoABob.DOS_BLOQUES_DERECHA);
        }
        return false;
    }

    //Métodos para los casos de empujar caja
    public boolean estáPresionandoUnBotónLaCajaQueDeseaMover(ConstanteRespectoABob constanteRespectoABob) {
        return ((Caja) devolverElBloqueRespectoABob(constanteRespectoABob)).laCajaEstaPresionandoUnBotón;
    }

    public void mutarBloque(ConstanteRespectoABob constanteRespectoABob, Bloque bloque) {
        mapa.getDiseñoEnBloques()[posiciónEnY() + constanteRespectoABob.getDirecciónY()][posiciónEnX() + constanteRespectoABob.getDirecciónX()] = bloque;
    }

    public void cambiarEstadoDeLaCaja(ConstanteRespectoABob constanteRespectoABob, boolean presionaBotón) {
        ((Caja) devolverElBloqueRespectoABob(constanteRespectoABob)).setLaCajaEstaPresionandoUnBotón(presionaBotón);
    }

    public void empujarCajaGeneral(KeyEvent evento) {
        //Arriba
        if (evento.getKeyCode() == KeyEvent.VK_UP) {
            empujarCajaParticular(ConstanteRespectoABob.UN_BLOQUE_ARRIBA, ConstanteRespectoABob.DOS_BLOQUES_ARRIBA);
        }
        //Abajo
        if (evento.getKeyCode() == KeyEvent.VK_DOWN) {
            empujarCajaParticular(ConstanteRespectoABob.UN_BLOQUE_ABAJO, ConstanteRespectoABob.DOS_BLOQUES_ABAJO);
        }
        //Izquierda
        if (evento.getKeyCode() == KeyEvent.VK_LEFT) {
            empujarCajaParticular(ConstanteRespectoABob.UN_BLOQUE_IZQUIERDA, ConstanteRespectoABob.DOS_BLOQUES_IZQUIERDA);
        }
        //Derecha
        if (evento.getKeyCode() == KeyEvent.VK_RIGHT) {
            empujarCajaParticular(ConstanteRespectoABob.UN_BLOQUE_DERECHA, ConstanteRespectoABob.DOS_BLOQUES_DERECHA);
        }
    }

    //Ejecución de empujar caja
    public void empujarCajaParticular(ConstanteRespectoABob constanteRespectoABob1, ConstanteRespectoABob constanteRespectoABob2) {
        if (esUnEmpujarDeTipoUno(constanteRespectoABob1, constanteRespectoABob2)) {
            empujarEnCasoDeTipoUno(constanteRespectoABob1, constanteRespectoABob2);
        } else if (esUnEmpujarDeTipoDos(constanteRespectoABob1, constanteRespectoABob2)) {
            empujarEnCasoDeTipoDos(constanteRespectoABob1, constanteRespectoABob2);
        } else if (esUnEmpujarDeTipoTres(constanteRespectoABob1, constanteRespectoABob2)) {
            empujarEnCasoDeTipoTres(constanteRespectoABob1, constanteRespectoABob2);
        } else {
            empujarDeterminado(constanteRespectoABob1, constanteRespectoABob2);
        }
        numeroDeEmpujones++;
    }

    //Casos para empujar caja
    //Tipo 1 un boton y caja sin presionar un boton
    public boolean esUnEmpujarDeTipoUno(ConstanteRespectoABob constanteRespectoABob1, ConstanteRespectoABob constanteRespectoABob2) {
        return elBloqueEsUnBotón(constanteRespectoABob2) & !estáPresionandoUnBotónLaCajaQueDeseaMover(constanteRespectoABob1);
    }

    public void empujarEnCasoDeTipoUno(ConstanteRespectoABob constanteRespectoABob1, ConstanteRespectoABob constanteRespectoABob2) {
        mutarBloque(constanteRespectoABob1, new Suelo());
        mutarBloque(constanteRespectoABob2, new Caja());
        cambiarEstadoDeLaCaja(constanteRespectoABob2, true);
    }

    //Tipo 2 un boton y caja si presiona un boton
    public boolean esUnEmpujarDeTipoDos(ConstanteRespectoABob constanteRespectoABob1, ConstanteRespectoABob constanteRespectoABob2) {
        return elBloqueEsUnBotón(constanteRespectoABob2) & estáPresionandoUnBotónLaCajaQueDeseaMover(constanteRespectoABob1);
    }

    public void empujarEnCasoDeTipoDos(ConstanteRespectoABob constanteRespectoABob1, ConstanteRespectoABob constanteRespectoABob2) {
        mutarBloque(constanteRespectoABob1, new Botón());
        mutarBloque(constanteRespectoABob2, new Caja());
        cambiarEstadoDeLaCaja(constanteRespectoABob2, true);
    }

    //Tipo 3 un suelo y caja si presiona un boton
    public boolean esUnEmpujarDeTipoTres(ConstanteRespectoABob constanteRespectoABob1, ConstanteRespectoABob constanteRespectoABob2) {
        return elBloqueEsUnSuelo(constanteRespectoABob2) & estáPresionandoUnBotónLaCajaQueDeseaMover(constanteRespectoABob1);
    }

    public void empujarEnCasoDeTipoTres(ConstanteRespectoABob constanteRespectoABob1, ConstanteRespectoABob constanteRespectoABob2) {
        mutarBloque(constanteRespectoABob1, new Botón());
        mutarBloque(constanteRespectoABob2, new Caja());
        //cambiarEstadoDeLaCaja(constanteRespectoABob2, false);
    }

    //Tipo sin restricción
    public void empujarDeterminado(ConstanteRespectoABob constanteRespectoABob1, ConstanteRespectoABob constanteRespectoABob2) {
        mutarBloque(constanteRespectoABob1, new Suelo());
        mutarBloque(constanteRespectoABob2, new Caja());
    }

    //El caminar
    public boolean puedeAvanzar(KeyEvent evento) {
        //Arriba
        if (evento.getKeyCode() == KeyEvent.VK_UP) {
            return elBloqueEsTraspasable(ConstanteRespectoABob.UN_BLOQUE_ARRIBA);
        }
        //Abajo
        if (evento.getKeyCode() == KeyEvent.VK_DOWN) {
            return elBloqueEsTraspasable(ConstanteRespectoABob.UN_BLOQUE_ABAJO);
        }
        //Izquierda
        if (evento.getKeyCode() == KeyEvent.VK_LEFT) {
            return elBloqueEsTraspasable(ConstanteRespectoABob.UN_BLOQUE_IZQUIERDA);
        }
        //Derecha
        if (evento.getKeyCode() == KeyEvent.VK_RIGHT) {
            return elBloqueEsTraspasable(ConstanteRespectoABob.UN_BLOQUE_DERECHA);
        }
        return false;
    }

    public void caminarParticular(ConstanteRespectoABob constanteRespectoABob) {
        posiciónEnY = posiciónEnY + constanteRespectoABob.getDirecciónY();
        posiciónEnX = posiciónEnX + constanteRespectoABob.getDirecciónX();
        numeroDePasos++;
    }

    public void actualizarDirecciónDeSkin(DirecciónDeSkinSegúnElMovimiento direcciónDeSkinSegúnElMovimiento) {
        direcciónSkin = getClass().getResource(direcciónDeSkinSegúnElMovimiento.getDirección());
    }

    public void caminarGeneral(KeyEvent evento) {
        //Arriba
        if (evento.getKeyCode() == KeyEvent.VK_UP) {
            caminarParticular(ConstanteRespectoABob.UN_BLOQUE_ARRIBA);
            actualizarDirecciónDeSkin(DirecciónDeSkinSegúnElMovimiento.CAMINANDO_ARRIBA);
        }
        //Abajo
        if (evento.getKeyCode() == KeyEvent.VK_DOWN) {
            caminarParticular(ConstanteRespectoABob.UN_BLOQUE_ABAJO);
            actualizarDirecciónDeSkin(DirecciónDeSkinSegúnElMovimiento.CAMINANDO_ABAJO);
        }
        //Izquierda
        if (evento.getKeyCode() == KeyEvent.VK_LEFT) {
            caminarParticular(ConstanteRespectoABob.UN_BLOQUE_IZQUIERDA);
            actualizarDirecciónDeSkin(DirecciónDeSkinSegúnElMovimiento.CAMINANDO_IZQUIERDA);
        }
        //Derecha
        if (evento.getKeyCode() == KeyEvent.VK_RIGHT) {
            caminarParticular(ConstanteRespectoABob.UN_BLOQUE_DERECHA);
            actualizarDirecciónDeSkin(DirecciónDeSkinSegúnElMovimiento.CAMINANDO_DERECHA);
        }
    }

    public void interactuarConElMapa(KeyEvent evento) {
        if (puedeAvanzar(evento)) {
            caminarGeneral(evento);
        } else if (puedeEmpujarCajaGeneral(evento)) {
            empujarCajaGeneral(evento);
            caminarGeneral(evento);
        }
    }

    public int tomarPosiciónEnYDelArchivo() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaDelNivel));
            String cadena;
            cadena = bufferedReader.readLine();
            return Integer.parseInt(cadena.substring(0, 2).replaceAll("\\s+", "")) - 1;
        } catch (IOException e) {
            System.out.println("Error de carga del personaje");
        }
        return 0;
    }

    public int tomarPosiciónEnXDelArchivo() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(rutaDelNivel));
            String cadena;
            cadena = bufferedReader.readLine();
            return Integer.parseInt(cadena.substring(2).replaceAll("\\s+", "")) - 1;
        } catch (IOException e) {
            System.out.println("Error de carga del personaje");
        }
        return 0;
    }

    public void reiniciarBob() {
        posiciónEnX = tomarPosiciónEnXDelArchivo();
        posiciónEnY = tomarPosiciónEnYDelArchivo();
        numeroDeEmpujones = 0;
        numeroDePasos = 0;
    }

    public int posiciónEnX() {
        return posiciónEnX;
    }

    public int posiciónEnY() {
        return posiciónEnY;
    }

    public URL getDirecciónSkin() {
        return direcciónSkin;
    }

    public int getNumeroDePasos() {
        return numeroDePasos;
    }

    public int getNumeroDeEmpujones() {
        return numeroDeEmpujones;
    }
}
