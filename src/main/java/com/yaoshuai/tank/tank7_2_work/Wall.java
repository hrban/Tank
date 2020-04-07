package com.yaoshuai.tank.tank7_2_work;

import java.awt.*;

public class Wall extends GameObject{
    private int x,y;
    private int w,h;
    private GameModule gameModule;
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
    void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x,y,w,h);
        g.setColor(c);
    }
}
