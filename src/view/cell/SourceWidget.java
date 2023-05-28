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

        File file = new File(getFullPath());
        System.out.println();
        BufferedImage image = ImageIO.read(file);

        BufferedImage img1 = new MiniPlumberProductEndWidget(plumberProduct().getEndsList().get(0)).getImage(isFilled());

        BufferedImage resultImg = ImageUtils.overlayImage(image, img1);

        return resultImg;
    }

    @Override
    protected String getPath() {
        return "image/";
    }

    @Override
    protected String getFileName() {

       return  "source.png";
    }

}
