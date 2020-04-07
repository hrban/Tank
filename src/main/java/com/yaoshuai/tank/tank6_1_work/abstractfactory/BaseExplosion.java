package com.yaoshuai.tank.tank6_1_work.abstractfactory;

import com.yaoshuai.tank.tank6_1_work.TankFrame;

import java.awt.*;

public abstract class BaseExplosion {
    protected int x,y;
    protected boolean live = true;
    protected TankFrame tankFrame;
    protected int step = 0;

    public abstract void paint(Graphics g);
}
