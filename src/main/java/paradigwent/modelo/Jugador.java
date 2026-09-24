package paradigwent.modelo;

public class Jugador {

    static final  int CANT_VIDAS = 3;

    protected String nombre;
    protected Mazo mazo;
    protected Mano mano;
    protected PilaDescarte descarte;
    protected Faccion faccion;
    protected int vidas;
    protected boolean paso;

    public Jugador(String nombre, Mazo mazo, Mano mano, Faccion faccion) {
        this.nombre = nombre;
        this.mazo = mazo;
        this.mano = mano;
        this.faccion = faccion;
        this.descarte = new PilaDescarte();
        this.vidas = CANT_VIDAS;
        this.paso = false;
    }

    public void jugarCarta(Carta carta, Tablero tablero) {
        // TODO: sacar la carta de la mano (mano.quitarCarta) y llamar a carta.jugar(this, tablero)
    }

    public void pasarTurno() {
        this.paso = true;
    }

    public void reiniciarEstadoDeRonda() {
        this.paso = false;
    }

    public void perderVida() {
        // TODO: restar una vida (cuidando de no bajar de 0)
    }

    public boolean estaEliminado() {
        return vidas <= 0;
    }

    public boolean haPasado() {
        return paso;
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

    public int getVidas() {
        return vidas;
    }
}
