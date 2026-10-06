package paradigwent.modelo;

import paradigwent.modelo.objetivos.Objetivo;
import paradigwent.modelo.objetivos.ObjetivoCriatura;
import paradigwent.modelo.objetivos.ObjetivoFila;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Estado del tablero para ambos jugadores durante una ronda.
 */
public class Tablero {
    private final Jugador jugador1;
    private final Jugador jugador2;
    private final Map<TipoLinea, LineaDeAtaque> lineasJugador1;
    private final Map<TipoLinea, LineaDeAtaque> lineasJugador2;
    private final ZonaDeClima zonaDeClima;

    public Tablero(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.lineasJugador1 = new EnumMap<>(TipoLinea.class);
        this.lineasJugador2 = new EnumMap<>(TipoLinea.class);
        this.zonaDeClima = new ZonaDeClima();
        for (TipoLinea tipo : TipoLinea.values()) {
            lineasJugador1.put(tipo, new LineaDeAtaque(tipo));
            lineasJugador2.put(tipo, new LineaDeAtaque(tipo));
        }
    }

    private Map<TipoLinea, LineaDeAtaque> lineasDe(Jugador jugador) {
        return jugador == jugador1 ? lineasJugador1 : lineasJugador2;
    }

    public Jugador oponenteDe(Jugador jugador) {
        return jugador == jugador1 ? jugador2 : jugador1;
    }

    public void colocarCriatura(Jugador jugador, Criatura criatura) {
        lineasDe(jugador).get(criatura.getTipoLinea()).agregarCriatura(criatura);
    }

    public void aplicarClima(Jugador jugador, CartaClima nuevoClima) {
        zonaDeClima.colocar(nuevoClima, jugador.getDescarte());
    }

    /** Una opcion por cada criatura que tiene en juego el duenio. */
    public List<Objetivo> objetivosCriaturas(Jugador duenio) {
        List<Objetivo> objetivos = new ArrayList<>();
        for (LineaDeAtaque linea : lineasDe(duenio).values()) {
            for (Criatura criatura : linea.getCriaturas()) {
                objetivos.add(new ObjetivoCriatura(criatura, linea, duenio.getDescarte()));
            }
        }
        return objetivos;
    }

    /** Una opcion por cada linea de ataque del duenio. */
    public List<Objetivo> objetivosFilas(Jugador duenio) {
        List<Objetivo> objetivos = new ArrayList<>();
        for (LineaDeAtaque linea : lineasDe(duenio).values()) {
            objetivos.add(new ObjetivoFila(linea, duenio.getDescarte()));
        }
        return objetivos;
    }

    public int calcularFuerzaJugador(Jugador jugador) {
        int total = 0;
        for (LineaDeAtaque linea : lineasDe(jugador).values()) {
            total += linea.calcularFuerzaTotal(zonaDeClima);
        }
        return total;
    }

    public List<Criatura> criaturasEn(Jugador jugador, TipoLinea tipo) {
        return lineasDe(jugador).get(tipo).getCriaturas();
    }

    public int calcularFuerzaLinea(Jugador jugador, TipoLinea tipo) {
        return lineasDe(jugador).get(tipo).calcularFuerzaTotal(zonaDeClima);
    }

    public void limpiarTablero() {
        descartarLineas(lineasJugador1, jugador1);
        descartarLineas(lineasJugador2, jugador2);
        zonaDeClima.limpiar();
    }

    private void descartarLineas(Map<TipoLinea, LineaDeAtaque> lineas, Jugador duenio) {
        for (LineaDeAtaque linea : lineas.values()) {
            linea.descartarTodas(duenio.getDescarte());
        }
    }

    public CartaClima getClimaActivo() {
        return zonaDeClima.getCartaActiva();
    }
}
