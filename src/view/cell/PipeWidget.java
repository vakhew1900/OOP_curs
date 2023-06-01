package view.cell;

import model.plumber_product.Pipe;
import model.plumber_product_end.PlumberProductEnd;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PipeWidget extends PlumberProductWidget {

    public PipeWidget(@NotNull Pipe pipe) {
        super(pipe);
        addMouseListener(new MyMouseListener());
        setToolTipText("Повернуть");
    }

    @Override
    protected BufferedImage getImage() throws IOException {

        BufferedImage img1 = new PlumberProductProductEndWidget(plumberProduct().getEndsList().get(0)).getImage(isFilled());
        BufferedImage img2 = new PlumberProductProductEndWidget(plumberProduct().getEndsList().get(1)).getImage(isFilled());

        BufferedImage resultImg = ImageUtils.overlayImage(img1, img2);
        resultImg = ImageUtils.overlayImage(resultImg, super.getImage());

        return resultImg;
    }



    @Override
    protected String getFileName() {
        return "pipe_center.png";
    }

    @Override
    public Pipe plumberProduct() {
        return (Pipe) super.plumberProduct();
    }

    /**
     * Обработчик работы с мышью
     */
    private class MyMouseListener extends MouseAdapter {

        /**
         * Обработчик события нажатия кнопки
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            plumberProduct().rotate();
            repaint();
        }

    }
}
