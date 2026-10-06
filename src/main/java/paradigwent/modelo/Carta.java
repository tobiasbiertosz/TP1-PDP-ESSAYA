package paradigwent.modelo;


import paradigwent.modelo.objetivos.Objetivo;
import paradigwent.modelo.objetivos.SinObjetivo;

import java.util.List;

public abstract class Carta {

    protected String nombre;
    protected Faccion faccion;

    public Carta(String nombre, Faccion faccion) {
        this.nombre = nombre;
        this.faccion = faccion;
    }

    /**
     * Objetivos entre los que el jugador puede elegir antes de jugar la carta.
     * Por defecto la carta no necesita objetivo. Nunca devuelve una lista vacia.
     */
    public List<Objetivo> objetivosPosibles(Jugador jugador, Tablero tablero) {
        return List.of(new SinObjetivo());
    }

    /**
     * Cada tipo de carta hace algo distinto al jugarse (polimorfismo):
     * - Criatura: se coloca en su linea de ataque.
     * - CartaEfecto: aplica su efecto sobre el objetivo.
     * - CartaClima: pasa a ser el clima activo del tablero.
     */
    public abstract void jugar(Jugador jugador, Tablero tablero, Objetivo objetivo);

    public String getNombre() {
        return nombre;
    }

    public Faccion getFaccion() {
        return faccion;
    }
}
