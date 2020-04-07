package com.yaoshuai.tank.tank6_1_work;

import com.yaoshuai.tank.tank6_1_work.abstractfactory.BaseTank;
import com.yaoshuai.tank.tank6_1_work.abstractfactory.GameFactory;

public class FireNuclearBombStrategy implements FireStrategy {
    private FireNuclearBombStrategy(){}

    public static final FireNuclearBombStrategy INSTANCE = new FireNuclearBombStrategy();

    public static FireNuclearBombStrategy getInstance(){
        return INSTANCE;
    }
    @Override
    public void fire(BaseTank tank, GameFactory gameFactory) {
        int bx = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2 ;
        gameFactory.creatBullet(bx,by,tank.getDir(),tank.getGroup(),tank.getTankFrame());

    }
}
