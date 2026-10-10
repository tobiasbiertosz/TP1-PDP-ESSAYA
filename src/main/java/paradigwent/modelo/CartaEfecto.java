package paradigwent.modelo;

import paradigwent.modelo.efectos.Efecto;
import paradigwent.modelo.objetivos.Objetivo;

import java.util.List;

public class CartaEfecto extends Carta {

    private final Efecto efecto;

    public CartaEfecto(String nombre, Faccion faccion, Efecto efecto, String imagen) {
        super(nombre, faccion, imagen);
        this.efecto = efecto;
    }

    @Override
    public List<Objetivo> objetivosPosibles(Jugador jugador, Tablero tablero) {
        return efecto.objetivosPosibles(jugador, tablero);
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero, Objetivo objetivo) {
        efecto.aplicar(jugador, tablero, objetivo);
        jugador.descartar(this);
    }

    @Override
    public String descripcion() {
        return "Carta de efecto: " + efecto.descripcion();
    }

    @Override
    public TipoDeCarta getTipo() {
        return TipoDeCarta.EFECTO;
    }
}
