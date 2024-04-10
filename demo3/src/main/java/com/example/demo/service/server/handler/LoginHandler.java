package com.example.demo.service.server.handler;

import com.example.demo.pojo.DO.UserDO;
import com.example.demo.pojo.info.LoginRequestInfo;
import com.example.demo.pojo.info.LoginResponseInfo;
import com.example.demo.pojo.info.UserInfo;
import com.example.demo.service.impl.UserServer;
import com.example.demo.service.session.SessionMemoryImpl;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class LoginHandler extends SimpleChannelInboundHandler<LoginRequestInfo> {

//    private final Gson gson = new Gson();
    @Autowired
    UserServer userServer;
    @Autowired
    SessionMemoryImpl sessionMemory;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestInfo loginRequestInfo) throws Exception {
        String username = loginRequestInfo.getUsername();
        String password = loginRequestInfo.getPassword();

        boolean isValid = userServer.login(username, password);
        if (isValid) {
            UserDO userDO = userServer.getUserInfo(username);
            UserInfo userInfo = new UserInfo(userDO);
            userInfo.setUsername(userDO.getUsername());

            sessionMemory.bind(channelHandlerContext.channel(), username);
            LoginResponseInfo responseInfo = new LoginResponseInfo(true,"", userInfo);
            channelHandlerContext.writeAndFlush(responseInfo);
//            System.out.println("登陆成功");
        } else {
            LoginResponseInfo responseInfo = new LoginResponseInfo(false, "");
            responseInfo.setSuccess(false);
            channelHandlerContext.writeAndFlush(responseInfo);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        sessionMemory.unbind(ctx.channel());
        System.out.println("连接已断开");
    }
}
