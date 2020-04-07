package com.yaoshuai.tank.tank2_1;

import java.awt.*;


public class T {
    public static void main(String[] args) throws InterruptedException {

     Frame tf = new TankFrame();
     while (true){
         Thread.sleep(100);
         tf.repaint();
     }
    }
}
