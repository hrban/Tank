package com.yaoshuai.tank.tank17_1.net;

import com.yaoshuai.tank.tank17_1.tank.Dir;
import com.yaoshuai.tank.tank17_1.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

public class TankJoinMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out){
//        //需要计算TankJoinMsg字节 -----吐了
//        if(in.readableBytes() < 33) return;//Tcp拆包和粘包的问题
//
//        TankJoinMsg msg = new TankJoinMsg();
//        msg.x = in.readInt();
//        msg.y = in.readInt();
//        msg.dir = Dir.values()[in.readInt()];
//        msg.moving = in.readBoolean();
//        msg.group = Group.values()[in.readInt()];
//        msg.id = new UUID(in.readLong(),in.readLong());
//
//        out.add(msg);
        //消息头：消息类型+长度是8个字节 收到以后才可以解析消息
        if(in.readableBytes() < 8) return;
        //标记读指针
        in.markReaderIndex();

        MsgType msgType = MsgType.values()[in.readInt()];
        int length = in.readInt();

        //如果已读的部分小于消息的长度 继续回去读 return并重置读指针
        if(in.readableBytes() < length){
            //将读指针归位 重置读指针
            in.resetReaderIndex();
            return;
        }
        //读全了消息再进行处理
        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        switch(msgType){
            case TankJoinMsg:
                TankJoinMsg msg = new TankJoinMsg();
                msg.parse(bytes);
                out.add(msg);
            default:
                break;
        }
    }
}
