//package com.example.demo.service.server;
//
////import com.example.demo.service.impl.UserServer;
//import com.example.demo.service.protocol.MessageCodec;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.socket.SocketChannel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class ServerInitializer extends ChannelInitializer<SocketChannel> {
//
//    @Autowired
//    ServerHandler serverHandler;
//    @Override
//    protected void initChannel(SocketChannel ch) {
//        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast(new MessageCodec());
//        pipeline.addLast(serverHandler);
//    }
//}
