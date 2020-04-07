package com.yaoshuai.tank.tank2_3_work;

import java.awt.*;

public class EnemyTank extends Tank{

    private boolean live = true;

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public EnemyTank(int x, int y, Dir dir, TankFrame tankFrame) {
        super(x, y, dir, tankFrame);
    }

    @Override
    public void paint(Graphics g) {
        if(!live){
            tankFrame.enemyTankes.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.fillRect(x,y,WIDTH,HEIGHT);
        g.setColor(c);

        move();
    }
    public void move(){
        switch (dir) {
            case UP:
                //y -= SPEED;
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

        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            live = false;
        }
        for(int i = 0;i < tankFrame.bullets.size();i++){
            if(x < tankFrame.bullets.get(i).getX() && x+WIDTH-1 > tankFrame.bullets.get(i).getX() && y < tankFrame.bullets.get(i).getY() && y+HEIGHT-1 >tankFrame.bullets.get(i).getX()){
                live = false;
                tankFrame.bullets.get(i).setLive(false);
            }
        }

    }
    //添加敌方坦克群
    public void addEnemyTank(){
        for(int i = 1;i < 15;i+=2){
            tankFrame.enemyTankes.add(new EnemyTank(0+WIDTH*i,y,dir,this.tankFrame));
        }

    }

}
