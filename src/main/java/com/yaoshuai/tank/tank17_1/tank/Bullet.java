package com.yaoshuai.tank.tank17_1.tank;

import com.yaoshuai.tank.tank17_1.net.BulletNewMsg;
import com.yaoshuai.tank.tank17_1.net.Client;
import com.yaoshuai.tank.tank17_1.net.TankDieMsg;

import java.awt.*;
import java.util.UUID;

public class Bullet {

    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private Dir dir;
    private int x,y;
    private UUID id = UUID.randomUUID();
    private UUID playerID = UUID.randomUUID();


    Rectangle rectangle = new Rectangle();

    private boolean live = true;

    private TankFrame tankFrame;

    private Group group = Group.BAD;

    public Bullet(){

    }

    public Bullet(UUID playerID,int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.playerID = playerID;
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

    public Bullet(BulletNewMsg msg){
        this.x = msg.x;
        this.y = msg.y;
        this.dir = msg.dir;
        this.group = msg.group;
        this.id = msg.id;

        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPlayerID() {
        return playerID;
    }

    public void setPlayerID(UUID playerID) {
        this.playerID = playerID;
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
        if(this.playerID.equals(tank.getId())) return;

        //TODO:用一个rect来记录子弹的位置
        //已修复一直创建rectangle的bug

        if(this.live && tank.isLive() && rectangle.intersects(tank.rectangle)){
            tank.die();
            this.die();
            Client.INSTANCE.send(new TankDieMsg(this.id,tank.getId()));
        }
    }

    public void die() {
        this.live = false;
    }
}
