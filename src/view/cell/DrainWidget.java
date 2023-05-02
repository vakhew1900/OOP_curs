package view.cell;

import model.Direction;
import model.plumber_product.Drain;
import model.plumber_product.PlumbingProduct;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrainWidget extends PlumberProductWidget{

    private Drain drain;
    public DrainWidget(@NotNull Drain drain){
        super();
        this.drain = drain;
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

        if (drain.hasEnd(Direction.west())){
            fileName = "Drain_west_80.png";
        }

        if (drain.hasEnd(Direction.north())){
            fileName = "Drain_north_80.png";
        }
        return fileName;
    }



    @Override
    public PlumbingProduct plumberProduct() {
        return drain;
    }
}
