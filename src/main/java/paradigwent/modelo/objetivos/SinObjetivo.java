package paradigwent.modelo.objetivos;

/**
 * Objetivo nulo: se usa cuando una carta no necesita objetivo, o cuando no hay
 * ninguno disponible (el efecto "se pierde"). Evita null y chequeos de tipo.
 */
public class SinObjetivo implements Objetivo {

    @Override
    public void duplicarFuerza() {
        // nada que duplicar
    }

    @Override
    public void eliminar() {
        // nada que eliminar
    }

    @Override
    public String descripcion() {
        return "Sin objetivo";
    }
}
