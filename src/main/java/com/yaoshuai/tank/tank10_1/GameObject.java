package com.yaoshuai.tank.tank10_1;

import java.awt.*;
import java.io.Serializable;

public abstract class GameObject implements Serializable {
    public int x;
    public int y;

    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();

}
