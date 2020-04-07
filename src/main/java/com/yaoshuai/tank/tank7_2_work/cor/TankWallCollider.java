package com.yaoshuai.tank.tank7_2_work.cor;

import com.yaoshuai.tank.tank7_2_work.GameObject;
import com.yaoshuai.tank.tank7_2_work.Tank;
import com.yaoshuai.tank.tank7_2_work.Wall;

public class TankWallCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Wall&& o2 instanceof Tank){
            Tank tank = (Tank)o2;
            Wall wall = (Wall)o1;
            if(tank.rectangle.intersects(wall.rectangle)){
                tank.back();
                return false;
            }

        }else if (o1 instanceof Tank&& o2 instanceof Wall){
            collide(o2,o1);
        }
        return true;
    }
}
