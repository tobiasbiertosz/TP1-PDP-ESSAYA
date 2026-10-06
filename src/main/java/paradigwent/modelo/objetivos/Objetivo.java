package paradigwent.modelo.objetivos;

/**
 * Cosa sobre la que un efecto puede actuar (una criatura, una fila entera, o nada).
 * Los efectos solo conocen estas operaciones, no el tipo concreto de objetivo.
 */
public interface Objetivo {

    /** Texto corto para que el jugador distinga este objetivo al elegirlo. */
    String descripcion();

    void duplicarFuerza();

    /** Envia el objetivo al descarte de su dueño. */
    void eliminar();
}
