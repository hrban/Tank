package com.yaoshuai.tank.tank17_1.net;

public class TankStartMovingMsg extends Msg{
    @Override
    public void handle() {

    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStartMoving;
    }

    @Override
    public void parse(byte[] bytes) {

    }
}
