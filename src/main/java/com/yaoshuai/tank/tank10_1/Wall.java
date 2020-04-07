package com.yaoshuai.tank.tank10_1;

import java.awt.*;

public class Wall extends GameObject {
    private int w,h;
    public Rectangle rectangle ;
    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        rectangle = new Rectangle(x,y,w,h);


    }
    public Wall(){

    }
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x,y,w,h);
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
