package com.yaoshuai.tank.tank7_1;

import java.awt.*;

public class Explosion extends GameObject{
    private int x,y;
    private boolean live = true;
    private GameModule gameModule;
    public static final int WIDTH = ResourceMgr.explosions[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explosions[0].getHeight();

    private int step = 0;//paint到哪一步


    public Explosion(int x, int y, GameModule gameModule) {
        this.x = x;
        this.y = y;
        this.gameModule = gameModule;
    }
    public Explosion(){

    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(ResourceMgr.explosions[step++],x,y,null);
        if(step >= ResourceMgr.explosions.length)
            //爆炸结束后移除爆炸
            gameModule.remove(this);
    }

}
