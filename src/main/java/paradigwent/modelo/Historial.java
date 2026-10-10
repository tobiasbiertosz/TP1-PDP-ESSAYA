package paradigwent.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Registro, en orden, de lo que fue pasando en la partida. */
public class Historial {

    private final List<String> entradas = new ArrayList<>();

    public void registrar(String entrada) {
        entradas.add(entrada);
    }

    public List<String> getEntradas() {
        return Collections.unmodifiableList(entradas);
    }
}
