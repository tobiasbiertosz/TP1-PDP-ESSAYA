package paradigwent.modelo;

public class CartaEfecto extends Carta {

    private String descripcionEfecto;

    public CartaEfecto(String nombre, Faccion faccion, String descripcionEfecto) {
        super(nombre, faccion);
        this.descripcionEfecto = descripcionEfecto;
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero) {
        // TODO: aplicar el efecto sobre la carta o la linea de ataque objetivo
    }

    public String getDescripcionEfecto() {
        return descripcionEfecto;
    }
}
