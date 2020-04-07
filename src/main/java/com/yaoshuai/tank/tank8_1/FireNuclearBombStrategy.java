package com.yaoshuai.tank.tank8_1;

public class FireNuclearBombStrategy implements FireStrategy {
    private FireNuclearBombStrategy(){}

    public static final FireNuclearBombStrategy INSTANCE = new FireNuclearBombStrategy();

    public static FireNuclearBombStrategy getInstance(){
        return INSTANCE;
    }
    @Override
    public void fire(Tank tank) {
        int bx = tank.getX() + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int by = tank.getY() + Tank.HEIGHT/2 - Bullet.HEIGHT/2 ;
        new Bullet(bx,by,tank.getDir(),tank.getGroup());

    }
}
