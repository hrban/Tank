package com.yaoshuai.tank.tank6_1_work;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    public static BufferedImage goodTankL,goodTankU,goodTankR,goodTankD;
    public static BufferedImage badTankL,badTankU,badTankR,badTankD;
    public static BufferedImage bulletL,bulletD,bulletR,bulletU;
    public static BufferedImage[] explosions = new BufferedImage[16];
    public static BufferedImage pkqExplosion;
    public static BufferedImage pkqBulletL,pkqBulletD,pkqBulletR,pkqBulletU;
    public static BufferedImage pkqGoodTankL,pkqGoodTankU,pkqGoodTankR,pkqGoodTankD;
    public static BufferedImage pkqBadTankL,pkqBadTankU,pkqBadTankR,pkqBadTankD;

    public static final ResourceMgr INSTANCE;//把ResourceMgr写成单例

    static {

        INSTANCE = new ResourceMgr();
        try {
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU,-90);
            goodTankR = ImageUtil.rotateImage(goodTankU,90);
            goodTankD = ImageUtil.rotateImage(goodTankU,180);

            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankL = ImageUtil.rotateImage(badTankU,-90);
            badTankR = ImageUtil.rotateImage(badTankU,90);
            badTankD = ImageUtil.rotateImage(badTankU,180);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);

            for(int i = 0;i < 16;i++){
                explosions[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
            }
            pkqExplosion = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/love.bmp"));

            pkqBulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/xin.jpg"));
            pkqBulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/xin.jpg"));
            pkqBulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/xin.jpg"));
            pkqBulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/xin.jpg"));

            pkqGoodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/pikaqiu.jpg"));
            pkqGoodTankL = ImageUtil.rotateImage(pkqGoodTankU,-90);
            pkqGoodTankR = ImageUtil.rotateImage(pkqGoodTankU,90);
            pkqGoodTankD = ImageUtil.rotateImage(pkqGoodTankU,180);

            pkqBadTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bear.jpg"));
            pkqBadTankL = ImageUtil.rotateImage(pkqBadTankU,-90);
            pkqBadTankR = ImageUtil.rotateImage(pkqBadTankU,90);
            pkqBadTankD = ImageUtil.rotateImage(pkqBadTankU,180);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private ResourceMgr(){}
    public ResourceMgr getInstance(){
        return INSTANCE;
    }
}
