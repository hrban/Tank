package com.yaoshuai.tank.tank17_1.tank;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageUtil {
    public static BufferedImage rotateImage(final BufferedImage bufferedImage,final int degree){

        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        int type = bufferedImage.getColorModel().getTransparency();

        BufferedImage image;
        Graphics2D graphics2D;
        graphics2D = (image = new BufferedImage(w,h,type)).createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.rotate(Math.toRadians(degree),w / 2,h / 2);
        graphics2D.drawImage(bufferedImage,0,0,null);
        graphics2D.dispose();

        return image;
    }
}
