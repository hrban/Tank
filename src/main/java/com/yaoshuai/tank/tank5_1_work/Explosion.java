package com.yaoshuai.tank.tank5_1_work;

import java.awt.*;

public class Explosion {
    private int x,y;
    private boolean live = true;
    private TankFrame tankFrame;
    public static final int WIDTH = ResourceMgr.explosions[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explosions[0].getHeight();

    private int step = 0;//paint到哪一步


    public Explosion(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
//        new Audio("audio/explode.wav").loop();//添加声音
    }
    public Explosion(){

    }

    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explosions[step++],x,y,null);
        if(step >= ResourceMgr.explosions.length)
            //爆炸结束后移除爆炸
            tankFrame.explosions.remove(this);
    }

}
