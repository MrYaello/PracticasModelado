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
        /**
         * Ruta por defecto
         */
        String ruta = "./imagenes/foto.jpg"; 

        /**
         * Si se desea utulizar alguna otra imagen, verificamos el tamaño del arreglo es >0
         */
        if(args.length>0){
            System.out.println("Tomando imagen proporcionada para aplicar filtros...");
            ruta = args[0];
        }

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
        filtrosImagen.aplicarFiltro(imagen, "./imagenes/" + nombreArchivo + ++i + ".jpg" , "escalaDeGrises");
        filtrosImagen.aplicarFiltro(imagen, "./imagenes/" + nombreArchivo + ++i + ".jpg" , "rojo");
        filtrosImagen.aplicarFiltro(imagen, "./imagenes/" + nombreArchivo + ++i + ".jpg", "verde");
        filtrosImagen.aplicarFiltro(imagen, "./imagenes/" + nombreArchivo + ++i + ".jpg", "antiguo");
    }
}

