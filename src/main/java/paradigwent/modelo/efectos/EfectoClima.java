package paradigwent.modelo.efectos;

import paradigwent.modelo.TipoLinea;

/** Como un clima modifica la fuerza de una criatura segun la linea en que esta. */
public interface EfectoClima {

    int modificar(TipoLinea linea, int fuerza);

    /** Frase que explica que hace el clima. */
    String descripcion();
}
