package paradigwent.persistencia;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import paradigwent.modelo.Carta;
import paradigwent.modelo.CartaClima;
import paradigwent.modelo.CartaEfecto;
import paradigwent.modelo.Criatura;
import paradigwent.modelo.Faccion;
import paradigwent.modelo.TipoLinea;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/** Lee el XML de cartas (en resources) y arma las facciones con todas sus cartas. */
public class CargadorDeFacciones {

    private final FabricaDeEfectos fabrica = new FabricaDeEfectos();

    public List<Faccion> cargar(String recurso) {
        try (InputStream entrada = CargadorDeFacciones.class.getResourceAsStream(recurso)) {
            if (entrada == null) {
                throw new ErrorDeCargaException("No se encontró el archivo de cartas: " + recurso);
            }
            Document documento = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder().parse(entrada);
            return leerFacciones(documento);
        } catch (IOException | SAXException | ParserConfigurationException
                 | IllegalArgumentException e) {
            throw new ErrorDeCargaException("El archivo " + recurso + " es inválido: " + e.getMessage(), e);
        }
    }

    private List<Faccion> leerFacciones(Document documento) {
        List<Faccion> facciones = new ArrayList<>();
        NodeList nodos = documento.getElementsByTagName("faccion");
        for (int i = 0; i < nodos.getLength(); i++) {
            facciones.add(leerFaccion((Element) nodos.item(i)));
        }
        return facciones;
    }

    private Faccion leerFaccion(Element elemento) {
        Faccion faccion = new Faccion(elemento.getAttribute("nombre"));
        NodeList nodos = elemento.getElementsByTagName("carta");
        for (int i = 0; i < nodos.getLength(); i++) {
            Element carta = (Element) nodos.item(i);
            int cantidad = Integer.parseInt(carta.getAttribute("cantidad"));
            for (int copia = 0; copia < cantidad; copia++) {
                // cada copia es un objeto nuevo: las cartas guardan estado propio
                faccion.agregarCarta(crearCarta(carta, faccion));
            }
        }
        return faccion;
    }

    private Carta crearCarta(Element carta, Faccion faccion) {
        String nombre = carta.getAttribute("nombre");
        String imagen = carta.getAttribute("imagen");
        String tipo = carta.getAttribute("tipo");
        return switch (tipo) {
            case "CRIATURA" -> new Criatura(nombre, faccion,
                    Integer.parseInt(texto(carta, "fuerza")),
                    TipoLinea.valueOf(texto(carta, "linea")),
                    fabrica.efecto(hijo(carta, "habilidad")), imagen);
            case "EFECTO" -> new CartaEfecto(nombre, faccion,
                    fabrica.efecto(hijo(carta, "efecto")), imagen);
            case "CLIMA" -> new CartaClima(nombre, faccion,
                    fabrica.clima(hijo(carta, "clima")), imagen);
            default -> throw new ErrorDeCargaException(
                    "Tipo de carta desconocido '" + tipo + "' en la carta " + nombre);
        };
    }

    /** Primer sub-elemento con ese nombre, o null si no existe. */
    private Element hijo(Element padre, String nombre) {
        NodeList nodos = padre.getElementsByTagName(nombre);
        return nodos.getLength() == 0 ? null : (Element) nodos.item(0);
    }

    private String texto(Element padre, String nombre) {
        Element hijo = hijo(padre, nombre);
        if (hijo == null) {
            throw new ErrorDeCargaException("Falta <" + nombre + "> en la carta "
                    + padre.getAttribute("nombre"));
        }
        return hijo.getTextContent().trim();
    }
}
