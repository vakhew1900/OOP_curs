package view.cell;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class CellItemWidget extends JPanel {


    protected abstract BufferedImage getImage();
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage(), 0, 0, null);
    }

    protected  BufferedImage rotateClockwise(BufferedImage src, double radian) {
        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage dest = new BufferedImage(height, width, src.getType());

        Graphics2D graphics2D = dest.createGraphics();
        graphics2D.translate((height - width) / 2, (height - width) / 2);
        graphics2D.rotate(radian, height / 2, width / 2);
        graphics2D.drawRenderedImage(src, null);

        return dest;
    }
}
