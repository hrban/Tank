package com.yaoshuai.tank.tank9_1.decorator;

import com.yaoshuai.tank.tank9_1.GameObject;

import java.awt.*;

public abstract class GameObjDecorator extends GameObject {

    GameObject gameObj;

    public GameObjDecorator(GameObject gameObj) {
        this.gameObj = gameObj;
    }

    @Override
    public void paint(Graphics g) {
        gameObj.paint(g);
    }

}
