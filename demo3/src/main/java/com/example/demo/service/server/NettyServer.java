package com.example.demo.service.server;

import com.example.demo.service.protocol.MessageCodec;
import com.example.demo.service.server.handler.LoginHandler;
import com.example.demo.service.server.handler.RegisterHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NettyServer {
    @Value("${netty.port}")
    private int port;
    @Autowired
    LoginHandler LOGIN_HANDLER = new LoginHandler();
    @Autowired
    RegisterHandler REGISTER_HANDLER = new RegisterHandler();
    private ConcurrentHashMap<SocketChannel, NettyClient> socketChannelNettyClientConcurrentHashMap;

    private List<NettyClient> onlineNettyClient;
    public void start() throws Exception {
        socketChannelNettyClientConcurrentHashMap = new ConcurrentHashMap<>();
        onlineNettyClient = new ArrayList<>();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            System.out.println("Starting Netty server on port " + port);
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            NettyClient client = new NettyClient();
                            client.setSocketChannel(socketChannel);
                            socketChannelNettyClientConcurrentHashMap.put(socketChannel, client);
                            onlineNettyClient.add(client);

                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new MessageCodec());
                            pipeline.addLast(LOGIN_HANDLER);
                            pipeline.addLast(REGISTER_HANDLER);
                        }

                    });
            ChannelFuture future = bootstrap.bind(port).sync();

            future.addListener((ChannelFutureListener) channelFuture -> {
                if (channelFuture.isSuccess()) {
                    System.out.println("Server is now listening for incoming connections on port " + port);
                } else {
                    System.err.println("Failed to start server");
                    channelFuture.cause().printStackTrace();
                }
            });

            System.out.println("Netty server started successfully."); // 添加日志输出
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
