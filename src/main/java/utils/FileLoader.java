package utils;

import sun.misc.ClassLoaderUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class FileLoader {

    public static Image getImage(String icon_name) {
        InputStream is = ClassLoaderUtil.class.getResourceAsStream("/icon/"+icon_name+".png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static ImageIcon getImageIcon(String icon_name) {
        InputStream is = ClassLoaderUtil.class.getResourceAsStream("/icon/"+icon_name+".png");
        BufferedImage image = null;
        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ImageIcon(image);
    }

}
