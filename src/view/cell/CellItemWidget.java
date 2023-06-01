package view.cell;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class CellItemWidget extends JPanel {

    /**
     * Конструктор
     */
     public  CellItemWidget(){
         setPreferredSize(new Dimension(CellWidget.CELL_SIZE, CellWidget.CELL_SIZE));
         setOpaque(false);
     }

    /**
     * Получить изображение CellItem
     * @return - изображение CellItem
     * @throws IOException
     */
    protected  BufferedImage getImage() throws IOException{
        File file = new File(getFullPath());
        BufferedImage image = ImageIO.read(file);
        return image;
    }

    /**
     * Нарисовать виджет
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            g.drawImage(getImage(), 0, 0, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Получить путь до картинки
     * @return путь до картники в виде строки
     */
    protected  abstract String getPath();

    /**
     * Получить имя для картинки
     * @return имя картинки
     */
    protected abstract String getFileName();

    /**
     * Получить полный путь до картинки с учетом путя и имени файла
     * @return полный путь до картинки
     */
    protected String getFullPath(){
        return getPath() + getFileName();
    }
}
