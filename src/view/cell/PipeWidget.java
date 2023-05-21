package view.cell;

import model.Direction;
import model.plumber_product.Pipe;
import model.plumber_product.PlumbingProduct;
import model.plumber_product_end.AbstractPlumberProductEnd;
import model.plumber_product_end.PlumberProductEnd;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PipeWidget extends PlumberProductWidget {

    public PipeWidget(@NotNull Pipe pipe) {
        super(pipe);
        addMouseListener(new MyMouseListener());
        setToolTipText("Повернуть");
    }

    @Override
    protected BufferedImage getImage() throws IOException {
        BufferedImage image = null;
        try {

            String imagePath = getFullPath();
            System.out.println(getFullPath());
            File file = new File(imagePath);
            image = ImageIO.read(file);


            AbstractPlumberProductEnd leftPlumberProductEnd = plumberProduct().getEndsList().get(0);
            AbstractPlumberProductEnd rightPlumberProductEnd = plumberProduct().getEndsList().get(1);

            Direction expectedLeftDirection = Direction.north();
            Direction expectedRightDirection = Direction.south();

            if(plumberProduct().isAngular()){
                expectedRightDirection = Direction.east();
            }

            while (leftPlumberProductEnd.direction().equals(expectedLeftDirection) == false){
                leftPlumberProductEnd = leftPlumberProductEnd.rotate();
            }
//
            while (rightPlumberProductEnd.direction().equals(expectedRightDirection) == false){
                rightPlumberProductEnd = rightPlumberProductEnd.rotate();
            }

            int cnt = 0;
            PlumbingProduct dbg = plumberProduct();
            while (plumberProduct().hasEnd(leftPlumberProductEnd) == false
                    || plumberProduct().hasEnd(rightPlumberProductEnd) == false ){
                leftPlumberProductEnd = leftPlumberProductEnd.rotate();
                rightPlumberProductEnd = rightPlumberProductEnd.rotate();

//                int exLeftHash = plumberProduct().getEndsList().get(0).hashCode();
//                int exRightHash = plumberProduct().getEndsList().get(1).hashCode();
//
//                int leftHash = rightPlumberProductEnd.hashCode();
//                int rightHash = leftPlumberProductEnd.hashCode();

                System.out.println(leftPlumberProductEnd.toString()+ " " + rightPlumberProductEnd.toString());
                cnt++;

                if(cnt > 6){
                    dbg.getEndsList();
                }
            }

            image = rotateClockwise(image, cnt * Math.PI/2);


        }
        catch (IOException ex){
            System.out.println("path:" +getPath());
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

        if(plumberProduct().isAngular()){
            path += "angular_pipe/";
        }
        else {
            path += "straight_pipe/";
        }


        AbstractPlumberProductEnd leftPlumberProductEnd = plumberProduct().getEndsList().get(0);
        AbstractPlumberProductEnd rightPlumberProductEnd = plumberProduct().getEndsList().get(1);



        String supDirectory = "big/";
        if(leftPlumberProductEnd instanceof PlumberProductEnd
            && rightPlumberProductEnd instanceof PlumberProductEnd){

            if (isMixed((PlumberProductEnd) leftPlumberProductEnd,(PlumberProductEnd) rightPlumberProductEnd)){
               supDirectory = "mixed/";
            }
            else if (isRevMixed((PlumberProductEnd) leftPlumberProductEnd,(PlumberProductEnd) rightPlumberProductEnd) && plumberProduct().isAngular()){
                supDirectory = "rev_mixed/";
            }
            else if( isSmall((PlumberProductEnd) leftPlumberProductEnd, (PlumberProductEnd) rightPlumberProductEnd) ){
                supDirectory = "small/";
            }



        }

        path += supDirectory;
        return path;
    }


    private  boolean isMixed(PlumberProductEnd left, PlumberProductEnd right){

        if(plumberProduct().isAngular()) {
            return left.diameter() < right.diameter();
        }

        return left.diameter() != right.diameter();
    }

    private  boolean isRevMixed(PlumberProductEnd left, PlumberProductEnd right){
        return left.diameter() > right.diameter();
    }
    private boolean isSmall(PlumberProductEnd left, PlumberProductEnd right){
        return left.diameter() == right.diameter() && left.diameter() == PlumberProductEnd.SMALL_DIAMETER;
    }

    @Override
    protected String getFileName() {

        String fileName = "pipe.png";

        String materialString = "metal";

        AbstractPlumberProductEnd leftPlumberProductEnd = plumberProduct().getEndsList().get(0);
        AbstractPlumberProductEnd rightPlumberProductEnd = plumberProduct().getEndsList().get(1);

        if(leftPlumberProductEnd instanceof PlumberProductEnd && rightPlumberProductEnd instanceof PlumberProductEnd){
            materialString = ((PlumberProductEnd) leftPlumberProductEnd).material().toString()+ "_" + ((PlumberProductEnd) rightPlumberProductEnd).material().toString() + "_";

            if(((PlumberProductEnd) leftPlumberProductEnd).material().equals(((PlumberProductEnd) rightPlumberProductEnd).material())){
                materialString = ((PlumberProductEnd) leftPlumberProductEnd).material().toString() + "_";
            }
        }

        if(plumberProduct().isFilled()){
            materialString = "";
        }

        fileName = materialString + fileName;
        return fileName;
    }


    @Override
    public Pipe plumberProduct() {
        return (Pipe) super.plumberProduct();
    }

    private class MyMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            plumberProduct().rotate();
            repaint();
        }

    }
}
