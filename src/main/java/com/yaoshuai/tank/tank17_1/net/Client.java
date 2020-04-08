package com.yaoshuai.tank.tank17_1.net;

import com.yaoshuai.tank.tank17_1.tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

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
                                    .addLast(new MsgEncoder())
                                    .addLast(new MsgDecoder())
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

    public void send(Msg msg){
        channel.writeAndFlush(msg);
    }

    //通知服务器客户端要退出
    public void closeConnect(){
//        this.send("_bye_");
        System.out.println("exit success!");
    }
}

//可以用泛型指定 处理一种消息类型 也可以把多种类型消息继承同一个msg 也可使用
class ClientHandler extends SimpleChannelInboundHandler<Msg>{

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg){

        System.out.println("客户端收到消息："+msg.toString());
        ServerFrame.INSTANCE.updateServerMsg("客户端收到消息："+msg.toString());
        msg.handle();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
    }
}

