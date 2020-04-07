package com.yaoshuai.tank.tank2_1;

import java.awt.*;
import java.awt.event.*;
/*
*1、抽象出坦克类 封装属性和方法 在Frame中调用
*2、处理坦克静止
*   只要不按4个键中给的一个 就静止
*3、加坦克子弹
*   定义子弹类 new一个新的子弹
*
*
* */
public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 200, Dir.DOWN);
    Bullet bullet = new Bullet(300,300,Dir.DOWN);

    public TankFrame() {
        setSize(800, 600);
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

    @Override
    //开启窗口时自动调用
    public void paint(Graphics g) {
        //将画笔交给坦克
        myTank.paint(g);
        bullet.paint(g);
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
//                case (KeyEvent.VK_SPACE):
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
//                case (KeyEvent.VK_SPACE):
//                    bS = false;
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

