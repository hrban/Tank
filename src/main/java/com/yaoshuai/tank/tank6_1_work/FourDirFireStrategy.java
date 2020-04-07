package com.yaoshuai.tank.tank6_1_work;

import com.yaoshuai.tank.tank6_1_work.abstractfactory.BaseTank;
import com.yaoshuai.tank.tank6_1_work.abstractfactory.GameFactory;

public class FourDirFireStrategy implements FireStrategy {

    private FourDirFireStrategy(){}

    public static final FourDirFireStrategy INSTANCE = new FourDirFireStrategy();

    public static FourDirFireStrategy getInstance(){
        return INSTANCE;
    }

    @Override
    public void fire(BaseTank tank,GameFactory gameFactory) {
        int bx = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2 ;
        Dir[] dirs = Dir.values();
        for(Dir dir:dirs){
            gameFactory.creatBullet(bx,by,dir,tank.getGroup(),tank.getTankFrame());
        }

    }
}
