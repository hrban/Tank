package com.yaoshuai.tank.tank2_2;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 20;
    private static final int WIDTH = 15;
    private static final int HEIGHT = 15;
    private Dir dir;
    private int x,y;

    private boolean live = true;

    private TankFrame tankFrame;

    public Bullet(){

    }

    public Bullet(int x, int y, Dir dir,TankFrame tankFrame) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g){

        if(!live){
            tankFrame.bullets.remove(this);
        }

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
        //如果子弹飞出边界 自行死亡
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            live = false;
        }
    }
}
