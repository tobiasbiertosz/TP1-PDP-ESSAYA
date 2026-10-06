package paradigwent.persistencia;

import org.w3c.dom.Element;
import paradigwent.modelo.TipoLinea;
import paradigwent.modelo.efectos.Bando;
import paradigwent.modelo.efectos.DuplicarCriatura;
import paradigwent.modelo.efectos.DuplicarFila;
import paradigwent.modelo.efectos.Efecto;
import paradigwent.modelo.efectos.EfectoClima;
import paradigwent.modelo.efectos.EliminarCriatura;
import paradigwent.modelo.efectos.LimitarFuerzaEnLineas;
import paradigwent.modelo.efectos.ResucitarUltimaCarta;
import paradigwent.modelo.efectos.SinEfecto;

import java.util.EnumSet;
import java.util.Set;

/** Traduce los elementos efecto, habilidad y clima del XML a objetos del modelo. */
class FabricaDeEfectos {

    /** Si el elemento es null (la carta no declara efecto) devuelve un efecto nulo. */
    Efecto efecto(Element elemento) {
        if (elemento == null) {
            return new SinEfecto();
        }
        String tipo = elemento.getAttribute("tipo");
        return switch (tipo) {
            case "DUPLICAR_CRIATURA" -> new DuplicarCriatura(bando(elemento));
            case "DUPLICAR_FILA" -> new DuplicarFila(bando(elemento));
            case "ELIMINAR_CRIATURA" -> new EliminarCriatura(bando(elemento));
            case "RESUCITAR_ULTIMA_CARTA_DESCARTE" -> new ResucitarUltimaCarta();
            default -> throw new ErrorDeCargaException("Efecto desconocido: '" + tipo + "'");
        };
    }

    EfectoClima clima(Element elemento) {
        if (elemento == null) {
            throw new ErrorDeCargaException("Una carta de clima no declara su <clima>");
        }
        String tipo = elemento.getAttribute("tipo");
        return switch (tipo) {
            case "LIMITAR_FUERZA" -> new LimitarFuerzaEnLineas(
                    Integer.parseInt(elemento.getAttribute("maximo")), lineas(elemento));
            default -> throw new ErrorDeCargaException("Clima desconocido: '" + tipo + "'");
        };
    }

    private Bando bando(Element elemento) {
        return Bando.valueOf(elemento.getAttribute("bando"));
    }

    /** Lee un atributo como "DISTANCIA,ASEDIO". */
    private Set<TipoLinea> lineas(Element elemento) {
        Set<TipoLinea> lineas = EnumSet.noneOf(TipoLinea.class);
        for (String nombre : elemento.getAttribute("lineas").split(",")) {
            lineas.add(TipoLinea.valueOf(nombre.trim()));
        }
        return lineas;
    }
}