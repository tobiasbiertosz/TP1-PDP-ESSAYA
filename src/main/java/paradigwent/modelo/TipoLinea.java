package paradigwent.modelo;

/**
 * Representa las 3 lineas de ataque posibles en el tablero.
 */
public enum TipoLinea {
    CUERPO_A_CUERPO("Cuerpo a cuerpo"),
    DISTANCIA("Distancia"),
    ASEDIO("Asedio");

    private final String nombre;

    TipoLinea(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}