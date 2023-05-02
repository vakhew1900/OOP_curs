package view.cell;

import model.Direction;
import model.plumber_product.Pipe;
import model.plumber_product.PlumbingProduct;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PipeWidget extends PlumberProductWidget {

    Pipe pipe;
    public PipeWidget(@NotNull Pipe pipe) {
        this.pipe = pipe;
    }

    @Override
    protected BufferedImage getImage() throws IOException {
        BufferedImage image = null;
        try {

            String imagePath = getFullPath();
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

        return path;
    }

    @Override
    protected String getFileName() {

        String fileName = "angular_pipe_80.png";

        if (plumberProduct().hasEnd(Direction.north()) && plumberProduct().hasEnd(Direction.south())) {
            fileName = "straight_pipe_80.png";
        }

        if (plumberProduct().hasEnd(Direction.east()) && plumberProduct().hasEnd(Direction.west())) {
            fileName = "straight_pipe_80.png";
        }

        return fileName;
    }

    @Override
    public PlumbingProduct plumberProduct() {
        return pipe;
    }
}
