package paradigwent.modelo;

import paradigwent.modelo.efectos.Efecto;
import paradigwent.modelo.efectos.EfectoClima;
import paradigwent.modelo.efectos.SinEfecto;
import paradigwent.modelo.objetivos.Objetivo;

import java.util.List;

public class Criatura extends Carta {

    private final int fuerzaBase;
    private final TipoLinea tipoLinea;
    private final Efecto habilidad;
    private int multiplicador;

    /** Criatura comun, sin habilidad. */
    public Criatura(String nombre, Faccion faccion, int fuerzaBase,
                    TipoLinea tipoLinea, String imagen) {
        this(nombre, faccion, fuerzaBase, tipoLinea, new SinEfecto(), imagen);
    }

    public Criatura(String nombre, Faccion faccion, int fuerzaBase,
                    TipoLinea tipoLinea, Efecto habilidad, String imagen) {
        super(nombre, faccion, imagen);
        this.fuerzaBase = fuerzaBase;
        this.tipoLinea = tipoLinea;
        this.habilidad = habilidad;
        this.multiplicador = 1;
    }

    @Override
    public List<Objetivo> objetivosPosibles(Jugador jugador, Tablero tablero) {
        return habilidad.objetivosPosibles(jugador, tablero);
    }

    @Override
    public void jugar(Jugador jugador, Tablero tablero, Objetivo objetivo) {
        tablero.colocarCriatura(jugador, this);
        habilidad.aplicar(jugador, tablero, objetivo);
    }

    /** El clima fija la fuerza base y despues se aplican las duplicaciones. */
    public int calcularFuerza(EfectoClima clima) {
        return clima.modificar(tipoLinea, fuerzaBase) * multiplicador;
    }

    public void duplicarFuerza() {
        multiplicador *= 2;
    }

    public void restablecerFuerza() {
        multiplicador = 1;
    }

    public int getFuerzaBase() {
        return fuerzaBase;
    }

    public TipoLinea getTipoLinea() {
        return tipoLinea;
    }

    @Override
    public String textoDeFuerza() {
        return String.valueOf(fuerzaBase);
    }

    @Override
    public String descripcion() {
        String texto = "Criatura de " + tipoLinea.getNombre().toLowerCase()
                + ", fuerza " + fuerzaBase + ".";
        String textoHabilidad = habilidad.descripcion();
        return textoHabilidad.isEmpty() ? texto : texto + "\nHabilidad: " + textoHabilidad;
    }

    @Override
    public TipoDeCarta getTipo() {
        return TipoDeCarta.CRIATURA;
    }
}
