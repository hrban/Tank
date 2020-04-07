package com.yaoshuai.tank.tank2_1;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 10;
    private static final int WIDTH = 20;
    private static final int HEIGHT = 20;
    private Dir dir;
    private int x,y;

    public Bullet(){

    }

    public Bullet( int x, int y,Dir dir) {
        this.dir = dir;
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);
        move();

    }
    private void move(){
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
