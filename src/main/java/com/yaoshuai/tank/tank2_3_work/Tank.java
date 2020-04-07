package com.yaoshuai.tank.tank2_3_work;

import java.awt.*;

public abstract class Tank {
    protected int x,y;
    //定义方向
    protected Dir dir;
    //定义速度
    protected static final int SPEED = 5;


    protected TankFrame tankFrame;

    protected static final int WIDTH = 50,HEIGHT = 50;


    public Tank(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }
    public Tank(){

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getSPEED() {
        return SPEED;
    }

    public abstract void paint(Graphics g);


    public void fire() {
//        tankFrame.bullet = new Bullet(this.x,this.y,this.dir);
        tankFrame.bullets.add(new Bullet(this.x+WIDTH/2,this.y+HEIGHT/2,this.dir,this.tankFrame));
    }
}
