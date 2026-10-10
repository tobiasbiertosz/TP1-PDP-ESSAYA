package paradigwent.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LineaDeAtaqueTest {

    private static final ZonaDeClima SIN_CLIMA = new ZonaDeClima();

    private Criatura criatura(int fuerza) {
        return new Criatura("C" + fuerza, new Faccion("HUMANOS"), fuerza, TipoLinea.DISTANCIA, "");
    }

    @Test
    void laFuerzaTotalSumaLasCriaturas() {
        LineaDeAtaque linea = new LineaDeAtaque(TipoLinea.DISTANCIA);
        linea.agregarCriatura(criatura(3));
        linea.agregarCriatura(criatura(4));

        assertEquals(7, linea.calcularFuerzaTotal(SIN_CLIMA));
    }

    @Test
    void duplicarLaFilaDuplicaLaFuerzaTotal() {
        LineaDeAtaque linea = new LineaDeAtaque(TipoLinea.DISTANCIA);
        linea.agregarCriatura(criatura(3));
        linea.agregarCriatura(criatura(4));
        linea.duplicarFuerza();

        assertEquals(14, linea.calcularFuerzaTotal(SIN_CLIMA));
    }

    @Test
    void descartarTodasVaciaLaLineaYLlevaLasCartasAlDescarte() {
        LineaDeAtaque linea = new LineaDeAtaque(TipoLinea.DISTANCIA);
        PilaDescarte descarte = new PilaDescarte();
        linea.agregarCriatura(criatura(3));
        linea.agregarCriatura(criatura(4));
        linea.duplicarFuerza();

        linea.descartarTodas(descarte);

        assertTrue(linea.getCriaturas().isEmpty());
        assertEquals(2, descarte.getCartas().size());
        assertEquals(1, linea.getMultiplicador());
    }
}
