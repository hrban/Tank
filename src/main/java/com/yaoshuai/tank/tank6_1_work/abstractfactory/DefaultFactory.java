package com.yaoshuai.tank.tank6_1_work.abstractfactory;

import com.yaoshuai.tank.tank6_1_work.*;

public class DefaultFactory extends GameFactory{
    @Override
    public BaseTank creatTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new Tank(x,y,dir,group,tankFrame);
    }

    @Override
    public BaseExplosion createExplosion(int x, int y, TankFrame tankFrame) {
        return new Explosion(x,y,tankFrame);
    }

    @Override
    public BaseBullet creatBullet(int x, int y, Dir dir,Group group,TankFrame tankFrame) {
        return new Bullet(x,y,dir,group,tankFrame);
    }
}
