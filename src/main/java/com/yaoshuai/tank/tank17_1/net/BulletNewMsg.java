package com.yaoshuai.tank.tank17_1.net;

import com.yaoshuai.tank.tank17_1.tank.*;

import java.io.*;
import java.util.UUID;

public class BulletNewMsg extends Msg{

    public UUID playerID;
    public UUID id;
    public Dir dir;
    public int x;
    public int y;
    public Group group;

    public BulletNewMsg(){}

    public BulletNewMsg(UUID playerID, UUID id, Dir dir, int x, int y, Group group) {
        this.playerID = playerID;
        this.id = id;
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.group = group;
    }

    public BulletNewMsg(Bullet bullet){
        this.playerID = bullet.getPlayerID();
        this.id = bullet.getId();
        this.x = bullet.getX();
        this.y = bullet.getY();
        this.dir = bullet.getDir();
        this.group = bullet.getGroup();
    }

    @Override
    public void handle() {
        ServerFrame.INSTANCE.updateServerMsg(toString());
        if (this.playerID.equals(TankFrame.INSTANCE.getMainTank().getId())) return;

        Bullet bullet = new Bullet(this.playerID, x, y, dir, group, TankFrame.INSTANCE);
        bullet.setId(this.id);
//        Bullet bullet = new Bullet(this);
        TankFrame.INSTANCE.addBullet(bullet);


    }

    @Override
    public byte[] toBytes() {
        DataOutputStream dos = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bytes = null;
        try{
            byteArrayOutputStream = new ByteArrayOutputStream();
            dos = new DataOutputStream(byteArrayOutputStream);
            dos.writeLong(playerID.getMostSignificantBits());
            dos.writeLong(playerID.getLeastSignificantBits());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeInt(group.ordinal());
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
    public MsgType getMsgType() {
        return MsgType.BulletNew;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try{
            this.playerID = new UUID(dis.readLong(),dis.readLong());
            this.id = new UUID(dis.readLong(),dis.readLong());
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.group = Group.values()[dis.readInt()];

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
        return "BulletNewMsg{" +
                "坦克ID=" + playerID +
                "|| 子弹id=" + id +
                "|| dir=" + dir +
                "|| x=" + x +
                "|| y=" + y +
                "|| group=" + group +
                '}';
    }
}
