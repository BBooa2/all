package com.example.demo.service.server;

import io.netty.channel.socket.SocketChannel;

public class NettyClient {
    private SocketChannel socketChannel;
    public SocketChannel getSocketChannel() {
        return socketChannel;
    }
    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }
}
