package com.yaoshuai.tank.tank8_1.cor;

import com.yaoshuai.tank.tank8_1.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
