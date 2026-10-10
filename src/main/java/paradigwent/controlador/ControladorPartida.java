package paradigwent.controlador;

import javafx.animation.PauseTransition;
import javafx.scene.Parent;
import javafx.util.Duration;
import paradigwent.modelo.Carta;
import paradigwent.modelo.Jugador;
import paradigwent.modelo.Partida;
import paradigwent.modelo.objetivos.Objetivo;
import paradigwent.vista.OyenteDeTablero;
import paradigwent.vista.VistaTablero;
import paradigwent.vista.audio.ReproductorDeAudio;
import paradigwent.vista.audio.Sonido;
import paradigwent.vista.audio.SonidosDePartida;

import java.util.List;

/** Conecta lo que hace el usuario en la vista con la logica de la Partida. */
public class ControladorPartida implements OyenteDeTablero {

    private static final Duration PAUSA_ANTES_DE_JUGAR_LA_IA = Duration.millis(900);

    private final Partida partida;
    private final Jugador humano;
    private final VistaTablero vista;
    private final ReproductorDeAudio audio;
    private final Runnable alTerminar;

    public ControladorPartida(Partida partida, ReproductorDeAudio audio, Runnable alTerminar) {
        this.partida = partida;
        this.humano = partida.getJugador1();
        this.audio = audio;
        this.alTerminar = alTerminar;
        this.vista = new VistaTablero(partida, this);
        partida.agregarObservador(new SonidosDePartida(audio));
    }

    public Parent getRaiz() {
        return vista.getRaiz();
    }

    /** Se llama una vez que la vista ya esta en pantalla: arranca la musica y, si empieza la IA, que juegue. */
    public void iniciar() {
        audio.detenerTodo();
        audio.iniciarMusicaDeFondo();
        despuesDeUnaJugada();
    }

    @Override
    public void alElegirCarta(Carta carta) {
        if (!esTurnoDelHumano()) {
            return;
        }
        List<Objetivo> objetivos = carta.objetivosPosibles(humano, tablero());
        if (objetivos.size() == 1) {
            jugar(carta, objetivos.get(0));
        } else {
            vista.pedirObjetivo(objetivos, objetivo -> jugar(carta, objetivo));
        }
    }

    @Override
    public void alPasar() {
        if (!esTurnoDelHumano()) {
            return;
        }
        partida.pasar();
        despuesDeUnaJugada();
    }

    @Override
    public void alRendirse() {
        if (partida.estaTerminada()) {
            return;
        }
        partida.rendirse(humano);
        despuesDeUnaJugada();
    }

    private void jugar(Carta carta, Objetivo objetivo) {
        partida.jugarCarta(carta, objetivo);
        despuesDeUnaJugada();
    }

    private void despuesDeUnaJugada() {
        vista.actualizar();
        if (partida.estaTerminada()) {
            terminarPartida();
        } else if (turnoActual().juegaSolo()) {
            programarJugadaAutomatica();
        }
    }

    /** Corta la musica de fondo, hace sonar victoria o derrota y muestra el resultado. */
    private void terminarPartida() {
        audio.detenerTodo();
        audio.reproducir(partida.obtenerGanadorPartida() == humano ? Sonido.VICTORIA : Sonido.DERROTA);
        vista.mostrarResultado(mensajeFinal(), alTerminar);
    }

    /** Pausa corta para que se note que la computadora jugo, y despues juega. */
    private void programarJugadaAutomatica() {
        PauseTransition pausa = new PauseTransition(PAUSA_ANTES_DE_JUGAR_LA_IA);
        pausa.setOnFinished(e -> {
            if (partida.estaTerminada()) {
                return;     // el jugador se rindio mientras la IA "pensaba"
            }
            turnoActual().jugarTurno(partida);
            despuesDeUnaJugada();
        });
        pausa.play();
    }

    private boolean esTurnoDelHumano() {
        return !partida.estaTerminada() && turnoActual() == humano;
    }

    private Jugador turnoActual() {
        return partida.getRondaActual().getTurnoActual();
    }

    private paradigwent.modelo.Tablero tablero() {
        return partida.getRondaActual().getTablero();
    }

    private String mensajeFinal() {
        Jugador ganador = partida.obtenerGanadorPartida();
        if (ganador == humano) {
            return "¡Ganaste la partida!";
        }
        if (ganador == null) {
            return "Empate: ambos jugadores se quedaron sin vidas.";
        }
        return "Perdiste la partida.";
    }
}