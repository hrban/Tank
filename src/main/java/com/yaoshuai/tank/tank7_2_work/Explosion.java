package com.yaoshuai.tank.tank7_2_work;

import java.awt.*;

public class Explosion extends GameObject {
    private int x,y;
    public static final int WIDTH = ResourceMgr.explosions[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explosions[0].getHeight();

    private int step = 0;//paint到哪一步


    public Explosion(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Explosion(){

    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explosions[step++],x,y,null);
        if(step >= ResourceMgr.explosions.length)
            //爆炸结束后移除爆炸
            GameModule.getInstance().remove(this);
    }

}
