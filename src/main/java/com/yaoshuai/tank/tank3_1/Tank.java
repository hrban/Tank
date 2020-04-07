package com.yaoshuai.tank.tank3_1;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    private Dir dir;//定义方向
    private static final int SPEED = 1;//定义速度
    private boolean moving = true;
    private boolean live = true;
    private TankFrame tankFrame = null;
    public static final int WIDTH = ResourceMgr.tankD.getWidth();
    public static final int HEIGHT = ResourceMgr.tankD.getHeight();

    private Random random = new Random();

    private Group group = Group.BAD;

    public Tank(int x, int y, Dir dir,Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
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

    public void paint(Graphics g) {
        if(!live) {

            tankFrame.enemys.remove(this);
            return;
        }
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            default:
                break;
        }

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
        if(random.nextInt(10) > 8 ) this.fire();
    }

    public void fire() {
        int bx = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        //因为图片不是很准确，所以加了个值
        int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2 + Bullet.WIDTH/4;
        tankFrame.bullets.add(new Bullet(bx,by,this.dir,this.group,tankFrame));
    }

    public void die() {
        this.live = false;
    }
}
