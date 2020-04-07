package com.yaoshuai.tank.tank17_1.tank;

import com.yaoshuai.tank.tank17_1.net.Client;
import com.yaoshuai.tank.tank17_1.net.TankStartMovingMsg;
import com.yaoshuai.tank.tank17_1.net.TankStopMsg;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

/*
* 1、爆炸功能完成
* 2、敌方坦克随机移动
* 3、爆炸出现在敌方坦克中心
* 4、边界检测 遇墙反弹
* 5、修复在子弹和坦克碰撞检测时会new很多rectangle
* 6、管理日志问题
* */
public class TankFrame extends Frame {


    static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth");
    static final int GAME_HEIGHT = PropertyMgr.getInt("gameHeight");

    public static final TankFrame INSTANCE = new TankFrame();
    Random r = new Random();
//    Tank myTank = new Tank(PropertyMgr.getInt("mainTank_x"), PropertyMgr.getInt("mainTank_y"), Dir.DOWN, Group.GOOD,this);
    Tank myTank = new Tank(r.nextInt(GAME_WIDTH),r.nextInt(GAME_HEIGHT),Dir.values()[r.nextInt(4)], Group.GOOD,this);

//    List<Tank> enemys = new ArrayList<>();//敌方tank
    Map<UUID,Tank> tanks = new HashMap<>();//为了方便通过ID找坦克
    List<Bullet> bullets = new ArrayList<>(); //多颗子弹
    List<Explosion> explosions = new ArrayList<>();//多次爆炸
//    public static final TankFrame INSTANCE = new TankFrame();

    private TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        //设置能否改变大小
        setResizable(false);
        setTitle("Tank War");
//        setVisible(true);

        //添加键盘操作监听器
        this.addKeyListener(new MyKeyListener());

        //添加Windows监听器
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Client.INSTANCE.closeConnect();
                System.exit(0);
            }
        });

    }
    public Tank findByUUID(UUID id){
        return tanks.get(id);
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
        g.drawString("敌方坦克数量："+tanks.size(),10,80);
        g.drawString("爆炸数量："+explosions.size(),10,100);
        g.setColor(c);

        //主战坦克绘制
        myTank.paint(g);
        //子弹绘制
        for (int i = 0;i < bullets.size();i++){
            bullets.get(i).paint(g);
        }
        //敌方坦克绘制
        /*for (int i = 0;i < enemys.size();i++){
            enemys.get(i).paint(g);
        }*/

        //Stream API
        tanks.values().stream().forEach((e)->e.paint(g));
        //爆炸绘制
        for(int i = 0;i < explosions.size();i++){
            explosions.get(i).paint(g);
        }
        //子弹和坦克碰撞检测
        for(int i = 0;i < bullets.size();i++){
            for(int j = 0;j < tanks.size();j++){
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
    }

    public void addTank(Tank tank) {
        tanks.put(tank.getId(),tank);
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
            //如果不按方向键则保持静止
            if(!bL && !bR && !bD && !bU) {
                myTank.setMoving(false);
                Client.INSTANCE.send(new TankStopMsg(getMainTank()));
            }

            //使坦克变为移动状态
            else {
                myTank.setMoving(true);

                //根据键盘操作改变坦克移动方向
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bD) myTank.setDir(Dir.DOWN);
                if (bR) myTank.setDir(Dir.RIGHT);


                //发出坦克移动的消息
                Client.INSTANCE.send(new TankStartMovingMsg(getMainTank()));
            }
        }
    }
    public Tank getMainTank(){
        return this.myTank;
    }

}

