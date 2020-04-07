package com.yaoshuai.tank.tank9_1;

import com.yaoshuai.tank.tank9_1.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModule {

    private static final GameModule INSTANCE = new GameModule();

    static {
        INSTANCE.init();
    }
     Tank myTank ;

    ColliderChain chain = new ColliderChain();

    private List<GameObject> gameObjects = new ArrayList<>();

    private GameModule() {


    }

    private void init(){
        //初始化主战坦克
        myTank =  new  Tank(PropertyMgr.getInt("mainTank_x"), PropertyMgr.getInt("mainTank_y"),  Dir.DOWN,  Group.GOOD);
        int initTankCount = PropertyMgr.getInt("initTankCount");
        //初始化敌方tank
        for(int i = 0;i < initTankCount;i++){
            new  Tank(50 + i*80,250, Dir.DOWN, Group.BAD);
        }

        //初始化墙
        add(new  Wall(150,150,200,50));
        add(new  Wall(500,150,200,50));
        add(new  Wall(300,300,50,200));
        add(new Wall(550,300,50,200));
    }

    public static GameModule getInstance(){
        return INSTANCE;
    }

    public void add(GameObject gameObject){
        gameObjects.add(gameObject);
    }

    public void remove(GameObject gameObject){
        gameObjects.remove(gameObject);
    }

    public void paint(Graphics g) {

        //主战坦克绘制
        myTank.paint(g);

        //游戏对象绘制
        for (int i = 0;i < gameObjects.size();i++){
            gameObjects.get(i).paint(g);
        }

        //相互碰撞
        for(int i = 0;i < gameObjects.size();i++){
            for(int j = i+1;j < gameObjects.size();j++){
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                chain.collide(o1,o2);
            }
        }
    }

    public Tank getMyTank() {
        return myTank;
    }
}
