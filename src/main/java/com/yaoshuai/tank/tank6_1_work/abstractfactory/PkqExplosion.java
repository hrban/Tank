package com.yaoshuai.tank.tank6_1_work.abstractfactory;

import com.yaoshuai.tank.tank6_1_work.ResourceMgr;
import com.yaoshuai.tank.tank6_1_work.TankFrame;

import java.awt.*;

public class PkqExplosion extends BaseExplosion{

    public static final int WIDTH = ResourceMgr.pkqExplosion.getWidth();
    public static final int HEIGHT = ResourceMgr.pkqExplosion.getHeight();




    public PkqExplosion(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }
    public PkqExplosion(){

    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.pkqExplosion,0,0,WIDTH*step,HEIGHT*step,null);
        step++;
        if(step >= 10){
            tankFrame.explosions.remove(this);
        }


    }
}
