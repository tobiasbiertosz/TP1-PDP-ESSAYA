package paradigwent.modelo;

import org.junit.jupiter.api.Test;
import paradigwent.modelo.exceptions.CartaNoEstaEnManoException;
import paradigwent.modelo.exceptions.IndiceInvalidoException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ManoTest {

    private Criatura criatura(String nombre) {
        return new Criatura(nombre, new Faccion("HUMANOS"), 3, TipoLinea.ASEDIO, "");
    }

    @Test
    void quitarCartaLaSacaDeLaMano() {
        Criatura a = criatura("A");
        Mano mano = new Mano(List.of(a, criatura("B")));

        mano.quitarCarta(a);

        assertEquals(1, mano.getCartas().size());
    }

    @Test
    void quitarUnaCartaQueNoEstaLanzaExcepcion() {
        Mano mano = new Mano(List.of(criatura("A")));

        assertThrows(CartaNoEstaEnManoException.class, () -> mano.quitarCarta(criatura("otra")));
    }

    @Test
    void elegirUnIndiceInvalidoLanzaExcepcion() {
        Mano mano = new Mano(List.of(criatura("A")));

        assertThrows(IndiceInvalidoException.class, () -> mano.elegirCarta(5));
    }

    @Test
    void unaManoSinCartasEstaVacia() {
        assertTrue(new Mano(List.of()).estaVacia());
    }
}
