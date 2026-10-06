package paradigwent.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextAlignment;
import paradigwent.modelo.Carta;

import java.io.InputStream;

public class VistaCarta extends StackPane {

    private static final double ANCHO_POR_DEFECTO = 180;
    private static final double ANCHO_MINIMO_PARA_NOMBRE = 70;

    private final Carta carta;
    private final double ancho;
    private final ImageView imagenCarta;

    public VistaCarta(Carta carta) {
        this(carta, ANCHO_POR_DEFECTO);
    }

    public VistaCarta(Carta carta, double ancho) {

        this.carta = carta;
        this.ancho = ancho;

        imagenCarta = cargarImagen(carta.getImagen());
        imagenCarta.setPreserveRatio(true);
        imagenCarta.setFitWidth(ancho);

        setAlignment(Pos.CENTER);
        // el panel mide lo mismo que la imagen (si no, un HBox lo estira a lo alto)
        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        getChildren().add(imagenCarta);
        if (ancho >= ANCHO_MINIMO_PARA_NOMBRE) {
            getChildren().add(crearEtiquetaNombre());
        } else {
            Tooltip.install(this, new Tooltip(carta.getNombre()));
        }
        getChildren().add(crearEtiquetaFuerza());

        // Cursor de selección
        setCursor(Cursor.HAND);

        // Efecto al pasar el mouse
        setOnMouseEntered(e -> {
            setScaleX(1.08);
            setScaleY(1.08);
        });

        setOnMouseExited(e -> {
            setScaleX(1.0);
            setScaleY(1.0);
        });
    }

    private Label crearEtiquetaNombre() {
        int tamanioLetra = ancho >= 120 ? 12 : 9;

        Label nombre = new Label(carta.getNombre());
        nombre.setMaxWidth(ancho);
        nombre.setWrapText(true);
        nombre.setTextAlignment(TextAlignment.CENTER);
        nombre.setAlignment(Pos.CENTER);
        nombre.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.65);"
                        + "-fx-text-fill: white;"
                        + "-fx-font-weight: bold;"
                        + "-fx-font-size: " + tamanioLetra + "px;"
                        + "-fx-padding: 3 4 3 4;"
        );
        StackPane.setAlignment(nombre, Pos.BOTTOM_CENTER);
        return nombre;
    }

    private Label crearEtiquetaFuerza() {
        String texto = carta.textoDeFuerza();
        int tamanioLetra = (int) Math.max(10, Math.round(ancho * 0.1));

        Label fuerza = new Label(texto);
        fuerza.setMaxWidth(Region.USE_PREF_SIZE);
        fuerza.setMaxHeight(Region.USE_PREF_SIZE);
        fuerza.setMinWidth(tamanioLetra * 1.9);
        fuerza.setAlignment(Pos.CENTER);
        fuerza.setStyle(
                "-fx-background-color: rgba(0, 0, 0, 0.75);"
                        + "-fx-background-radius: 17;"
                        + "-fx-text-fill: white;"
                        + "-fx-font-size: " + tamanioLetra + "px;"
                        + "-fx-font-weight: bold;"
                        + "-fx-padding: 2 5 2 5;"
        );
        fuerza.setVisible(!texto.isEmpty());   // efectos y climas no tienen fuerza
        StackPane.setAlignment(fuerza, Pos.TOP_LEFT);
        StackPane.setMargin(fuerza, new Insets(4));
        return fuerza;
    }

    private ImageView cargarImagen(String rutaImagen) {

        InputStream stream = getClass()
                .getResourceAsStream(rutaImagen);

        if (stream == null) {

            System.out.println(
                    "No se encontró la imagen: " + rutaImagen
            );

            return new ImageView();
        }

        Image imagen = new Image(stream);

        return new ImageView(imagen);
    }

    public Carta getCarta() {
        return carta;
    }

    public ImageView getImagenCarta() {
        return imagenCarta;
    }
}