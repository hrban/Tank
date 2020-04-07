package com.yaoshuai.tank.tank6_1_work;

import com.yaoshuai.tank.tank6_1_work.abstractfactory.BaseExplosion;

import java.awt.*;

public class Explosion extends BaseExplosion {

    public static final int WIDTH = ResourceMgr.explosions[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explosions[0].getHeight();

    public Explosion(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
//        new Audio("audio/explode.wav").loop();//添加声音
    }
    public Explosion(){

    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explosions[step++],x,y,null);
        if(step >= ResourceMgr.explosions.length)
            //爆炸结束后移除爆炸
            tankFrame.explosions.remove(this);
    }

}
