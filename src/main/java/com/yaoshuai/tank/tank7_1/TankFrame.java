package com.yaoshuai.tank.tank7_1;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/*
* ps:在5_1的基础上修改 因为6_1用了抽象工厂模式，改动较大
*
*       门面模式+调停者模式
*           解决TankFrame添加新物体太麻烦的问题
*   1、TankFrame--------->Facade
*       Frame------->展示
*       GameModule-->内部计算
*   2、GameObject 游戏相关组件的父类
*
* 具体改变：抽象出GameModule将Module与View分离，同时GameModule将作为Facade负责与Frame打交道同时处理Module内部事务
* 责任链模式，处理各种游戏对象的碰撞，即产生一个碰撞链，且在新添加游戏对象比如墙时，仅需增加墙的类以及墙和坦克 和子弹的碰撞策略 不需要修改代码
*           各种策略通过读取配置文件获取
* ps：1、责任链也实现碰撞器Collider接口，可以实现责任链叠加
*     2、设置Boolean类型返回值，目的是为了在某些策略中 游戏对象消失以后在之后的责任链循环中除去不必要的流程。
*
* */
public class TankFrame extends Frame {

    GameModule gameModule = new GameModule();

    static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth");
    static final int GAME_HEIGHT = PropertyMgr.getInt("gameHeight");


    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        //设置能否改变大小
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);

        //添加键盘操作监听器
        this.addKeyListener(new MyKeyListener());

        //添加Windows监听器
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
    //双缓冲解决闪烁
    Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        //先用自定义的画笔（存在于内存）涂黑整个屏幕
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(color);
        //调用paint方法 用自定义的画笔（存在于内存）画坦克和子弹
        paint(gOffScreen);
        //用系统的画笔把内存里画的全部拷贝至屏幕
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    //开启窗口时自动调用
    public void paint(Graphics g) {

        gameModule.paint(g);
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;


        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case (KeyEvent.VK_W):
                    bU = true;
                    break;
                case (KeyEvent.VK_S):
                    bD = true;
                    break;
                case (KeyEvent.VK_A):
                    bL = true;
                    break;
                case (KeyEvent.VK_D):
                    bR = true;
                    break;
                case (KeyEvent.VK_SPACE):

//                    bS = true;
                default:
                    break;
            }
            setMainTankDir();

        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();
            switch (key) {
                case (KeyEvent.VK_W):
                    bU = false;
                    break;
                case (KeyEvent.VK_S):
                    bD = false;
                    break;
                case (KeyEvent.VK_A):
                    bL = false;
                    break;
                case (KeyEvent.VK_D):
                    bR = false;
                    break;
                case (KeyEvent.VK_SPACE):
                    gameModule.getMyTank().fire(FourDirFireStrategy.getInstance());
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            Tank myTank = gameModule.getMyTank();
            //使坦克变为移动状态
            myTank.setMoving(true);

            //根据键盘操作改变坦克移动方向
            if (bL) myTank.setDir(Dir.LEFT);
            if (bU) myTank.setDir(Dir.UP)  ;
            if (bD) myTank.setDir(Dir.DOWN) ;
            if (bR) myTank.setDir(Dir.RIGHT)  ;

            //如果不按方向键则保持静止
            if(!bL && !bR && !bD && !bU) myTank.setMoving(false);
        }
    }
}

