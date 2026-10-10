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
import javafx.util.Duration;
import paradigwent.modelo.Carta;

import java.io.InputStream;

public class VistaCarta extends StackPane {

    private static final double ANCHO_POR_DEFECTO = 180;
    private static final double ANCHO_MINIMO_PARA_NOMBRE = 70;

    private static final String FONDO_FUERZA_NORMAL = "rgba(0, 0, 0, 0.75)";
    private static final String FONDO_FUERZA_AUMENTADA = "rgba(30, 130, 50, 0.9)";
    private static final String FONDO_FUERZA_REDUCIDA = "rgba(170, 40, 40, 0.9)";

    private final Carta carta;
    private final double ancho;
    private final ImageView imagenCarta;
    private final Label etiquetaFuerza;
    private final Tooltip tooltip;

    public VistaCarta(Carta carta) {
        this(carta, ANCHO_POR_DEFECTO);
    }

    public VistaCarta(Carta carta, double ancho) {

        this.carta = carta;
        this.ancho = ancho;

        imagenCarta = cargarImagen(carta.getImagen());
        imagenCarta.setPreserveRatio(true);
        imagenCarta.setFitWidth(ancho);

        etiquetaFuerza = crearEtiquetaFuerza();
        tooltip = crearTooltip();

        setAlignment(Pos.CENTER);
        // el panel mide lo mismo que la imagen (si no, un HBox lo estira a lo alto)
        setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        getChildren().add(imagenCarta);
        if (ancho >= ANCHO_MINIMO_PARA_NOMBRE) {
            getChildren().add(crearEtiquetaNombre());
        }
        getChildren().add(etiquetaFuerza);

        // Al pasar el mouse se explica que hace la carta
        Tooltip.install(this, tooltip);

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

    /**
     * Para cartas que estan en una fila del tablero: muestra la fuerza que
     * realmente tienen (con clima y duplicaciones) en vez de la fuerza base.
     * Verde si subio, rojo si bajo.
     */
    public void mostrarFuerzaReal(int fuerzaReal, int fuerzaBase) {
        etiquetaFuerza.setText(String.valueOf(fuerzaReal));
        etiquetaFuerza.setStyle(estiloFuerza(fondoSegun(fuerzaReal, fuerzaBase)));
        tooltip.setText(textoDelTooltip(
                "\nFuerza actual: " + fuerzaReal + " (base " + fuerzaBase + ")"));
    }

    private String fondoSegun(int fuerzaReal, int fuerzaBase) {
        if (fuerzaReal > fuerzaBase) {
            return FONDO_FUERZA_AUMENTADA;
        }
        if (fuerzaReal < fuerzaBase) {
            return FONDO_FUERZA_REDUCIDA;
        }
        return FONDO_FUERZA_NORMAL;
    }

    private Tooltip crearTooltip() {
        Tooltip nuevo = new Tooltip(textoDelTooltip(""));
        nuevo.setShowDelay(Duration.millis(200));
        nuevo.setWrapText(true);
        nuevo.setMaxWidth(260);
        nuevo.setStyle("-fx-font-size: 12px;");
        return nuevo;
    }

    private String textoDelTooltip(String extra) {
        return carta.getNombre() + "\n" + carta.descripcion() + extra;
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

        Label fuerza = new Label(texto);
        fuerza.setMaxWidth(Region.USE_PREF_SIZE);
        fuerza.setMaxHeight(Region.USE_PREF_SIZE);
        fuerza.setMinWidth(tamanioLetraFuerza() * 1.9);
        fuerza.setAlignment(Pos.CENTER);
        fuerza.setStyle(estiloFuerza(FONDO_FUERZA_NORMAL));
        fuerza.setVisible(!texto.isEmpty());   // efectos y climas no tienen fuerza
        StackPane.setAlignment(fuerza, Pos.TOP_LEFT);
        StackPane.setMargin(fuerza, new Insets(4));
        return fuerza;
    }

    private int tamanioLetraFuerza() {
        return (int) Math.max(10, Math.round(ancho * 0.1));
    }

    private String estiloFuerza(String colorDeFondo) {
        return "-fx-background-color: " + colorDeFondo + ";"
                + "-fx-background-radius: 17;"
                + "-fx-text-fill: white;"
                + "-fx-font-size: " + tamanioLetraFuerza() + "px;"
                + "-fx-font-weight: bold;"
                + "-fx-padding: 2 5 2 5;";
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