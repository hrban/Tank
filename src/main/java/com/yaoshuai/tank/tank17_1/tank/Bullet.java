package com.yaoshuai.tank.tank17_1.tank;

import java.awt.*;

public class Bullet {

    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private Dir dir;
    private int x,y;

    Rectangle rectangle = new Rectangle();

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

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
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

        //update rectangle
        rectangle.x = this.x;
        rectangle.y = this.y;
    }
    public void collideWith(Tank tank){
        if(this.group == tank.getGroup()) return;

        //TODO:用一个rect来记录子弹的位置
        //已修复一直创建rectangle的bug

        if(rectangle.intersects(tank.rectangle)){
            tank.die();
            this.die();
            //出现爆炸
            int ex = tank.getX() + Tank.WIDTH/2 - Explosion.WIDTH/2;
            int ey = tank.getY() + Tank.HEIGHT/2 - Explosion.HEIGHT/2 ;
            tankFrame.explosions.add(new Explosion(ex,ey,tankFrame));
        }
    }

    private void die() {
        this.live = false;
    }
}
