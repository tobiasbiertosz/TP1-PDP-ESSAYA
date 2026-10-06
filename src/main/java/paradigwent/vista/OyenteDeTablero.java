package paradigwent.vista;

import paradigwent.modelo.Carta;

/** Lo que la vista le avisa al controlador cuando el usuario hace algo. */
public interface OyenteDeTablero {

    void alElegirCarta(Carta carta);

    void alPasar();

    void alRendirse();
}
