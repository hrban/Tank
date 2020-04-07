package com.yaoshuai.tank.tank17_1.net;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerFrame extends Frame {
    public static final ServerFrame INSTANCE = new ServerFrame();

    Button btnStart = new Button("start");
    TextArea taLeft = new TextArea();
    TextArea taRight = new TextArea();
    Server server = new Server();

    public ServerFrame(){
        this.setSize(1600,600);
        this.setLocation(300,30);
        this.add(btnStart,BorderLayout.NORTH);
        Panel p = new Panel(new GridLayout(1,2));
        p.add(taLeft);
        p.add(taRight);
        this.add(p);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        /*this.btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                server.serverStart();
            }
        });*/
//        this.btnStart.addActionListener((e)->server.serverStart());
    }

    public static void main(String[] args) {
        ServerFrame.INSTANCE.setVisible(true);
        //解决线程冲突 主线程启动Server
        ServerFrame.INSTANCE.server.serverStart();
    }
    public void updateServerMsg(String s){
        this.taLeft.setText(taLeft.getText() + System.getProperty("line.separator") + s);
    }
    public void updateClientMsg(String s){
        this.taRight.setText(taRight.getText() + System.getProperty("line.separator") + s);
    }

}