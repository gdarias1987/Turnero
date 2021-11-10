package utils;

import sun.misc.ClassLoaderUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class FileLoader {

    public static Image getIcon(String icon_name) {
        InputStream is = ClassLoaderUtil.class.getResourceAsStream("/icon/"+icon_name+".png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
