package com.yaoshuai.tank.tank10_1.cor;

import com.yaoshuai.tank.tank10_1.GameObject;
import com.yaoshuai.tank.tank10_1.Tank;
import com.yaoshuai.tank.tank10_1.Wall;

public class TankWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Wall && o2 instanceof Tank){
            Tank tank = (Tank)o2;
            Wall wall = (Wall)o1;
            if(tank.rectangle.intersects(wall.rectangle)){
                tank.back();
                return false;
            }

        }else if (o1 instanceof Tank && o2 instanceof Wall){
            collide(o2,o1);
        }
        return true;
    }
}
