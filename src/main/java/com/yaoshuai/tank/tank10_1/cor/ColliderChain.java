package com.yaoshuai.tank.tank10_1.cor;

import com.yaoshuai.tank.tank10_1.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();
//    String[] colliderNames = new String[4];

    public ColliderChain(){

        add(new BulletTankCollider());
        add(new TankTankCollider());
        add(new TankWallCollider());
        add(new BulletWallCollider());

    }

    public void add(Collider collider){
        colliders.add(collider);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2){
        for (int i = 0;i < colliders.size();i++){
            if(!colliders.get(i).collide(o1,o2)){
                return false;
            }
        }
        return true;
    }

}
