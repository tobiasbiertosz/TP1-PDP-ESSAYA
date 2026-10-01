package paradigwent.modelo;

public class CartaClima extends Carta {

    private String tipoClima;

    public CartaClima(String nombre, Faccion faccion, String tipoClima) {
        super(nombre, faccion);
        this.tipoClima = tipoClima;
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero) {
        tablero.aplicarClima(jugador, this);
    }

    public String getTipoClima() {
        return tipoClima;
    }
}
