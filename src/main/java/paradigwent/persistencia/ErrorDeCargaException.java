package paradigwent.persistencia;

/** Se lanza cuando el archivo de cartas no existe o tiene un formato invalido. */
public class ErrorDeCargaException extends RuntimeException {

    public ErrorDeCargaException(String mensaje) {
        super(mensaje);
    }

    public ErrorDeCargaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
