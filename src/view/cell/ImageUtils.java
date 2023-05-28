package view.cell;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtils {

    private  ImageUtils(){};

    protected static BufferedImage rotateClockwise(BufferedImage src, double radian) {
        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage dest = new BufferedImage(height, width, src.getType());

        Graphics2D graphics2D = dest.createGraphics();
        graphics2D.translate((height - width) / 2, (height - width) / 2);
        graphics2D.rotate(radian, height / 2, width / 2);
        graphics2D.drawRenderedImage(src, null);

        return dest;
    }

    public static void changeColor(BufferedImage img, Color old, Color update) {
        final int oldRGB = old.getRGB();
        final int newRGB = update.getRGB();
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                if (img.getRGB(x, y) == oldRGB)
                    img.setRGB(x, y, newRGB);
            }
        }
    }

    public static BufferedImage overlayImage(BufferedImage img1, BufferedImage img2){

        BufferedImage resultImg = new BufferedImage(80,80,BufferedImage.TYPE_INT_ARGB);
        resultImg.getGraphics().drawImage(img1, 0, 0, null);
        resultImg.getGraphics().drawImage(img2, 0, 0, null);

        return  resultImg;
    }
}
