package com.yaoshuai.tank.tank6_1_work.abstractfactory;

import com.yaoshuai.tank.tank6_1_work.*;

import java.awt.*;

public class PkqTank extends BaseTank{
    public static final int WIDTH = ResourceMgr.pkqGoodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.pkqGoodTankD.getHeight();


    public PkqTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
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
                g.drawImage((this.group == Group.GOOD)? ResourceMgr.pkqGoodTankL: ResourceMgr.pkqBadTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage((this.group == Group.GOOD)? ResourceMgr.pkqGoodTankR: ResourceMgr.pkqBadTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage((this.group == Group.GOOD)? ResourceMgr.pkqGoodTankD: ResourceMgr.pkqBadTankD,x,y,null);
                break;
            case UP:
                g.drawImage((this.group == Group.GOOD)? ResourceMgr.pkqGoodTankU: ResourceMgr.pkqBadTankU,x,y,null);
                break;
            default:
                break;
        }

        move();

    }
}
