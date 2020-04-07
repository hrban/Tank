package com.yaoshuai.tank.tank17_1.tank;


import com.yaoshuai.tank.tank17_1.net.Client;

public class Main {
    public static void main(String[] args)  {

     TankFrame tf = TankFrame.INSTANCE;
     tf.setVisible(true);

     new Thread(()->{
         while (true){
             try {
                 Thread.sleep(100);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             tf.repaint();
         }
     }).start();

        Client.INSTANCE.connect();
    }
}
