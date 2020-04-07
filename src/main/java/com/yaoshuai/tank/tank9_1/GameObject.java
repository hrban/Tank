package com.yaoshuai.tank.tank9_1;

import java.awt.*;

public abstract class GameObject {
    public int x;
    public int y;

    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();

}
