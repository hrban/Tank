package com.yaoshuai.tank.tank6_1_work.abstractfactory;

import com.yaoshuai.tank.tank6_1_work.*;

import java.awt.*;

public abstract class BaseBullet {

    protected static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    protected Dir dir;
    protected int x;
    protected int y;

    protected Rectangle rectangle = new Rectangle();

    protected boolean live = true;

    protected TankFrame tankFrame;

    protected Group group = Group.BAD;

    public abstract void paint(Graphics g);

    public void collideWith(BaseTank tank,GameFactory gameFactory){
        if(this.group == tank.getGroup()) return;

        //TODO:用一个rect来记录子弹的位置
        //已修复一直创建rectangle的bug

        if(rectangle.intersects(tank.rectangle)){
            tank.die();
            this.die();
            //出现爆炸
            int ex = tank.getX() + Tank.WIDTH/2 - Explosion.WIDTH/2;
            int ey = tank.getY() + Tank.HEIGHT/2 - Explosion.HEIGHT/2 ;
            tankFrame.explosions.add(gameFactory.createExplosion(ex,ey,tankFrame));
        }
    }
    protected void move(){
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

    protected void die() {
        this.live = false;
    }
}
