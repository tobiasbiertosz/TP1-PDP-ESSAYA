package paradigwent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import paradigwent.controlador.ControladorPartida;
import paradigwent.persistencia.CargadorDeFacciones;
import paradigwent.persistencia.ErrorDeCargaException;
import paradigwent.modelo.FabricaDePartida;
import paradigwent.modelo.Faccion;
import paradigwent.modelo.Partida;
import paradigwent.vista.VistaMenu;


import java.util.List;
import java.util.Random;

public class Main extends Application {

    private static final String ARCHIVO_CARTAS = "/mazos/cartas_juego.xml";

    private Scene escena;

    @Override
    public void start(Stage stage) {

        escena = new Scene(new VBox(), 1024, 680);

        mostrarMenu();

        stage.setTitle("Paradigwent");
        stage.setScene(escena);
        stage.show();
    }

    private void mostrarMenu() {

        VistaMenu menu = new VistaMenu(
                this::mostrarJuego,
                Platform::exit
        );

        escena.setRoot(menu.getRaiz());
    }

    private void mostrarJuego() {

        List<Faccion> facciones;
        try {
            facciones = new CargadorDeFacciones().cargar(ARCHIVO_CARTAS);
        } catch (ErrorDeCargaException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            return;
        }

        Partida partida = new FabricaDePartida().crear(facciones, new Random());
        ControladorPartida controlador = new ControladorPartida(partida, this::mostrarMenu);

        escena.setRoot(controlador.getRaiz());
        controlador.iniciar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}