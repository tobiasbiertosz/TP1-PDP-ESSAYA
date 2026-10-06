package paradigwent.modelo.vista;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.InputStream;

public class VistaCarta extends StackPane {

    private final ImageView imagenCarta;

    private static final double ANCHO_CARTA = 120;

    public VistaCarta(String rutaImagen) {

        imagenCarta = cargarImagen(rutaImagen);

        imagenCarta.setPreserveRatio(true);
        imagenCarta.setFitWidth(ANCHO_CARTA);

        setAlignment(Pos.CENTER);

        getChildren().add(imagenCarta);

        // Hace que parezca que la carta se puede seleccionar
        setCursor(Cursor.HAND);

        // Efecto visual cuando pasamos el mouse por encima
        setOnMouseEntered(e -> {
            setScaleX(1.08);
            setScaleY(1.08);
        });

        setOnMouseExited(e -> {
            setScaleX(1.0);
            setScaleY(1.0);
        });
    }

    private ImageView cargarImagen(String rutaImagen) {

        InputStream stream = getClass()
                .getResourceAsStream(rutaImagen);

        if (stream == null) {
            System.out.println(
                    "No se encontró la imagen de la carta: "
                            + rutaImagen
            );

            return new ImageView();
        }

        Image imagen = new Image(stream);

        return new ImageView(imagen);
    }

    public ImageView getImagenCarta() {
        return imagenCarta;
    }
}
