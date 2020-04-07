package com.yaoshuai.tank.tank9_1.strategy;

import com.yaoshuai.tank.tank9_1.Bullet;
import com.yaoshuai.tank.tank9_1.Dir;
import com.yaoshuai.tank.tank9_1.GameModule;
import com.yaoshuai.tank.tank9_1.Tank;
import com.yaoshuai.tank.tank9_1.decorator.RectDecorator;
import com.yaoshuai.tank.tank9_1.decorator.TailDecorator;

public class FourDirFireStrategy implements FireStrategy {

    private FourDirFireStrategy(){}

    public static final FourDirFireStrategy INSTANCE = new FourDirFireStrategy();

    public static FourDirFireStrategy getInstance(){
        return INSTANCE;
    }

    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2 ;
        Dir[] dirs = Dir.values();
        for(Dir dir:dirs){
            //Bug? new了两发子弹
            Bullet bullet = new Bullet(bx,by,dir,tank.getGroup());
            GameModule.getInstance().add(new TailDecorator(new RectDecorator(bullet)));
        }

    }
}
