package view.cell;

import model.Direction;
import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.ExclusivePlumberProductEnd;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ExclusivePlumberProductEndWidget extends PlumberProductProductEndWidget{

    public ExclusivePlumberProductEndWidget(ExclusivePlumberProductEnd plumberProductEnd) {
        super(plumberProductEnd);
    }

    @Override
    protected BufferedImage getImage() throws IOException {
        BufferedImage image = super.getImage();

        BufferedImage image1;
        image1 = new BufferedImage(CellWidget.CELL_SIZE, CellWidget.CELL_SIZE, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = image1.createGraphics();
        graphics2D.setColor(Color.black);
        graphics2D.setFont(new Font("serif", Font.PLAIN, 15));
        graphics2D.drawString(plumberProductEnd().exclusiveMaterial().toString(), CellWidget.CELL_SIZE/2 - 10, 10);

        double angle = 0;

        if(plumberProductEnd().direction().equals(Direction.south())){
            angle = Math.PI;
        }
        else if (plumberProductEnd().direction().equals(Direction.west())){
            angle = 3*Math.PI/2;
        }
        else if (plumberProductEnd().direction().equals(Direction.east())){
            angle = Math.PI/2;
        }

        System.out.println(plumberProductEnd().exclusiveMaterial().toString());
        image1 = ImageUtils.rotateClockwise(image1, angle);
        image = ImageUtils.overlayImage(image, image1);

        return image;
    }


    @Override
    public ExclusivePlumberProductEnd plumberProductEnd() {
        return (ExclusivePlumberProductEnd) super.plumberProductEnd();
    }
}
