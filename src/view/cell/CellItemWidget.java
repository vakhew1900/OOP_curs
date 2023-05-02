package view.cell;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class CellItemWidget extends JPanel {


    private Color CELL_COLOR = Color.decode("#70FE19");
     public  CellItemWidget(){
         setPreferredSize(new Dimension(80, 80));
         setOpaque(false);
     }
    protected abstract BufferedImage getImage() throws IOException;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(getImage(), 0, -5, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    protected  abstract String getPath();

    protected abstract String getFileName();

    protected String getFullPath(){
        return getPath() + getFileName();
    }
}
