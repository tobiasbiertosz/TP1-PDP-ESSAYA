package paradigwent.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import paradigwent.modelo.objetivos.SinObjetivo;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Integracion entre Partida, Ronda, Tablero, Jugador, Mano y LineaDeAtaque. */
class PartidaIntegracionTest {

    /** Random que hace que siempre empiece el jugador 1. */
    private static final Random EMPIEZA_JUGADOR_1 = new Random() {
        @Override
        public boolean nextBoolean() {
            return true;
        }
    };

    private Faccion faccion;
    private Criatura fuerte;
    private Criatura debil;
    private Jugador j1;
    private Jugador j2;
    private Partida partida;

    @BeforeEach
    void armarPartida() {
        faccion = new Faccion("HUMANOS");
        fuerte = new Criatura("Fuerte", faccion, 8, TipoLinea.CUERPO_A_CUERPO, "");
        debil = new Criatura("Debil", faccion, 3, TipoLinea.CUERPO_A_CUERPO, "");
        // cada mano tiene una carta de relleno para que nadie pase automaticamente
        j1 = new Jugador("J1", new Mazo(List.of()), new Mano(List.of(fuerte, relleno())), faccion);
        j2 = new Jugador("J2", new Mazo(List.of()), new Mano(List.of(debil, relleno())), faccion);
        partida = new Partida(j1, j2, EMPIEZA_JUGADOR_1);
        partida.iniciar();
    }

    private Criatura relleno() {
        return new Criatura("Relleno", faccion, 1, TipoLinea.ASEDIO, "");
    }

    @Test
    void jugarUnaCartaLaPoneEnElTableroYPasaElTurno() {
        partida.jugarCarta(fuerte, new SinObjetivo());

        Tablero tablero = partida.getRondaActual().getTablero();
        assertEquals(8, tablero.calcularFuerzaJugador(j1));
        assertSame(j2, partida.getRondaActual().getTurnoActual());
    }

    @Test
    void quienTieneMenosFuerzaPierdeUnaVidaYElTableroSeLimpia() {
        partida.jugarCarta(fuerte, new SinObjetivo());
        partida.jugarCarta(debil, new SinObjetivo());
        partida.pasar();   // pasa J1
        partida.pasar();   // pasa J2 -> cierra la ronda

        assertEquals(3, j1.getVidas());
        assertEquals(2, j2.getVidas());
        assertEquals(1, partida.getRondasCerradas());
        assertEquals(0, partida.getRondaActual().getTablero().calcularFuerzaJugador(j1));
        assertTrue(j1.getDescarte().getCartas().contains(fuerte));
    }

    @Test
    void unEmpateHaceQueAmbosPierdanUnaVida() {
        partida.pasar();
        partida.pasar();

        assertEquals(2, j1.getVidas());
        assertEquals(2, j2.getVidas());
    }

    @Test
    void rendirseTerminaLaPartidaYGanaElOtro() {
        partida.rendirse(j1);

        assertTrue(partida.estaTerminada());
        assertSame(j2, partida.obtenerGanadorPartida());
    }

    @Test
    void sinNadieEliminadoNoHayGanadorTodavia() {
        assertNull(partida.obtenerGanadorPartida());
    }
}
