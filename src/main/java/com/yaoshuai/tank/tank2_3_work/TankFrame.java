package com.yaoshuai.tank.tank2_3_work;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/*
*1、双缓冲解决闪烁
*   <1>repaint之后会先调用update方法
*   <2>截获update（重写这个方法）
*   <3>把要画的（子弹，坦克，背景）用自定义画笔画到内存中
*   <4>把内存的内容用系统画笔画在屏幕上
*
*2、按键打子弹
*
* */
public class TankFrame extends Frame {

    MainTank myTank = new MainTank(200, 200, Dir.DOWN,this);
    List<EnemyTank> enemyTankes = new ArrayList<>();
    EnemyTank enemyTank = new EnemyTank(350,350,Dir.UP,this);
    //为了能发射多颗子弹
    List<Bullet> bullets = new ArrayList<>();
//    Bullet bullet = new Bullet(300,300, Dir.UP,this);
    static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;
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

        //一个bug产生 子弹一直打 数量一直增加 （java内存泄漏）
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹数量"+bullets.size(),10,60);
        g.setColor(c);

        g.setColor(Color.white);
        g.drawString("剩余敌方坦克数量"+enemyTankes.size(),10,80);
        g.setColor(c);

        //将画笔交给坦克
        myTank.paint(g);
//        bullet.paint(g);
        //多颗子弹
        //第一种解决方式
        for (int i = 0;i < bullets.size();i++){
            bullets.get(i).paint(g);
        }

        //第二种解决方式
//        for(Iterator<Bullet> iterator = bullets.iterator();iterator.hasNext();){
//            Bullet b = iterator.next();
//            if(!b.isLive()) iterator.remove();
//            b.paint(g);
//        }
        //ConcurrentModificationException按如下方式在子弹消失的时候会出现这个异常
//        for(Bullet b:bullets){
//            b.paint(g);
//        }

        for(int i = 0;i < enemyTankes.size();i++){
            enemyTankes.get(i).paint(g);
        }

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
                    myTank.fire();
                    break;
                case (KeyEvent.VK_SHIFT):
                    enemyTank.addEnemyTank();
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
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

