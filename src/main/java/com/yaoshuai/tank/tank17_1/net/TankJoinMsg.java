package com.yaoshuai.tank.tank17_1.net;

import com.yaoshuai.tank.tank17_1.tank.*;

import java.io.*;
import java.util.UUID;


public class TankJoinMsg extends Msg{
    public int x,y;
    public Dir dir;
    public boolean moving;
    public Group group;
    public UUID id;

    //为了传tank方便
    public TankJoinMsg(Tank tank) {
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.group = tank.getGroup();
        this.id = tank.getId();
        this.moving = tank.isMoving();
    }

    public TankJoinMsg(int x, int y, Dir dir, boolean moving, Group group, UUID id) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.id = id;
    }

    public TankJoinMsg(){}

    @Override
    public byte[] toBytes(){
        ByteArrayOutputStream byteArrayOutputStream = null;
        DataOutputStream dataOutputStream = null;
        byte[] bytes = null;

        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            dataOutputStream.writeInt(x);
            dataOutputStream.writeInt(y);
            //ordinal 数组下标
            dataOutputStream.writeInt(dir.ordinal());
            dataOutputStream.writeBoolean(moving);
            dataOutputStream.writeInt(group.ordinal());
            //UUID是128位 高位+低位 两个long
            dataOutputStream.writeLong(id.getMostSignificantBits());
            dataOutputStream.writeLong(id.getLeastSignificantBits());
            //
            dataOutputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  bytes;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankJoinMsg;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try{
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.moving = dis.readBoolean();
            this.group = Group.values()[dis.readInt()];
            this.id = new UUID(dis.readLong(),dis.readLong());
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
    public String toString() {
        return "TankJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", moving=" + moving +
                ", group=" + group +
                ", id=" + id +
                '}';
    }

    @Override
    public void handle() {
        if(this.id.equals(TankFrame.INSTANCE.getMainTank().getId())||
                TankFrame.INSTANCE.findByUUID(this.id) != null) return;
        System.out.println(this);
        Tank tank = new Tank(this);
        TankFrame.INSTANCE.addTank(tank);
        Client.INSTANCE.send(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
    }
}
