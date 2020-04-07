package com.yaoshuai.tank.tank17_1.net;

import com.yaoshuai.tank.tank17_1.tank.Tank;
import com.yaoshuai.tank.tank17_1.tank.TankFrame;

import java.io.*;
import java.util.UUID;

public class TankStopMsg extends Msg{

    UUID id;
    int x,y;

    public TankStopMsg(UUID id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    public TankStopMsg(){}

    public TankStopMsg(Tank tank){
        this.id = tank.getId();
        this.x = tank.getX();
        this.y = tank.getY();
    }

    @Override
    public void handle() {
        if(this.id.equals(TankFrame.INSTANCE.getMainTank().getId()))
            return;
        Tank tank = TankFrame.INSTANCE.findByUUID(this.id);
        if(tank != null){
            tank.setMoving(false);
            tank.setX(this.x);
            tank.setY(this.y);
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try{
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeInt(x);
            dos.writeInt(y);
            dos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStop;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try{
            this.id = new UUID(dis.readLong(),dis.readLong());
            this.x = dis.readInt();
            this.y = dis.readInt();

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
}
