package com.yaoshuai.tank.tank6_1_work.abstractfactory;

import com.yaoshuai.tank.tank6_1_work.*;

import java.awt.*;
import java.util.Random;

public abstract class BaseTank {
    protected int x;
    protected int y;
    protected Dir dir;//定义方向
    protected static final int SPEED = PropertyMgr.getInt("tankSpeed");//定义速度
    protected boolean moving = true;
    protected boolean live = true;
    protected TankFrame tankFrame = null;
    protected Group group = Group.BAD;

    public Rectangle rectangle = new Rectangle();
    private Random random = new Random();

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public static int getSPEED() {
        return SPEED;
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }

    public void setTankFrame(TankFrame tankFrame) {
        this.tankFrame = tankFrame;
    }

    public BaseTank(int x, int y, Dir dir, Group group,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;

        rectangle.x = this.x;
        rectangle.y = this.y;

    }
    public BaseTank(){}

    public abstract void paint(Graphics g);

    protected void move(){
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
            this.fire(DefaultStrategy.getInstance(),tankFrame.gameFactory);
            randomDir();
        }
        //边界检测
        bondsCheck();

        rectangle.x = this.x;
        rectangle.y = this.y;


    }
    //边界检测
    protected void bondsCheck() {
        if(this.x < 0) x = 0;
        if (this.y < 100) y = 100;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH ) x = TankFrame.GAME_WIDTH - Tank.WIDTH ;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
    }

    protected void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire(FireStrategy f,GameFactory gameFactory){
        f.fire(this,gameFactory);
    }

    public void die() {
        this.live = false;
    }
}
