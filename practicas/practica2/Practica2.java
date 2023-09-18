import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Práctica 2 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */
public class Practica2 {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    /* Ruta por defecto. */
    String ruta = "./imagenes/foto.jpg"; 

    /* Si se desea utilizar alguna otra imagen, verificamos si el tamaño del arreglo es mayor que 0. */
    if (args.length > 0) {
      ruta = args[0];
      System.out.println("Utilizando imagen proporcionada para aplicar filtros. \"" + ruta + "\"");
    }

    FiltrosImagen filtrosImagen = new FiltrosImagen();

    System.out.println("Aplicando filtros a la imagen \"" + ruta + "\"");
    BufferedImage imagen = null;
    try {
      imagen = ImageIO.read(new File(ruta));
    } catch (IOException ioe) {
      System.out.println("No se puede leer la imagen \"" + ruta + "\"");
      System.exit(1);
    }
    /* Utilizará el nombre de la imagen para nombrar a las imagenes con filtro. */ 
    String nombreArchivo = ruta.substring(ruta.lastIndexOf("/") == -1 ? 0 : ruta.lastIndexOf("/"), ruta.lastIndexOf("."));
    int i = 0;
    filtrosImagen.aplicarFiltro(imagen, "./imagenes/" + nombreArchivo + ++i + ".jpg", "escalaDeGrises");
    filtrosImagen.aplicarFiltro(imagen, "./imagenes/" + nombreArchivo + ++i + ".jpg", "rojo");
    filtrosImagen.aplicarFiltro(imagen, "./imagenes/" + nombreArchivo + ++i + ".jpg", "verde");
    filtrosImagen.aplicarFiltro(imagen, "./imagenes/" + nombreArchivo + ++i + ".jpg", "antiguo");
  }
}

