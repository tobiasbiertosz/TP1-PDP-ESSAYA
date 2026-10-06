package paradigwent.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/** Arma una Partida lista para jugar: sortea facciones, reparte manos e inicia. */
public class FabricaDePartida {

    public Partida crear(List<Faccion> facciones, Random random) {
        if (facciones.size() < 2) {
            throw new IllegalArgumentException("Se necesitan al menos 2 facciones");
        }
        List<Faccion> sorteadas = new ArrayList<>(facciones);
        Collections.shuffle(sorteadas, random);

        Faccion faccionHumano = sorteadas.get(0);
        Mazo mazoHumano = new Mazo(faccionHumano.getCartasDisponibles());
        Mano manoHumano = new Mano(mazoHumano.repartirMano(Partida.CANTIDAD_CARTAS_MANO_INICIAL));
        Jugador humano = new Jugador("Jugador", mazoHumano, manoHumano, faccionHumano);

        Faccion faccionIA = sorteadas.get(1);
        Mazo mazoIA = new Mazo(faccionIA.getCartasDisponibles());
        Mano manoIA = new Mano(mazoIA.repartirMano(Partida.CANTIDAD_CARTAS_MANO_INICIAL));
        Jugador computadora = new JugadorIA("Computadora", mazoIA, manoIA, faccionIA, random);

        Partida partida = new Partida(humano, computadora, random);
        partida.iniciar();
        return partida;
    }
}