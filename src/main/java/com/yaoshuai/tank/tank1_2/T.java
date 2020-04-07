package com.yaoshuai.tank.tank1_2;

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
