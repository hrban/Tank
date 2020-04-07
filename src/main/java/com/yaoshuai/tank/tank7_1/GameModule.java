package com.yaoshuai.tank.tank7_1;

import com.yaoshuai.tank.tank6_1_work.abstractfactory.BaseTank;
import com.yaoshuai.tank.tank7_1.cor.BulletTankCollider;
import com.yaoshuai.tank.tank7_1.cor.Collider;
import com.yaoshuai.tank.tank7_1.cor.ColliderChain;
import com.yaoshuai.tank.tank7_1.cor.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModule {
    Tank myTank = new Tank(PropertyMgr.getInt("mainTank_x"), PropertyMgr.getInt("mainTank_y"), Dir.DOWN, Group.GOOD,this);

    ColliderChain chain = new ColliderChain();
//    Collider collider = new BulletTankCollider();
//    Collider collider2 = new TankTankCollider();

    private List<GameObject> gameObjects = new ArrayList<>();

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameModule() {
        int initTankCount = PropertyMgr.getInt("initTankCount");
        //初始化敌方tank
        for(int i = 0;i < initTankCount;i++){
            add(new Tank(50 + i*80,400, Dir.DOWN, Group.BAD,this));
        }
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
//                collider.collide(o1,o2);
//                collider2.collide(o1,o2);
                chain.collide(o1,o2);
            }
        }
    }

    public Tank getMyTank() {
        return myTank;
    }
}
