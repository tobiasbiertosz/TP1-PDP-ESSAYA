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
        mano.quitarCarta(carta);
        carta.jugar(this, tablero);
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
