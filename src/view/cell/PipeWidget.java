package view.cell;

import model.Direction;
import model.plumber_product.Pipe;
import model.plumber_product.PlumbingProduct;
import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PipeWidget extends PlumberProductWidget {

    public PipeWidget(@NotNull Pipe pipe) {
        super(pipe);
        addMouseListener(new MyMouseListener());
        setToolTipText("Повернуть");
    }

    @Override
    protected BufferedImage getImage() throws IOException {

        BufferedImage img1 = new PlumberProductEndWidget(plumberProduct().getEndsList().get(0)).getImage();
        BufferedImage img2 = new PlumberProductEndWidget(plumberProduct().getEndsList().get(1)).getImage();
        BufferedImage resultImg = new BufferedImage(80,80,BufferedImage.TYPE_INT_ARGB);



        if(isFilled()){
            ImageUtils.changeColor(img1, ((PlumberProductEnd) plumberProduct().getEndsList().get(0)).material().color(), new Color(5, 225, 225));
            ImageUtils.changeColor(img2, ((PlumberProductEnd) plumberProduct().getEndsList().get(1)).material().color(),new Color(5, 225, 225));
        }

        resultImg.getGraphics().drawImage(img1, 0, 0, null);
        resultImg.getGraphics().drawImage(img2, 0, 0, null);

        return resultImg;
    }

    @Override
    protected String getPath() {
        return null;
    }

    @Override
    protected String getFileName() {
        return null;
    }

    @Override
    public Pipe plumberProduct() {
        return (Pipe) super.plumberProduct();
    }

    private class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            plumberProduct().rotate();
            repaint();
        }

    }
}
