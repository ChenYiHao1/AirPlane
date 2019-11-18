package airPlay;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;

public class DemoT {

    private void DemoT() {

    }

    public static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            URL u = DemoT.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}
