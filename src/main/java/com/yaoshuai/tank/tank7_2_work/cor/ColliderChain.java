package com.yaoshuai.tank.tank7_2_work.cor;

import com.yaoshuai.tank.tank7_2_work.GameObject;
import com.yaoshuai.tank.tank7_2_work.PropertyMgr;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();
    String[] colliderNames = new String[4];

    public ColliderChain(){

        for(int i = 0;i <colliderNames.length;i++){
            colliderNames[i] = (String) PropertyMgr.get("colliders"+String.valueOf(i));
            try {
                colliders.add((Collider) Class.forName(colliderNames[i]).getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
