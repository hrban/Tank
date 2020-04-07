package com.yaoshuai.tank.tank7_1.cor;

import com.yaoshuai.tank.tank7_1.GameObject;
import com.yaoshuai.tank.tank7_1.Group;
import com.yaoshuai.tank.tank7_1.Tank;

public class TankTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank tank1 = (Tank)o1;
            Tank tank2 = (Tank)o2;

            if(tank1.rectangle.intersects(tank2.rectangle)){
                tank1.stop();
                tank2.stop();
                //return false;
            }
        }
        return true;
    }
}
