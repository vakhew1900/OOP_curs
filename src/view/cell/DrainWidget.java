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
        return "drain.png";
    }

}
