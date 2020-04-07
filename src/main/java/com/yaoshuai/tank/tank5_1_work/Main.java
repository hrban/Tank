package com.yaoshuai.tank.tank5_1_work;


public class Main {
    public static void main(String[] args) throws InterruptedException {

     TankFrame tf = new TankFrame();
     int initTankCount = PropertyMgr.getInt("initTankCount");
     //初始化敌方tank
     for(int i = 0;i < initTankCount;i++){
         tf.enemys.add(new Tank(50 + i*Tank.WIDTH + 5,400, Dir.DOWN, Group.BAD,tf));
     }
     while (true){
         Thread.sleep(100);
         tf.repaint();
     }

    }
}
