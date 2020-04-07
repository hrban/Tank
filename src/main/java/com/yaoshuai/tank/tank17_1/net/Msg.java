package com.yaoshuai.tank.tank17_1.net;

public abstract class Msg {
    public abstract void handle();
    public abstract byte[] toBytes();

    public abstract MsgType getMsgType();

    public abstract void parse(byte[] bytes);
}
