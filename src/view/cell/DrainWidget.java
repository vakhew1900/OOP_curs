package view.cell;

import model.Direction;
import model.plumber_product.Drain;
import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;
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

        path += "drain/";

        AbstractPlumberProductEnd  plumberProductEnd = plumberProduct().getEndsList().get(0);

        String tmp = "big/";

        if (plumberProductEnd instanceof PlumberProductEnd
            && ((PlumberProductEnd) plumberProductEnd).diameter() == PlumberProductEnd.SMALL_DIAMETER){
            tmp += "small/";
        }

        return path;
    }

    @Override
    protected String getFileName() {

        String fileName = "Drain_south.png";

        if (plumberProduct().hasEnd(Direction.west())){
            fileName = "Drain_west.png";
        }

        if (plumberProduct().hasEnd(Direction.north())){
            fileName = "Drain_north.png";
        }

        AbstractPlumberProductEnd plumberProductEnd = plumberProduct().getEndsList().get(0);
        String materialString = "metal";

        if(isFilled() == false &&  plumberProductEnd instanceof  PlumberProductEnd ){

            materialString = ((PlumberProductEnd) plumberProductEnd).material().toString();
        }
        fileName = materialString + "_" + fileName;

        return fileName;
    }

}
