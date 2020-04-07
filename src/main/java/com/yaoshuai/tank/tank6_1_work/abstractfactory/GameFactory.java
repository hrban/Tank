package com.yaoshuai.tank.tank6_1_work.abstractfactory;

import com.yaoshuai.tank.tank6_1_work.Dir;
import com.yaoshuai.tank.tank6_1_work.Group;
import com.yaoshuai.tank.tank6_1_work.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank creatTank(int x, int y, Dir dir, Group group, TankFrame tankFrame);
    public abstract BaseExplosion createExplosion(int x, int y, TankFrame tankFrame);
    public abstract BaseBullet creatBullet(int x, int y,Dir dir,Group group, TankFrame tankFrame);

}
