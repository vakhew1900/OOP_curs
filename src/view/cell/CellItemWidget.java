package view.cell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class CellItemWidget extends JPanel {

    private static final int CELL_SIZE = 80;
     public  CellItemWidget(){
         setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
         setOpaque(false);
     }
    protected  BufferedImage getImage() throws IOException{
        File file = new File(getFullPath());
        BufferedImage image = ImageIO.read(file);
        return image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(getImage(), 0, 0, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    protected  abstract String getPath();

    protected abstract String getFileName();

    protected String getFullPath(){
        return getPath() + getFileName();
    }
}
