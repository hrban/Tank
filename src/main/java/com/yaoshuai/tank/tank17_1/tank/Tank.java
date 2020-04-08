package com.yaoshuai.tank.tank17_1.tank;

import com.yaoshuai.tank.tank17_1.net.BulletNewMsg;
import com.yaoshuai.tank.tank17_1.net.Client;
import com.yaoshuai.tank.tank17_1.net.TankJoinMsg;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

public class Tank {
    private int x,y;
    private Dir dir;//定义方向
    private static final int SPEED = PropertyMgr.getInt("tankSpeed");//定义速度
    private boolean moving = false;
    private boolean live = true;
    private TankFrame tankFrame = null;
    public static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankD.getHeight();

    Rectangle rectangle = new Rectangle();
    private Random random = new Random();

    private Group group = Group.BAD;

    private UUID id = UUID.randomUUID();

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
    public Tank(TankJoinMsg msg){
        this.x = msg.x;
        this.y = msg.y;
        this.dir = msg.dir;
        this.group = msg.group;
        this.moving = msg.moving;
        this.id = msg.id;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void paint(Graphics g) {
//        if(!live) tankFrame.tanks.remove(this);
        //uuid on head
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawString(id.toString(),this.x,this.y-10);
        g.setColor(c);

        //draw a rect if dead
        if(!live){
            moving = false;
            Color cc = g.getColor();
            g.setColor(Color.WHITE);
            g.fillRect(x,y,WIDTH,HEIGHT);
            g.setColor(cc);
            return;
        }

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
        if(!live) return;
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
            this.fire();
            randomDir();
        }
        //边界检测
        bondsCheck();
        //update rectangle
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

    public void fire() {
        int bx = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2 ;
        Bullet bullet = new Bullet(this.id,bx,by,this.dir,this.group,tankFrame);
        tankFrame.bullets.add(bullet);
        Client.INSTANCE.send(new BulletNewMsg(bullet));
    }

    public void die() {
        this.live = false;
        //出现爆炸
        int ex = this.getX() + Tank.WIDTH/2 - Explosion.WIDTH/2;
        int ey = this.getY() + Tank.HEIGHT/2 - Explosion.HEIGHT/2 ;
        TankFrame.INSTANCE.explosions.add(new Explosion(ex,ey));
    }
}
