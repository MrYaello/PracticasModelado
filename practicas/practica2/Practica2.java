
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Color;

/**
 *
 * @author Sango
 */
public class Practica2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hola Mundo");

        BufferedImage i = null;

        try{
            i = ImageIO.read(new File("imagenes/foto.jpg"));
        } catch (Exception e){

        }


                BufferedImage nI = new BufferedImage(i.getWidth(),
                i.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        Color c;
        int p;


        for (int w = 0; w < i.getHeight(); w++) {
            for (int z = 0; z < i.getWidth(); z++) {

                c = new Color(i.getRGB(z, w));

                p = (c.getRed() + c.getGreen()
                        + c.getBlue()) / 3;
                nI.setRGB(z, w,
                        new Color(p, p, p).getRGB());
            }
        }

      
        try {

            ImageIO.write(nI, "jpg", new File("imagenes/i1.jpg"));

        } catch (Exception e) {

        }


                        BufferedImage nI2 = new BufferedImage(i.getWidth(),
                i.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        Color c2;


        for (int w = 0; w < i.getHeight(); w++) {
            for (int z = 0; z < i.getWidth(); z++) {

                c2 = new Color(i.getRGB(z, w));

                nI2.setRGB(z, w,
                        new Color(c2.getRed(), 0, 0).getRGB());
            }
        }

      
        try {

            ImageIO.write(nI2, "jpg", new File("imagenes/i2.jpg"));

        } catch (Exception e) {

        }



                                BufferedImage nI3 = new BufferedImage(i.getWidth(),
                i.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        Color c3;


        for (int w = 0; w < i.getHeight(); w++) {
            for (int z = 0; z < i.getWidth(); z++) {

                c3 = new Color(i.getRGB(z, w));

                nI3.setRGB(z, w,
                        new Color(0, c3.getGreen(), 0).getRGB());
            }
        }

      
        try {

            ImageIO.write(nI3, "jpg", new File("imagenes/i3.jpg"));

        } catch (Exception e) {

        }



                BufferedImage nI4 = new BufferedImage(i.getWidth(),
                i.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);

        Color c4;

        int c1;
        int c2a;
        int c3a;

        int c11;
        int c22;
        int c33;

        for (int y = 0; y < i.getHeight(); y++) {
            for (int x = 0; x < i.getWidth(); x++) {

                c4 = new Color(i.getRGB(x, y));

                c1 = c4.getRed();
                c2a = c4.getGreen();
                c3a = c4.getBlue();

                c11 = (int) ((c1 * .393) + (c2a * .769)
                        + (c3a * .189));
                c22 = (int) ((c1 * .349) + (c2a * .686)
                        + (c3a * .168));
                c33 = (int) ((c1 * .272) + (c2a * .534)
                        + (c3a * .131));


                if (c11 > 255) {
                    c11 = 255;
                }

                if (c22 > 255) {
                    c22 = 255;
                }

                if (c33 > 255) {
                    c33 = 255;
                }

                nI4.setRGB(x, y, new Color(c11, c22, c33).getRGB());
            }
        }

        try {

            ImageIO.write(nI4, "jpg", new File("imagenes/i4.jpg"));

        } catch (Exception e) {

        }

    }
}

