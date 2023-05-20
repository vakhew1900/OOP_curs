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
            System.out.println(getFullPath());
            File file = new File(imagePath);
            image = ImageIO.read(file);

   
            if (plumberProduct().hasEnd(Direction.east()) && plumberProduct().hasEnd(Direction.west())) {
                image = rotateClockwise(image, Math.PI/2);
            }

            if (plumberProduct().hasEnd(Direction.east()) && plumberProduct().hasEnd(Direction.south())){
                image = rotateClockwise(image, Math.PI/2);
            }

            if(plumberProduct().hasEnd(Direction.south()) && plumberProduct().hasEnd(Direction.west())){
                image = rotateClockwise(image, Math.PI);
            }

            if(plumberProduct().hasEnd(Direction.west()) && plumberProduct().hasEnd(Direction.north())){
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

        if(plumberProduct().isAngular()){
            path += "angular_pipe/";
        }
        else {
            path += "straight_pipe/";
        }


        return path;
    }

    @Override
    protected String getFileName() {

        String fileName = "angular_pipe.png";
        
        if (plumberProduct().isAngular() == false) {
            fileName = "straight_pipe.png";
        }

        return fileName;
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
