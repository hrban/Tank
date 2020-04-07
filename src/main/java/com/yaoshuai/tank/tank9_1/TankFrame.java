package com.yaoshuai.tank.tank9_1;

import com.yaoshuai.tank.tank9_1.strategy.FourDirFireStrategy;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/*
*装饰者模式Decorator在子弹上加一个外壳一个尾巴 只在主战坦克的开火策略中调用
*
* */
public class TankFrame extends Frame {

    GameModule gameModule =GameModule.getInstance();

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
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("主战坦克："+ String.valueOf(gameModule.getMyTank().isLive()),10,60);
        g.setColor(c);

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

