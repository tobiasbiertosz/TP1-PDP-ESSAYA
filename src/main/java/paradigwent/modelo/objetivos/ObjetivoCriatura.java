package paradigwent.modelo.objetivos;

import paradigwent.modelo.Criatura;
import paradigwent.modelo.LineaDeAtaque;
import paradigwent.modelo.PilaDescarte;

public class ObjetivoCriatura implements Objetivo {

    private final Criatura criatura;
    private final LineaDeAtaque linea;
    private final PilaDescarte descarteDelDuenio;

    public ObjetivoCriatura(Criatura criatura, LineaDeAtaque linea, PilaDescarte descarteDelDuenio) {
        this.criatura = criatura;
        this.linea = linea;
        this.descarteDelDuenio = descarteDelDuenio;
    }

    @Override
    public void duplicarFuerza() {
        criatura.duplicarFuerza();
    }

    @Override
    public void eliminar() {
        linea.descartar(criatura, descarteDelDuenio);
    }

    @Override
    public String descripcion() {
        return criatura.getNombre() + " (" + linea.getTipo().getNombre() + ")";
    }
}
