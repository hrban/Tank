package com.yaoshuai.tank.tank9_1.decorator;

import com.yaoshuai.tank.tank9_1.GameObject;

import java.awt.*;

public class TailDecorator extends GameObjDecorator{

    public TailDecorator(GameObject gameObj) {
        super(gameObj);
        this.x = gameObj.x;
        this.y = gameObj.y;
    }

    @Override
    public void paint(Graphics g){
        this.x = gameObj.x;
        this.y = gameObj.y;
        super.paint(g);

        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.fillOval(x,y+ getWidth(),getWidth()*1,getHeight()*1);
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
