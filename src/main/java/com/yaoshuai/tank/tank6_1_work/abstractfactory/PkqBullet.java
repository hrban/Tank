package com.yaoshuai.tank.tank6_1_work.abstractfactory;

import com.yaoshuai.tank.tank6_1_work.*;

import java.awt.*;

public class PkqBullet extends BaseBullet{
    public static final int WIDTH = ResourceMgr.pkqBulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.pkqBulletD.getHeight();
    public PkqBullet(){

    }

    public PkqBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.group = group;
        this.tankFrame = tankFrame;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
        //new一个子弹就加到子弹数组里
        tankFrame.bullets.add(this);
    }

    @Override
    public void paint(Graphics g){

        if(!live){
            tankFrame.bullets.remove(this);
        }

        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.pkqBulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.pkqBulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.pkqBulletD,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.pkqBulletU,x,y,null);
                break;
            default:
                break;
        }
        move();

    }
}
