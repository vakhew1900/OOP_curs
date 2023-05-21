package view.cell;

import model.Direction;
import model.plumber_product.PlumbingProduct;
import model.plumber_product.Source;
import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;
import model.plumber_product_end.SimplePlumberProductEnd;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SourceWidget extends PlumberProductWidget{

    public SourceWidget(@NotNull Source source){
        super(source);
    }
    @Override
    protected BufferedImage getImage() throws IOException {

        BufferedImage image = null;
        try {

            String imagePath = getFullPath();
            System.out.println(getFullPath());
            File file = new File(imagePath);
            image = ImageIO.read(file);
        }
        catch (IOException ex){
            System.out.println(getPath());
            String fullPath = getFullPath();
            ex.printStackTrace();
        }

        return image;
    }

    @Override
    protected String getPath() {

        String path = "images/source/";
        AbstractPlumberProductEnd plumberProductEnd = plumberProduct().getEndsList().get(0);
        String tmp = "big/";

        if (plumberProductEnd instanceof PlumberProductEnd
                && ((PlumberProductEnd) plumberProductEnd).diameter() == PlumberProductEnd.SMALL_DIAMETER){
            tmp = "small/";
        }

        path += tmp;

        return path;
    }

    @Override
    protected String getFileName() {

        String fileName = "source_south.png";
        if(plumberProduct().hasEnd(Direction.east())){
            fileName = "source_east.png";
        }

        if (plumberProduct().hasEnd(Direction.north())){
            System.out.println("fff");
            fileName = "source_north.png";
        }

        String materialString = "metal";
        AbstractPlumberProductEnd plumberProductEnd = plumberProduct().getEndsList().get(0);
        if (plumberProduct().getEndsList().get(0) instanceof PlumberProductEnd){
            materialString =  ((PlumberProductEnd) plumberProductEnd).material().toString();
        }

        fileName = materialString + "_" + fileName;
        System.out.println("fileName" + plumberProduct() + "---" + fileName);
        return fileName;
    }

}
