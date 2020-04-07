package com.yaoshuai.tank.tank8_1;

import java.awt.*;

public class Bullet extends GameObject {

    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private Dir dir;
    private int x,y;

    public Rectangle rectangle = new Rectangle();

    private boolean live = true;

    private Group group = Group.BAD;

    public Bullet(){

    }

    public Bullet(int x, int y, Dir dir, Group group) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.group = group;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
        //new一个子弹就加到子弹数组里
        GameModule.getInstance().add(this);
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


    @Override
    public void paint(Graphics g){

        if(!live){
            GameModule.getInstance().remove(this);
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

        //update rectangle
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    public void die() {
        this.live = false;
    }
}
