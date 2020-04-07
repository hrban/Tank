package com.yaoshuai.tank.tank10_1.cor;

import com.yaoshuai.tank.tank10_1.Bullet;
import com.yaoshuai.tank.tank10_1.GameObject;
import com.yaoshuai.tank.tank10_1.Wall;

public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Wall){
            Bullet bullet = (Bullet)o1;
            Wall wall = (Wall)o2;
            if(bullet.rectangle.intersects(wall.rectangle)){
                bullet.die();
            }

        }else if(o1 instanceof Wall && o2 instanceof Bullet){
            collide(o2,o1);
        }
        return true;
    }
}
