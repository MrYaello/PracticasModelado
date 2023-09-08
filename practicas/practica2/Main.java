
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Aplicando filtros a la imagen ./imagenes/foto.jpg");
        FiltrosImagen filtrosImagen = new FiltrosImagen();

        BufferedImage imagen = null;
        try {
            imagen = ImageIO.read(new File("./imagenes/foto.jpg"));
        } catch (Exception e){
            System.out.println("No se puede leer la imagen.");
        }

        filtrosImagen.escalaDeGrises(imagen, "./imagenes/i1.jpg");
        filtrosImagen.rojo(imagen, "./imagenes/i2.jpg");
        filtrosImagen.verde(imagen, "./imagenes/i3.jpg");
        filtrosImagen.antiguo(imagen, "./imagenes/i4.jpg");
    }
}

