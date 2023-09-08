
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.IOException;

/**
 *
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */
public class FiltrosImagen {

    /**
     * Colorea la imagen recibida a escala de grises y la guarda en una ruta especificada.
     * @param original Imagen original.
     * @param rutaSalida Ruta donde se guardará la imagen modificada.
     */
    public void escalaDeGrises(BufferedImage original, String rutaSalida) {
        BufferedImage nuevaImagen = new BufferedImage(original.getWidth(), 
                original.getHeight(), 
                BufferedImage.TYPE_3BYTE_BGR);

        Color color;
        int variacion;
        for (int i = 0; i < original.getHeight(); i++) {
            for (int j = 0; j < original.getWidth(); j++) {
                color = new Color(original.getRGB(j, i));
                variacion = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                nuevaImagen.setRGB(j, i, new Color(variacion, variacion, variacion).getRGB());
            }
        }
        escribirImagen(nuevaImagen, rutaSalida);
    }

    public void rojo(BufferedImage original, String rutaSalida) {
        BufferedImage nuevaImagen = new BufferedImage(original.getWidth(),
                original.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);

        Color color;
        for (int i = 0; i < original.getHeight(); i++) {
            for (int j = 0; j < original.getWidth(); j++) {
                color = new Color(original.getRGB(j, i));
                nuevaImagen.setRGB(j, i, new Color(color.getRed(), 0, 0).getRGB());
            }
        }
        escribirImagen(nuevaImagen, rutaSalida);
    }

    public void verde(BufferedImage original, String rutaSalida) {
        BufferedImage nuevaImagen = new BufferedImage(original.getWidth(),
                original.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);

        Color color;
        for (int i = 0; i < original.getHeight(); i++) {
            for (int j = 0; j < original.getWidth(); j++) {
                color = new Color(original.getRGB(j, i));
                nuevaImagen.setRGB(j, i, new Color(0, color.getGreen(), 0).getRGB());
            }
        }
        escribirImagen(nuevaImagen, rutaSalida);
    }

    public void antiguo(BufferedImage original, String rutaSalida) {
        BufferedImage nuevaImagen = new BufferedImage(original.getWidth(),
                original.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);

        Color color;
        int rojo, verde, azul;
        for (int i = 0; i < original.getHeight(); i++) {
            for (int j = 0; j < original.getWidth(); j++) {
                color = new Color(original.getRGB(j, i));
                rojo = (int) ((color.getRed() * .393) + (color.getGreen() * .769)
                        + (color.getBlue() * .189));
                verde = (int) ((color.getRed() * .349) + (color.getGreen() * .686)
                        + (color.getBlue() * .168));
                azul = (int) ((color.getRed() * .272) + (color.getGreen() * .534)
                        + (color.getBlue() * .131));

                if (rojo > 255) rojo = 255;
                if (verde > 255) verde = 255;
                if (azul > 255) azul = 255;
                nuevaImagen.setRGB(j, i, new Color(rojo, verde, azul).getRGB());
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
