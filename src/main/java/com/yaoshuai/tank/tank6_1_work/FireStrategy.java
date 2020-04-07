package com.yaoshuai.tank.tank6_1_work;

import com.yaoshuai.tank.tank6_1_work.abstractfactory.BaseTank;
import com.yaoshuai.tank.tank6_1_work.abstractfactory.GameFactory;

@FunctionalInterface
public interface FireStrategy {

    void fire(BaseTank tank, GameFactory gameFactory);
}
