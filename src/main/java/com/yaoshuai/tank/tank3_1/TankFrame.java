package com.yaoshuai.tank.tank3_1;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/*
*1、添加图片代表坦克,添加图片代表子弹
*
*2、子弹位置调整
*
* 3更改tank和bullet静态属性 WIDTH和HEIGHT
* 4在new子弹的时候参数改变
* 5 敌方tank
* 6、打掉敌方坦克（碰撞检测 每颗子弹对每个坦克 两败俱伤）
* 7、让敌方坦克动起来
* 8、让敌方坦克打子弹
* 9、区分敌我 默认不开启队友伤害
* 10、加入敌方坦克被击毁就爆炸 加入声音
*
*
* */
public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 200, Dir.DOWN,Group.GOOD,this);
    List<Tank> enemys = new ArrayList<>();//敌方tank
    List<Bullet> bullets = new ArrayList<>(); //多颗子弹
    static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;

    Explosion e = new Explosion(300,300,this);
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
        g.setColor(Color.white);
        g.drawString("子弹数量："+bullets.size(),10,60);

        g.drawString("敌方坦克数量："+enemys.size(),10,80);
        g.setColor(c);

        //将画笔交给坦克
        myTank.paint(g);
        e.paint(g);
        //子弹绘制
        for (int i = 0;i < bullets.size();i++){
            bullets.get(i).paint(g);
        }
        //敌方坦克绘制
        for (int i = 0;i < enemys.size();i++){
            enemys.get(i).paint(g);
            //enemys.get(3).fire();
        }
        for(int i = 0;i < bullets.size();i++){
            for(int j = 0;j < enemys.size();j++){
                bullets.get(i).collideWith(enemys.get(j));
            }

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

