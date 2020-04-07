package com.yaoshuai.tank.tank7_2_work;


public class Main {
    public static void main(String[] args) throws InterruptedException {

     TankFrame tf = new TankFrame();

     while (true){
         Thread.sleep(100);
         tf.repaint();
     }

    }
}
