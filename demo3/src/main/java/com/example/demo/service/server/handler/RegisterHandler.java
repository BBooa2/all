package com.example.demo.service.server.handler;

import com.example.demo.pojo.info.RegisterRequestInfo;
import com.example.demo.service.impl.UserServer;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class RegisterHandler extends SimpleChannelInboundHandler<RegisterRequestInfo> {

    @Autowired
    UserServer userServer;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RegisterRequestInfo registerRequestInfo) throws Exception {
        String username = registerRequestInfo.getUsername();
        String nickname = registerRequestInfo.getNickname();
        String password = registerRequestInfo.getPassword();
        String confirmPassword = registerRequestInfo.getConfirmPassword();
        String email = registerRequestInfo.getEmail();
        String Email = "^\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}$";

        boolean isValid = userServer.register(username, nickname, password, confirmPassword, email);
        if(isValid && email.matches(Email)) {
            System.out.println("注册成功");
        }
        else {
            System.out.println("注册失败");
        }
    }
}
