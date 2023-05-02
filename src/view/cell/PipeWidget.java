package view.cell;

import model.plumber_product.Pipe;
import model.plumber_product.PlumbingProduct;
import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PipeWidget extends PlumberProductWidget {
    public PipeWidget(@NotNull Pipe pipe) {

    }

    @Override
    protected BufferedImage getImage() throws IOException {
        return null;
    }

    @Override
    protected String getPath() {
        return null;
    }

    @Override
    protected String getFileName() {
        return null;
    }

    @Override
    public PlumbingProduct plumberProduct() {
        return null;
    }
}
