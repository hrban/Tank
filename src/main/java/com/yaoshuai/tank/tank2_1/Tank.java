package com.yaoshuai.tank.tank2_1;

import java.awt.*;

public class Tank {
    private int x,y;
    //定义方向
    private Dir dir;
    //定义速度
    private static final int SPEED = 5;

    private boolean moving = false;

    public Tank(int x, int y,Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
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
        g.fillRect(x,y,50,50);
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
}
