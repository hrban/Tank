package com.yaoshuai.tank.tank6_1_work;

import com.yaoshuai.tank.tank6_1_work.abstractfactory.BaseBullet;
import com.yaoshuai.tank.tank6_1_work.abstractfactory.BaseTank;
import com.yaoshuai.tank.tank6_1_work.abstractfactory.GameFactory;

//把策略写成单例是为了防止开火策略作为开火方法的参数被多次new
public class DefaultStrategy implements FireStrategy {


    private DefaultStrategy(){}

    public static final DefaultStrategy INSTANCE = new DefaultStrategy();

    public static DefaultStrategy getInstance(){
        return INSTANCE;
    }
    @Override
    public void fire(BaseTank tank, GameFactory gameFactory) {
        int bx = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2 ;
        gameFactory.creatBullet(bx, by, tank.getDir(), tank.getGroup(), tank.getTankFrame());

    }

}
