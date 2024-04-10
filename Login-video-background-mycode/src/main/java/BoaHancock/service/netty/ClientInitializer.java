package BoaHancock.service.netty;

import BoaHancock.GUI.main.Main;
import BoaHancock.service.protocol.MessageCodec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MessageCodec())
                .addLast(new LoginResponseHandler());
    }
}
