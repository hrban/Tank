package com.yaoshuai.tank.tank17_1.net;

import com.yaoshuai.tank.tank17_1.tank.Bullet;
import com.yaoshuai.tank.tank17_1.tank.Tank;
import com.yaoshuai.tank.tank17_1.tank.TankFrame;

import java.io.*;
import java.util.UUID;

public class TankDieMsg extends Msg{
    UUID id;
    UUID playerID;
    public TankDieMsg(){}
    public TankDieMsg(Tank tank){
        this.id = tank.getId();
    }
    public TankDieMsg(UUID id,UUID playerID){
        this.id = id;
        this.playerID = playerID;
    }

    @Override
    public void handle() {
        if(this.id.equals(TankFrame.INSTANCE.getMainTank().getId()))
            return;
        Tank tank = TankFrame.INSTANCE.findTankByUUID(this.playerID);
        if(this.playerID.equals(TankFrame.INSTANCE.getMainTank().getId())){
            TankFrame.INSTANCE.getMainTank().die();
        }else {
            Tank t = TankFrame.INSTANCE.findTankByUUID(this.playerID);
            Bullet b = TankFrame.INSTANCE.findBulletByUUID(this.id);
            if(t != null){
                t.die();
            }
            if(b != null){
                b.die();
            }
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
            dos.writeLong(playerID.getMostSignificantBits());
            dos.writeLong(playerID.getLeastSignificantBits());
            dos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                baos.close();
                dos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankDie;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try{
            this.id = new UUID(dis.readLong(),dis.readLong());
            this.playerID = new UUID(dis.readLong(),dis.readLong());

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
