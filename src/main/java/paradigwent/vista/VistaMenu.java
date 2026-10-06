package paradigwent.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.InputStream;

public class VistaMenu {

    private static final String RUTA_PORTADA = "/imagenes/portada.png";

    private final StackPane raiz = new StackPane();

    public VistaMenu(Runnable alIniciar, Runnable alSalir) {

        // Botón para iniciar el juego
        Button iniciar = new Button("Iniciar juego");
        iniciar.setOnAction(e -> alIniciar.run());

        // Botón para salir
        Button salir = new Button("Salir");
        salir.setOnAction(e -> alSalir.run());

        // Tamaño de los botones
        iniciar.setPrefWidth(220);
        iniciar.setPrefHeight(60);

        salir.setPrefWidth(220);
        salir.setPrefHeight(60);

        // Tamaño de la letra
        iniciar.setStyle("-fx-font-size: 20px;");
        salir.setStyle("-fx-font-size: 20px;");

        // Contenedor de los botones
        VBox botones = new VBox(15, iniciar, salir);
        botones.setAlignment(Pos.CENTER);

        // Ubicar los botones en el centro y desplazarlos hacia abajo
        StackPane.setAlignment(botones, Pos.CENTER);
        StackPane.setMargin(botones, new Insets(300, 0, 0, 0));

        // Fondo
        raiz.setStyle("-fx-background-color: #222222;");

        // Agregamos primero la imagen y después los botones.
        // Como StackPane trabaja por capas, los botones quedan encima.
        raiz.getChildren().addAll(
                crearPortada(),
                botones
        );
    }

    private Node crearPortada() {

        InputStream stream = getClass()
                .getResourceAsStream(RUTA_PORTADA);

        if (stream == null) {
            System.out.println("No se encontró la imagen: " + RUTA_PORTADA);
            return new Region();
        }

        Image imagen = new Image(stream);

        ImageView portada = new ImageView(imagen);

        portada.setPreserveRatio(true);
        portada.setFitWidth(1024);

        return portada;
    }

    public Parent getRaiz() {
        return raiz;
    }
}