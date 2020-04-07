package com.yaoshuai.tank.tank5_1_work;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    private Dir dir;//定义方向
    private static final int SPEED = PropertyMgr.getInt("tankSpeed");//定义速度
    private boolean moving = true;
    private boolean live = true;
    private TankFrame tankFrame = null;
    public static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankD.getHeight();

    Rectangle rectangle = new Rectangle();
    private Random random = new Random();

    private Group group = Group.BAD;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
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

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        if(!live) tankFrame.enemys.remove(this);
        switch (dir){
            case LEFT:
                g.drawImage((this.group == Group.GOOD)? ResourceMgr.goodTankL: ResourceMgr.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage((this.group == Group.GOOD)? ResourceMgr.goodTankR: ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage((this.group == Group.GOOD)? ResourceMgr.goodTankD: ResourceMgr.badTankD,x,y,null);
                break;
            case UP:
                g.drawImage((this.group == Group.GOOD)? ResourceMgr.goodTankU: ResourceMgr.badTankU,x,y,null);
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
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            this.fire(DefaultStrategy.getInstance());
            randomDir();
        }
        //边界检测
        bondsCheck();
        //update reactangle
        rectangle.x = this.x;
        rectangle.y = this.y;


    }
    //边界检测
    private void bondsCheck() {
        if(this.x < 0) x = 0;
        if (this.y < 100) y = 100;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH ) x = TankFrame.GAME_WIDTH - Tank.WIDTH ;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire(FireStrategy f) {
        f.fire(this);
//        int bx = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
//        int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2 ;
//        tankFrame.bullets.add(new Bullet(bx,by,this.dir,this.group,tankFrame));
    }

    public void die() {
        this.live = false;
    }
}
