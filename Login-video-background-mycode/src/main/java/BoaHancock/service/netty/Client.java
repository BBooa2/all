package BoaHancock.service.netty;


import BoaHancock.GUI.main.Main;
import BoaHancock.pojo.info.LoginRequestInfo;
import BoaHancock.pojo.info.RegisterRequestInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import javax.swing.*;
import java.io.IOException;
import java.net.UnknownHostException;

public class Client extends JPanel {
    private Channel channel;
    public void connect(String host, int port) throws UnknownHostException, IOException {
        System.out.println("Connecting to server at " + host + ":" + port); // 添加日志输出
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());

            ChannelFuture future = bootstrap.connect(host, port).sync();
            setChannel(future.channel());
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            group.shutdownGracefully();
        }

        System.out.println("Connected to server successfully."); // 添加日志输出
    }
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    public void sendLoginMessage(LoginRequestInfo info) {
        if(channel != null && channel.isActive()) {
            channel.writeAndFlush(info);
        }
        else {
            System.err.println("Channel is not active, unable to  login send message");
        }
    }
    public void sendRegisterMessage(RegisterRequestInfo info) {
        if(channel != null && channel.isActive()) {
            channel.writeAndFlush(info);
        }
        else {
            System.err.println("Channel is not active, unable to send register message");
        }
    }

}
