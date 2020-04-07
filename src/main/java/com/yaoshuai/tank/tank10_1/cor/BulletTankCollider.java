package com.yaoshuai.tank.tank10_1.cor;


import com.yaoshuai.tank.tank10_1.*;

public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet bullet = (Bullet)o1;
            Tank tank = (Tank)o2;

            if(bullet.getGroup() == tank.getGroup()) return true;

            //已修复一直创建rectangle的bug

            if(bullet.rectangle.intersects(tank.rectangle)){
                tank.die();
                bullet.die();
                //出现爆炸
                int ex = tank.getX() + Tank.WIDTH/2 - Explosion.WIDTH/2;
                int ey = tank.getY() + Tank.HEIGHT/2 - Explosion.HEIGHT/2 ;
                GameModule.getInstance().add(new Explosion(ex,ey));
                return false;
            }
        }
        //骚操作
        else if(o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2,o1);
        }
        //感觉没用
        return true;
    }
}
