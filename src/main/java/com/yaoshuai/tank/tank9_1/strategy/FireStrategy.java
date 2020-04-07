package com.yaoshuai.tank.tank9_1.strategy;

import com.yaoshuai.tank.tank9_1.Tank;

@FunctionalInterface
public interface FireStrategy {

    void fire(Tank tank);
}
