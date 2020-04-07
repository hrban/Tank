package com.yaoshuai.tank.tank9_1.decorator;

import com.yaoshuai.tank.tank9_1.GameObject;

import java.awt.*;

public class RectDecorator extends GameObjDecorator{

    public RectDecorator(GameObject gameObj) {

        super(gameObj);
    }

    @Override
    public void paint(Graphics g){

        this.x = gameObj.x;
        this.y = gameObj.y;
        super.paint(g);

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,getWidth()*2,getHeight()*2);
        g.setColor(c);

    }

    @Override
    public int getWidth() {
        return super.gameObj.getWidth();
    }

    @Override
    public int getHeight() {
        return super.gameObj.getHeight();
    }
}
