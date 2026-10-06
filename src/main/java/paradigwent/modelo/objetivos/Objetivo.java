package paradigwent.modelo.objetivos;

/**
 * Cosa sobre la que un efecto puede actuar (una criatura, una fila entera, o nada).
 * Los efectos solo conocen estas operaciones, no el tipo concreto de objetivo.
 */
public interface Objetivo {

    void duplicarFuerza();

    /** Envia el objetivo al descarte de su dueño. */
    void eliminar();
}
