package com.yaoshuai.tank.tank2_2;

import java.awt.*;

public class Tank {
    private int x,y;
    //定义方向
    private Dir dir;
    //定义速度
    private static final int SPEED = 5;

    private boolean moving = false;

    private TankFrame tankFrame;

    private static final int WIDTH = 50,HEIGHT = 50;

    public Tank(int x, int y, Dir dir,TankFrame tankFrame) {
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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(c);
        move();

    }
    private void move(){
        if (!moving) return;
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
    }

    public void fire() {
//        tankFrame.bullet = new Bullet(this.x,this.y,this.dir);
        tankFrame.bullets.add(new Bullet(this.x+WIDTH/2,this.y+HEIGHT/2,this.dir,this.tankFrame));
    }
}
