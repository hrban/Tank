package com.yaoshuai.tank.tank1_2;

import java.awt.*;
import java.awt.event.*;

public class TankFrame extends Frame {
    int x = 200,y = 200;
    //定义方向
    Dir dir = Dir.STOP;
    //定义速度
    final int SPEED = 10;


    public TankFrame(){
        setSize(800,600);
        //设置能否改变大小
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println("mouse clicked");
////                x += 50;
////                y += 50;
////                repaint();

            }
        });

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
    public void paint(Graphics g){
        g.fillRect(x,y,50,50);
        switch (dir){
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
            case STOP:
                break;
            default:
                break;
        }
//        x += 50;

    }
    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;
        boolean bS = false;


        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
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
                case(KeyEvent.VK_SPACE):
                    bS = true;
                default:
                    break;
            }
            setMainTankDir();

        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
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
                case(KeyEvent.VK_SPACE):
                    bS = false;
                default:
                    break;
            }
            setMainTankDir();
        }
        private void setMainTankDir(){
            if(bL) dir = Dir.LEFT;
            if(bU) dir = Dir.UP;
            if(bD) dir = Dir.DOWN;
            if(bR) dir = Dir.RIGHT;
            if(bS) dir = Dir.STOP;
        }
    }

}
