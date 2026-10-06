package paradigwent.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import paradigwent.modelo.Carta;
import paradigwent.modelo.CartaClima;
import paradigwent.modelo.Criatura;
import paradigwent.modelo.Jugador;
import paradigwent.modelo.Partida;
import paradigwent.modelo.Tablero;
import paradigwent.modelo.TipoLinea;
import paradigwent.modelo.objetivos.Objetivo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/** Dibuja el estado de una Partida. El jugador 1 es el humano (abajo). */
public class VistaTablero {

    private static final double ANCHO_EN_FILA = 40;
    private static final double ANCHO_EN_MANO = 72;
    private static final double ALTO_FILA = ANCHO_EN_FILA * 1.5 + 8;

    private static final List<TipoLinea> ORDEN_RIVAL =
            List.of(TipoLinea.ASEDIO, TipoLinea.DISTANCIA, TipoLinea.CUERPO_A_CUERPO);
    private static final List<TipoLinea> ORDEN_LOCAL =
            List.of(TipoLinea.CUERPO_A_CUERPO, TipoLinea.DISTANCIA, TipoLinea.ASEDIO);

    private static final String ESTILO_ZONA =
            "-fx-background-color: rgba(255, 255, 255, 0.08); -fx-background-radius: 6;";
    private static final String ESTILO_TEXTO = "-fx-text-fill: white;";

    private final Partida partida;
    private final Jugador local;
    private final Jugador rival;
    private final OyenteDeTablero oyente;
    private final BorderPane raiz = new BorderPane();

    public VistaTablero(Partida partida, OyenteDeTablero oyente) {
        this.partida = partida;
        this.local = partida.getJugador1();
        this.rival = partida.getJugador2();
        this.oyente = oyente;

        raiz.setStyle("-fx-background-color: #1e2a32;");
        actualizar();
    }

    /** Vuelve a dibujar todo a partir del estado actual de la partida. */
    public void actualizar() {
        Tablero tablero = partida.getRondaActual().getTablero();
        raiz.setLeft(crearPanelLateral(tablero));
        raiz.setCenter(crearCentro(tablero));
        raiz.setBottom(crearMano());
    }

    public Parent getRaiz() {
        return raiz;
    }

    /** Muestra las opciones y avisa la elegida. Si el jugador cancela, no pasa nada. */
    public void pedirObjetivo(List<Objetivo> objetivos, Consumer<Objetivo> alElegir) {
        List<String> opciones = new ArrayList<>();
        for (int i = 0; i < objetivos.size(); i++) {
            opciones.add((i + 1) + ". " + objetivos.get(i).descripcion());
        }

        ChoiceDialog<String> dialogo = new ChoiceDialog<>(opciones.get(0), opciones);
        dialogo.setTitle("Elegir objetivo");
        dialogo.setHeaderText("¿Sobre qué querés usar la carta?");
        dialogo.setContentText("Objetivo:");
        dialogo.showAndWait().ifPresent(
                elegida -> alElegir.accept(objetivos.get(opciones.indexOf(elegida))));
    }

    /** Cartel de fin de partida. Cuando el jugador lo cierra, se ejecuta alCerrar. */
    public void mostrarResultado(String mensaje, Runnable alCerrar) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, mensaje);
        alerta.setTitle("Fin de la partida");
        alerta.setHeaderText(null);
        alerta.setOnHidden(e -> alCerrar.run());
        alerta.show();
    }

    // ---------- panel lateral: datos de cada jugador y botones ----------

    private Node crearPanelLateral(Tablero tablero) {
        boolean esMiTurno = partida.getRondaActual().getTurnoActual() == local;

        Label turno = etiqueta("Turno: " + partida.getRondaActual().getTurnoActual().getNombre());
        turno.setStyle(ESTILO_TEXTO + "-fx-font-weight: bold; -fx-font-size: 14px;");

        Region separador = new Region();
        VBox.setVgrow(separador, Priority.ALWAYS);

        Button pasar = new Button("Pasar");
        pasar.setDisable(!esMiTurno);
        pasar.setOnAction(e -> oyente.alPasar());

        Button rendirse = new Button("Rendirse");
        rendirse.setOnAction(e -> oyente.alRendirse());

        VBox panel = new VBox(10,
                crearPanelJugador(rival, tablero),
                separador,
                turno,
                crearPanelJugador(local, tablero),
                new HBox(8, pasar, rendirse));
        panel.setPadding(new Insets(10));
        panel.setPrefWidth(170);
        panel.setMinWidth(170);
        return panel;
    }

    private Node crearPanelJugador(Jugador jugador, Tablero tablero) {
        VBox datos = new VBox(3);
        Label nombre = etiqueta(jugador.getNombre() + " (" + jugador.getFaccion().getNombre() + ")");
        nombre.setStyle(ESTILO_TEXTO + "-fx-font-weight: bold;");
        datos.getChildren().addAll(
                nombre,
                etiqueta("Vidas: " + jugador.getVidas()),
                etiqueta("Fuerza total: " + tablero.calcularFuerzaJugador(jugador)),
                etiqueta("Mano: " + jugador.getMano().getCartas().size()),
                etiqueta("Mazo: " + jugador.getMazo().cantidadDeCartas()),
                etiqueta("Descarte: " + jugador.getDescarte().getCartas().size()));
        if (jugador.haPasado()) {
            datos.getChildren().add(etiqueta("Pasó"));
        }
        datos.setPadding(new Insets(8));
        datos.setStyle(ESTILO_ZONA);
        return datos;
    }

    private Label etiqueta(String texto) {
        Label label = new Label(texto);
        label.setStyle(ESTILO_TEXTO);
        return label;
    }

    // ---------- centro: filas de ataque y clima ----------

    private Node crearCentro(Tablero tablero) {
        VBox centro = new VBox(4);
        centro.setPadding(new Insets(8));
        centro.getChildren().addAll(crearFilas(tablero, rival, ORDEN_RIVAL));
        centro.getChildren().add(crearZonaClima(tablero));
        centro.getChildren().addAll(crearFilas(tablero, local, ORDEN_LOCAL));
        return centro;
    }

    private List<Node> crearFilas(Tablero tablero, Jugador jugador, List<TipoLinea> orden) {
        List<Node> filas = new ArrayList<>();
        for (TipoLinea tipo : orden) {
            filas.add(crearFila(tablero, jugador, tipo));
        }
        return filas;
    }

    private Node crearFila(Tablero tablero, Jugador jugador, TipoLinea tipo) {
        Label puntos = new Label(String.valueOf(tablero.calcularFuerzaLinea(jugador, tipo)));
        puntos.setStyle(ESTILO_TEXTO + "-fx-font-size: 20px; -fx-font-weight: bold;");

        Label nombreLinea = new Label(tipo.getNombre());
        nombreLinea.setStyle("-fx-text-fill: #bbbbbb; -fx-font-size: 10px;");

        VBox encabezado = new VBox(2, puntos, nombreLinea);
        encabezado.setAlignment(Pos.CENTER);
        encabezado.setMinWidth(90);
        encabezado.setPrefWidth(90);

        HBox cartas = new HBox(4);
        cartas.setAlignment(Pos.CENTER_LEFT);
        for (Criatura criatura : tablero.criaturasEn(jugador, tipo)) {
            cartas.getChildren().add(new VistaCarta(criatura, ANCHO_EN_FILA));
        }

        HBox fila = new HBox(8, encabezado, cartas);
        fila.setAlignment(Pos.CENTER_LEFT);
        fila.setMinHeight(ALTO_FILA);
        fila.setPrefHeight(ALTO_FILA);
        fila.setMaxHeight(ALTO_FILA);
        fila.setStyle(ESTILO_ZONA);
        return fila;
    }

    private Node crearZonaClima(Tablero tablero) {
        Label titulo = new Label("Clima");
        titulo.setStyle(ESTILO_TEXTO + "-fx-font-size: 14px; -fx-font-weight: bold;");
        titulo.setMinWidth(90);
        titulo.setAlignment(Pos.CENTER);

        HBox zona = new HBox(8, titulo);
        zona.setAlignment(Pos.CENTER_LEFT);
        zona.setMinHeight(ALTO_FILA);
        zona.setPrefHeight(ALTO_FILA);
        zona.setMaxHeight(ALTO_FILA);
        zona.setStyle(ESTILO_ZONA);

        CartaClima clima = tablero.getClimaActivo();
        if (clima != null) {
            zona.getChildren().add(new VistaCarta(clima, ANCHO_EN_FILA));
        }
        return zona;
    }

    // ---------- abajo: la mano del jugador ----------

    private Node crearMano() {
        HBox mano = new HBox(4);
        mano.setAlignment(Pos.CENTER);
        mano.setPadding(new Insets(8));
        for (Carta carta : local.getMano().getCartas()) {
            VistaCarta vistaCarta = new VistaCarta(carta, ANCHO_EN_MANO);
            vistaCarta.setOnMouseClicked(e -> oyente.alElegirCarta(carta));
            mano.getChildren().add(vistaCarta);
        }
        return mano;
    }
}