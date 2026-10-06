package paradigwent.modelo;

import paradigwent.modelo.objetivos.Objetivo;

public class Jugador {

    static final int CANT_VIDAS = 3;

    private final String nombre;
    private final Mazo mazo;
    private final Mano mano;
    private final PilaDescarte descarte;
    private final Faccion faccion;
    private int vidas;
    private boolean paso;

    public Jugador(String nombre, Mazo mazo, Mano mano, Faccion faccion) {
        this.nombre = nombre;
        this.mazo = mazo;
        this.mano = mano;
        this.faccion = faccion;
        this.descarte = new PilaDescarte();
        this.vidas = CANT_VIDAS;
        this.paso = false;
    }

    public void jugarCarta(Carta carta, Tablero tablero, Objetivo objetivo) {
        mano.quitarCarta(carta);
        carta.jugar(this, tablero, objetivo);
    }

    public void descartar(Carta carta) {
        descarte.agregar(carta);
    }

    public void pasarTurno() {
        this.paso = true;
    }

    public void reiniciarEstadoDeRonda() {
        this.paso = false;
    }

    public void perderVida() {
        if (vidas > 0) {
            vidas--;
        }
    }

    public void rendirse() {
        vidas = 0;
    }

    public boolean estaEliminado() {
        return vidas <= 0;
    }

    public boolean haPasado() {
        return paso;
    }

    public boolean sinCartas() {
        return mano.estaVacia();
    }

    public String getNombre() {
        return nombre;
    }

    public Mano getMano() {
        return mano;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public PilaDescarte getDescarte() {
        return descarte;
    }

    public Faccion getFaccion() {
        return faccion;
    }

    public int getVidas() {
        return vidas;
    }

    /** Devuelve la ultima carta del descarte a la mano (si hay alguna). */
    public void resucitarUltimaCarta() {
        Carta carta = descarte.sacarUltima();
        if (carta != null) {
            mano.agregarCarta(carta);
        }
    }

    /** El jugador humano espera las ordenes de la vista; la IA juega sola. */
    public boolean juegaSolo() {
        return false;
    }

    public void jugarTurno(Partida partida) {
        throw new IllegalStateException(nombre + " no juega solo");
    }
}
