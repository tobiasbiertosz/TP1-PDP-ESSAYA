package pruebas;

import paradigwent.modelo.*;

import java.util.ArrayList;
import java.util.List;

    public class PruebaManual {

        public static void main(String[] args) {

            // 1) Armamos una facción de prueba con un par de criaturas
            Faccion monstruos = new Faccion("Monstruos");
            Criatura ghul = new Criatura("Ghul", monstruos, 5, TipoLinea.CUERPO_A_CUERPO, false);
            Criatura arquero = new Criatura("Arquero Oscuro", monstruos, 4, TipoLinea.DISTANCIA, false);

            // 2) Jugador 1: tiene cartas reales, las va a jugar
            List<Carta> cartasJ1 = new ArrayList<>(List.of(ghul, arquero));
            Mazo mazoJ1 = new Mazo(cartasJ1);
            Mano manoJ1 = new Mano(mazoJ1.repartirMano(2)); // repartimos las 2 que tiene
            Jugador jugador1 = new Jugador("Vos", mazoJ1, manoJ1, monstruos);

            // 3) Jugador 2: lo dejamos vacío por ahora, solo hace falta que EXISTA
            //    porque el Tablero necesita conocer a los dos jugadores
            Mazo mazoJ2 = new Mazo(new ArrayList<>());
            Mano manoJ2 = new Mano(new ArrayList<>());
            Jugador jugador2 = new Jugador("Rival", mazoJ2, manoJ2, monstruos);

            // 4) Armamos el tablero
            Tablero tablero = new Tablero(jugador1, jugador2);

            // 5) ACÁ está lo que queremos probar: jugar una carta
            System.out.println("Fuerza de jugador1 ANTES de jugar: " + tablero.calcularFuerzaJugador(jugador1));

            Carta cartaElegida = jugador1.getMano().elegirCarta(0); // debería ser "Ghul"
            System.out.println("Jugando carta: " + cartaElegida.getNombre());
            jugador1.jugarCarta(cartaElegida, tablero);

            // 6) Verificamos que haya pasado lo esperado
            System.out.println("Fuerza de jugador1 DESPUÉS de jugar: " + tablero.calcularFuerzaJugador(jugador1));
            System.out.println("Cartas que le quedan en la mano: " + jugador1.getMano().getCartas().size());
        }
}
