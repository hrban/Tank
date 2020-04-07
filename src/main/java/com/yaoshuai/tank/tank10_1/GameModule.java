package com.yaoshuai.tank.tank10_1;

import com.yaoshuai.tank.tank10_1.cor.ColliderChain;

import java.awt.*;
import java.io.*;
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

    public void save(){
        File f = new File("Tank.data");
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(f));
            oos.writeObject(myTank);
            oos.writeObject(gameObjects);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void load(){
        File file = new File("Tank.data");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            myTank = (Tank)ois.readObject();
            gameObjects = (List)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
