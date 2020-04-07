package com.yaoshuai.tank.tank6_1_work;

import com.yaoshuai.tank.tank6_1_work.abstractfactory.BaseTank;

import java.awt.*;
import java.util.Random;

public class Tank extends BaseTank {

    public static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankD.getHeight();


    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        super.x = x;
        super.y = y;
        super.dir = dir;
        super.group = group;
        super.tankFrame = tankFrame;

        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
    }

    @Override
    public void paint(Graphics g) {
        if(!live) tankFrame.enemys.remove(this);
        switch (dir){
            case LEFT:
                g.drawImage((this.group == Group.GOOD)? ResourceMgr.goodTankL: ResourceMgr.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage((this.group == Group.GOOD)? ResourceMgr.goodTankR: ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage((this.group == Group.GOOD)? ResourceMgr.goodTankD: ResourceMgr.badTankD,x,y,null);
                break;
            case UP:
                g.drawImage((this.group == Group.GOOD)? ResourceMgr.goodTankU: ResourceMgr.badTankU,x,y,null);
                break;
            default:
                break;
        }

        move();

    }
}
