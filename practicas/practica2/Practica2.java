
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */
public class Practica2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ruta = "./imagenes/foto.jpg";
        int i = 0;
        FiltrosImagen filtrosImagen = new FiltrosImagen();

        System.out.println("Aplicando filtros a la imagen \"" + ruta + "\"");

        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File(ruta));
        } catch (IOException ioe) {
            System.out.println("No se puede leer la imagen \"" + ruta + "\"");
        }

        String nombreArchivo = ruta.substring(ruta.lastIndexOf("/") == -1 ? 0 : ruta.lastIndexOf("/"), ruta.lastIndexOf("."));
        filtrosImagen.escalaDeGrises(imagen, "./imagenes/" + nombreArchivo + ++i + ".jpg");
        filtrosImagen.rojo(imagen, "./imagenes/" + nombreArchivo + ++i + ".jpg");
        filtrosImagen.verde(imagen, "./imagenes/" + nombreArchivo + ++i + ".jpg");
        filtrosImagen.antiguo(imagen, "./imagenes/" + nombreArchivo + ++i + ".jpg");
    }
}

