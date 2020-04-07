package com.yaoshuai.tank.tank10_1.strategy;

import com.yaoshuai.tank.tank10_1.Tank;

@FunctionalInterface
public interface FireStrategy {

    void fire(Tank tank);
}
