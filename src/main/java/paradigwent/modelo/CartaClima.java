package paradigwent.modelo;

public class CartaClima extends Carta {

    private String tipoClima;

    public CartaClima(String nombre, Faccion faccion, String tipoClima) {
        super(nombre, faccion);
        this.tipoClima = tipoClima;
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero) {
        // TODO: avisarle al tablero que esta carta es ahora el clima activo
        // (el tablero debe mandar al descarte el clima anterior, si habia uno)
    }

    public String getTipoClima() {
        return tipoClima;
    }
}
