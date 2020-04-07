package com.yaoshuai.tank.tank3_2_work;

import java.awt.*;

public class Bullet {

    private static final int SPEED = 20;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private Dir dir;
    private int x,y;

    private boolean live = true;

    private TankFrame tankFrame;

    private Group group = Group.BAD;

    public Bullet(){

    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g){

        if(!live){
            tankFrame.bullets.remove(this);
        }

        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            default:
                break;
        }
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
    public void collideWith(Tank tank){
        if(this.group == tank.getGroup()) return;

        //TODO:用一个rect来记录子弹的位置
        Rectangle rect1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if(rect1.intersects(rect2)){
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.live = false;
    }
}
