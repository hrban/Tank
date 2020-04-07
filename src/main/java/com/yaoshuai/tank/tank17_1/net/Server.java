package com.yaoshuai.tank.tank17_1.net;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class Server {
    //定义一个通道组
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public void serverStart(){
        //只负责连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //只负责读写（干活儿）
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            ChannelFuture channelFuture = serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch){
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new MsgDecoder())
                                    .addLast(new MsgEncoder())
                                    .addLast(new ServerChildHandler());

                        }
                    })
                    .bind(8888)
                    .sync();//第一个阻塞确保绑定成功


            ServerFrame.INSTANCE.updateServerMsg("server started!");

            //closeFuture()如果有人调用close方法 就返回一个ChannelFuture
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

class ServerChildHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //拿到对应的通道放到通道组里
        Server.clients.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ServerFrame.INSTANCE.updateClientMsg(msg.toString());
        Server.clients.writeAndFlush(msg);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        //删除出现异常的Client的Channel，并关闭连接
        Server.clients.remove(ctx.channel());
        ctx.close();
    }
}
