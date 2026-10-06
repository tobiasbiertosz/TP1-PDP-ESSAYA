package paradigwent.modelo.objetivos;

import paradigwent.modelo.LineaDeAtaque;
import paradigwent.modelo.PilaDescarte;

public class ObjetivoFila implements Objetivo {

    private final LineaDeAtaque linea;
    private final PilaDescarte descarteDelDuenio;

    public ObjetivoFila(LineaDeAtaque linea, PilaDescarte descarteDelDuenio) {
        this.linea = linea;
        this.descarteDelDuenio = descarteDelDuenio;
    }

    @Override
    public void duplicarFuerza() {
        linea.duplicarFuerza();
    }

    @Override
    public void eliminar() {
        linea.descartarTodas(descarteDelDuenio);
    }

    @Override
    public String descripcion() {
        return "Fila " + linea.getTipo().getNombre();
    }
}
