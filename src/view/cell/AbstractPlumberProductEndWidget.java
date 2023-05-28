package view.cell;

import model.Direction;
import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class AbstractPlumberProductEndWidget extends CellItemWidget{

    private AbstractPlumberProductEnd plumberProductEnd;

    public AbstractPlumberProductEndWidget(AbstractPlumberProductEnd plumberProductEnd){
        this.plumberProductEnd = plumberProductEnd;
    }

    @Override
    protected String getPath() {
        return "image/";
    }


    @Override
    protected BufferedImage getImage() throws IOException {

        File file = new File(getFullPath());
        System.out.println(getFullPath());
        BufferedImage image = ImageIO.read(file);

        double angle = 0;

        if(plumberProductEnd.direction().equals(Direction.south())){
            angle = Math.PI;
        }
        else if (plumberProductEnd.direction().equals(Direction.west())){
            angle = 3*Math.PI/2;
        }
        else if (plumberProductEnd.direction().equals(Direction.east())){
            angle = Math.PI/2;
        }

        image = ImageUtils.rotateClockwise(image, angle);

        if(plumberProductEnd instanceof PlumberProductEnd)
            ImageUtils.changeColor(image, new Color(217, 217, 217), ((PlumberProductEnd) plumberProductEnd).material().color());

        return image;
    }

    public  BufferedImage getImage(boolean isFilled) throws IOException {
        BufferedImage img = this.getImage();

        if(isFilled){
            Color color = new Color(217, 217, 217);
            Color waterColor = new Color(5, 225, 225);
            if (plumberProductEnd instanceof  PlumberProductEnd){
                color = ((PlumberProductEnd) plumberProductEnd).material().color();
            }

            ImageUtils.changeColor(img, color, waterColor );
        }

        return img;
    }

    public  AbstractPlumberProductEnd plumberProductEnd(){
        return plumberProductEnd;
    }
}