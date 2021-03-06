package com.yaoshuai.tank.tank17_1.net;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.yaoshuai.tank.tank17_1.tank.Dir;
import com.yaoshuai.tank.tank17_1.tank.Tank;
import com.yaoshuai.tank.tank17_1.tank.TankFrame;

import java.io.*;
import java.util.UUID;

public class TankStartMovingMsg extends Msg{
    UUID id;
    int x,y;
    Dir dir;

    public TankStartMovingMsg(UUID id, int x, int y, Dir dir) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
    public TankStartMovingMsg(){}

    public TankStartMovingMsg(Tank tank){
        this.id = tank.getId();
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
    }


    @Override
    public void handle() {
        if(this.id.equals(TankFrame.INSTANCE.getMainTank().getId()))
            return;
        Tank tank = TankFrame.INSTANCE.findTankByUUID(this.id);

        if(tank != null){
            tank.setMoving(true);
            tank.setDir(this.dir);
            tank.setX(this.x);
            tank.setY(this.y);
        }
    }

    @Override
    public byte[] toBytes() {

        DataOutputStream dos = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bytes = null;
        try{
            byteArrayOutputStream = new ByteArrayOutputStream();
            dos = new DataOutputStream(byteArrayOutputStream);
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.flush();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  bytes;
    }
    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try{
            this.id = new UUID(dis.readLong(),dis.readLong());
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStartMoving;
    }

    @Override
    public String toString() {
        return "TankStartMovingMsg{" +
                "坦克id=" + id +
                "|| x=" + x +
                "|| y=" + y +
                "|| dir=" + dir +
                '}';
    }
}
