package paradigwent.modelo;


public abstract class Carta {

    protected String nombre;
    protected Faccion faccion;

    public Carta(String nombre, Faccion faccion) {
        this.nombre = nombre;
        this.faccion = faccion;
    }

    /**
     * Cada tipo de carta hace algo distinto al jugarse (polimorfismo):
     * - Criatura: se coloca en su linea de ataque.
     * - CartaEfecto: aplica su efecto sobre el objetivo.
     * - CartaClima: pasa a ser el clima activo del tablero.
     */
    public abstract void jugar(Jugador jugador, Tablero tablero);

    public String getNombre() {
        return nombre;
    }

    public Faccion getFaccion() {
        return faccion;
    }
}
