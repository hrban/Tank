package com.yaoshuai.tank.tank3_1;

import java.awt.*;


public class T {
    public static void main(String[] args) throws InterruptedException {

     TankFrame tf = new TankFrame();

     //初始化敌方tank
     for(int i = 0;i < 8;i++){
         tf.enemys.add(new Tank(50 + i*50,400,Dir.UP,Group.BAD,tf));
     }
     while (true){
         Thread.sleep(100);
         tf.repaint();
     }
    }
}
