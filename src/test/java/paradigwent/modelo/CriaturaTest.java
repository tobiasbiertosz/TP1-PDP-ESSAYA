package paradigwent.modelo;

import org.junit.jupiter.api.Test;
import paradigwent.modelo.efectos.EfectoClima;
import paradigwent.modelo.efectos.LimitarFuerzaEnLineas;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CriaturaTest {

    private static final EfectoClima SIN_CLIMA = new ZonaDeClima();

    private Criatura soldado() {
        return new Criatura("Soldado", new Faccion("HUMANOS"), 5, TipoLinea.CUERPO_A_CUERPO, "");
    }

    @Test
    void sinClimaLaFuerzaEsLaBase() {
        assertEquals(5, soldado().calcularFuerza(SIN_CLIMA));
    }

    @Test
    void duplicarFuerzaMultiplicaPorDos() {
        Criatura criatura = soldado();
        criatura.duplicarFuerza();
        assertEquals(10, criatura.calcularFuerza(SIN_CLIMA));
    }

    @Test
    void elClimaLimitaLaFuerzaSoloEnLaLineaAfectada() {
        EfectoClima nieve = new LimitarFuerzaEnLineas(1, Set.of(TipoLinea.CUERPO_A_CUERPO));
        EfectoClima lluvia = new LimitarFuerzaEnLineas(1, Set.of(TipoLinea.ASEDIO));

        assertEquals(1, soldado().calcularFuerza(nieve));
        assertEquals(5, soldado().calcularFuerza(lluvia));
    }

    @Test
    void restablecerFuerzaQuitaLaDuplicacion() {
        Criatura criatura = soldado();
        criatura.duplicarFuerza();
        criatura.restablecerFuerza();
        assertEquals(5, criatura.calcularFuerza(SIN_CLIMA));
    }
}
