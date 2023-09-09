import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.IOException;

/**
 * Práctica 2 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */
public class FiltrosImagen {

  public void aplicarFiltro(BufferedImage original, String rutaSalida, String eleccion) {
    BufferedImage nuevaImagen = new BufferedImage(original.getWidth(),
        original.getHeight(),
        BufferedImage.TYPE_3BYTE_BGR);

    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {
        Color color = new Color(original.getRGB(j, i));

        switch (eleccion) {
          case "escalaDeGrises":
            int variacion = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
            nuevaImagen.setRGB(j, i, new Color(variacion, variacion, variacion).getRGB());
            break;
          case "rojo":
            nuevaImagen.setRGB(j, i, new Color(color.getRed(), 0, 0).getRGB());
            break;
          case "verde":
            nuevaImagen.setRGB(j, i, new Color(0, color.getGreen(), 0).getRGB());
            break;
          case "antiguo":
            int rojo = (int) ((color.getRed() * .393) + (color.getGreen() * .769)
                + (color.getBlue() * .189));
            int verde = (int) ((color.getRed() * .349) + (color.getGreen() * .686)
                + (color.getBlue() * .168));
            int azul = (int) ((color.getRed() * .272) + (color.getGreen() * .534)
                + (color.getBlue() * .131));

            if (rojo > 255) rojo = 255;
            if (verde > 255) verde = 255;
            if (azul > 255) azul = 255;
            nuevaImagen.setRGB(j, i, new Color(rojo, verde, azul).getRGB());
            break;
          default:
            System.out.println("Filtro no válido. No se aplicó ningún filtro.");
            break;
        }
      }
    }

    escribirImagen(nuevaImagen, rutaSalida);
  }

  public void escribirImagen(BufferedImage imagen, String rutaSalida) {
    try {
      ImageIO.write(imagen, "jpg", new File(rutaSalida));
    } catch (IOException ioe) {
      System.out.println("La imagen no puede ser escrita en " + rutaSalida);
    }
  }
}
