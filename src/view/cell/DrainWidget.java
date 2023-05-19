package view.cell;

import model.Direction;
import model.plumber_product.Drain;
import model.plumber_product.PlumbingProduct;
import model.plumber_product_end.SimplePlumberProductEnd;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrainWidget extends PlumberProductWidget{

    public DrainWidget(@NotNull Drain drain){
        super(drain);
    }
    @Override
    protected BufferedImage getImage() throws IOException {

        BufferedImage image = null;
        try {
            String basePath = new File(getFullPath()).getAbsolutePath();
            System.out.println(basePath);
            System.out.println(getFullPath());
            File file = new File(basePath);
            image = ImageIO.read(file);

        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        return image;
    }

    @Override
    protected String getPath() {

        String path = "images/unfilled/";

        if(isFilled()){
            path = "images/filled/";
        }

        return path;
    }

    @Override
    protected String getFileName() {

        String fileName = "Drain_south_80.png";

        if (plumberProduct().hasEnd(Direction.west())){
            fileName = "Drain_west_80.png";
        }

        if (plumberProduct().hasEnd(Direction.north())){
            fileName = "Drain_north_80.png";
        }
        return fileName;
    }

}
