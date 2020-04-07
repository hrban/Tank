package com.yaoshuai.tank.tank1_1;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class T {
    public static void main(String[] args) {
        //new一个窗口
        Frame frame = new Frame();
        frame.setSize(800,600);
        //设置能否改变大小
        frame.setResizable(false);
        frame.setTitle("Tank War");
        frame.setVisible(true);

        //添加Windows监听器
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
}
