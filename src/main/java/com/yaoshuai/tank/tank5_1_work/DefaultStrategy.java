package com.yaoshuai.tank.tank5_1_work;

//把策略写成单例是为了防止开火策略作为开火方法的参数被多次new
public class DefaultStrategy implements FireStrategy {


    private DefaultStrategy(){}

    public static final DefaultStrategy INSTANCE = new DefaultStrategy();

    public static DefaultStrategy getInstance(){
        return INSTANCE;
    }
    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2 ;
        new Bullet(bx,by,tank.getDir(),tank.getGroup(),tank.getTankFrame());
    }

}
