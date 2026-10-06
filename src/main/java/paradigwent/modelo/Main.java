package paradigwent.modelo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import paradigwent.modelo.vista.VistaMenu;

public class Main extends Application {

    private Scene escena;

    @Override
    public void start(Stage stage) {
        escena = new Scene(new VBox(), 1024, 768);
        mostrarMenu();
        stage.setTitle("Paradigwent");
        stage.setScene(escena);
        stage.show();
    }

    private void mostrarMenu() {
        VistaMenu menu = new VistaMenu(this::mostrarJuego, Platform::exit);
        escena.setRoot(menu.getRaiz());
    }

    // PROVISORIO: se reemplaza en la fase 2
    private void mostrarJuego() {
        Button volver = new Button("Rendirse (volver al menú)");
        volver.setOnAction(e -> mostrarMenu());
        VBox pantalla = new VBox(20, new Label("Acá va el juego"), volver);
        pantalla.setAlignment(Pos.CENTER);
        escena.setRoot(pantalla);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
