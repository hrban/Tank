package com.yaoshuai.tank.tank2_3_work;

import java.awt.*;

public class MainTank extends Tank{
    private boolean moving = false;

    public MainTank(int x, int y, Dir dir, TankFrame tankFrame) {
        super(x, y, dir, tankFrame);
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(c);
        move();
    }
    private void move(){
        if (!moving) return;
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
    }
}
