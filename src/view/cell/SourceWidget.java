package view.cell;

import model.Direction;
import model.plumber_product.PlumbingProduct;
import model.plumber_product.Source;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SourceWidget extends PlumberProductWidget{

    Source source;

    public SourceWidget(@NotNull Source source){
        super();
        this.source = source;
    }
    @Override
    protected BufferedImage getImage() throws IOException {

        BufferedImage image = null;
        try {

            String imagePath = getFullPath();
            File file = new File(imagePath);
            image = ImageIO.read(file);
            if (source.hasEnd(Direction.north())) {
                image = rotateClockwise(image, Math.PI);
            }

            return image;

        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return image;
    }

    @Override
    protected String getPath() {
        return "images/source/";
    }

    @Override
    protected String getFileName() {

        String fileName = "source_south_80.png";

        if(plumberProduct().hasEnd(Direction.west())){
            fileName = "source_east_80.png";
        }

        return fileName;
    }




    @Override
    public PlumbingProduct plumberProduct() {
        return source;
    }


}