package com.yaoshuai.tank.tank17_1.net;

import com.yaoshuai.tank.tank17_1.tank.Dir;
import com.yaoshuai.tank.tank17_1.tank.Group;
import com.yaoshuai.tank.tank17_1.tank.Tank;
import com.yaoshuai.tank.tank17_1.tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.util.UUID;

public class Client {

    public static final  Client INSTANCE = new Client();
    private Channel channel  = null;

    private Client(){}
    public void connect(){

        EventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap b = new Bootstrap();
        try {
            ChannelFuture channelFuture = b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    .addLast(new TankJoinMsgEncoder())
                                    .addLast(new TankJoinMsgDecoder())
                                    .addLast(new ClientHandler());
                        }
                    }).connect("localhost",8888);
            channelFuture.sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) {
                    if(future.isSuccess()){
                        System.out.println("connect success!");
                        //确认连接之后，初始化channel
                        channel = future.channel();
                    }else {
                        System.out.println("connect failed~");
                    }
                }
            });

            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    //将窗口的字符串传给服务器
    public void send(Msg msg){
        ByteBuf buf = Unpooled.copiedBuffer(msg.toBytes());
        channel.writeAndFlush(buf);
    }

    //通知服务器客户端要退出
    public void closeConnect(){
//        this.send("_bye_");
//        System.out.println("exit success!");
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.connect();
    }
}

/*
class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = null;
        try {
            byteBuf = (ByteBuf) msg;
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.getBytes(byteBuf.readerIndex(), bytes);
            String msgAccepted = new String(bytes);
//            ClientFrame.INSTANCE.updateText(msgAccepted);

        } finally {
            if (byteBuf != null) ReferenceCountUtil.release(byteBuf);
            //因为是直接访问内存 会跳过java的垃圾回收机制 所以需要释放 才会显示无人引用

        }
    }

}
*/

//可以用泛型指定 处理一种消息类型 也可以把多种类型消息继承同一个msg 也可使用
class ClientHandler extends SimpleChannelInboundHandler<Msg>{

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg){

        msg.handle();
//        if(msg.id.equals(TankFrame.INSTANCE.getMainTank().getId())||
//            TankFrame.INSTANCE.findByUUID(msg.id) != null) return;
//        System.out.println(msg);
//        Tank tank = new Tank(msg);
//        TankFrame.INSTANCE.addTank(tank);
//        channelHandlerContext.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        TankJoinMsg tankJoinMsg = new TankJoinMsg(TankFrame.INSTANCE.getMainTank());
        ctx.writeAndFlush(tankJoinMsg);
//        ctx.writeAndFlush(new TankJoinMsg(100,100, Dir.DOWN,false,Group.GOOD, UUID.randomUUID()));
    }
}

