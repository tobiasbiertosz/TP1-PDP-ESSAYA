package paradigwent.modelo;

public class Criatura extends Carta {

    private int fuerzaAtaque;
    private TipoLinea tipoLinea;
    private boolean tieneHabilidadEspecial;

    public Criatura(String nombre, Faccion faccion, int fuerzaAtaque,
                     TipoLinea tipoLinea, boolean tieneHabilidadEspecial) {
        super(nombre, faccion);
        this.fuerzaAtaque = fuerzaAtaque;
        this.tipoLinea = tipoLinea;
        this.tieneHabilidadEspecial = tieneHabilidadEspecial;
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero) {
        // TODO: pedirle al tablero que la coloque en la linea de ataque correspondiente
        // TODO: si tieneHabilidadEspecial es true, llamar a activarHabilidad(tablero)
    }

    public void activarHabilidad(Tablero tablero) {
        // TODO: implementar el efecto especial de esta criatura (si tiene una)
    }

    public int getFuerzaAtaque() {
        return fuerzaAtaque;
    }

    public TipoLinea getTipoLinea() {
        return tipoLinea;
    }

    public boolean tieneHabilidadEspecial() {
        return tieneHabilidadEspecial;
    }
}
