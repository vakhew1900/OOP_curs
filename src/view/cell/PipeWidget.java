package view.cell;

import model.Direction;
import model.plumber_product.Pipe;
import model.plumber_product.PlumbingProduct;
import model.plumber_product_end.SimplePlumberProductEnd;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        BufferedImage image = null;
        try {

            String imagePath = getFullPath();
            File file = new File(imagePath);
            image = ImageIO.read(file);

            SimplePlumberProductEnd eastPlumberProduct = new SimplePlumberProductEnd(Direction.east());
            SimplePlumberProductEnd westPlumberProduct = new SimplePlumberProductEnd(Direction.west());
            SimplePlumberProductEnd northPlumberProduct = new SimplePlumberProductEnd(Direction.north());
            SimplePlumberProductEnd southPlumberProduct = new SimplePlumberProductEnd(Direction.south());

            if (plumberProduct().hasEnd(eastPlumberProduct) && plumberProduct().hasEnd(westPlumberProduct)) {
                image = rotateClockwise(image, Math.PI/2);
            }

            if (plumberProduct().hasEnd(eastPlumberProduct) && plumberProduct().hasEnd(southPlumberProduct)){
                image = rotateClockwise(image, Math.PI/2);
            }

            if(plumberProduct().hasEnd(southPlumberProduct) && plumberProduct().hasEnd(eastPlumberProduct)){
                image = rotateClockwise(image, Math.PI);
            }

            if(plumberProduct().hasEnd(westPlumberProduct) && plumberProduct().hasEnd(northPlumberProduct)){
                image = rotateClockwise(image, Math.PI*3/2);
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return image;
    }

    @Override
    protected String getPath() {

        String path = "images/unfilled/";

        if (isFilled()) {
            path = "images/filled/";
        }

        return path;
    }

    @Override
    protected String getFileName() {

        String fileName = "angular_pipe_80.png";

        SimplePlumberProductEnd eastPlumberProduct = new SimplePlumberProductEnd(Direction.east());
        SimplePlumberProductEnd westPlumberProduct = new SimplePlumberProductEnd(Direction.west());
        SimplePlumberProductEnd northPlumberProduct = new SimplePlumberProductEnd(Direction.north());
        SimplePlumberProductEnd southPlumberProduct = new SimplePlumberProductEnd(Direction.south());


        if (plumberProduct().hasEnd(northPlumberProduct) && plumberProduct().hasEnd(southPlumberProduct)) {
            fileName = "straight_pipe_80.png";
        }

        if (plumberProduct().hasEnd(eastPlumberProduct) && plumberProduct().hasEnd(westPlumberProduct)) {
            fileName = "straight_pipe_80.png";
        }

        return fileName;
    }


    private class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            ((Pipe) plumberProduct()).rotate();
            repaint();
        }

    }
}
