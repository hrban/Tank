package com.yaoshuai.tank.tank6_1_work.abstractfactory;

import com.yaoshuai.tank.tank6_1_work.Dir;
import com.yaoshuai.tank.tank6_1_work.Group;
import com.yaoshuai.tank.tank6_1_work.TankFrame;

public class PkqFactory extends GameFactory{
    @Override
    public BaseTank creatTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new PkqTank(x,y,dir,group,tankFrame);
    }

    @Override
    public BaseExplosion createExplosion(int x, int y, TankFrame tankFrame) {
        return new PkqExplosion(x,y,tankFrame);
    }

    @Override
    public BaseBullet creatBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new PkqBullet(x,y,dir,group,tankFrame);
    }

}
