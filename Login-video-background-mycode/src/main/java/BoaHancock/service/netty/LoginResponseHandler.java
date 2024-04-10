package BoaHancock.service.netty;

import BoaHancock.GUI.application.Application;
import BoaHancock.GUI.main.Main;
import BoaHancock.pojo.info.LoginResponseInfo;
import BoaHancock.pojo.info.UserInfo;
import BoaHancock.utils.UserHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponseInfo> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponseInfo loginResponseInfo) throws Exception {
        if(loginResponseInfo.isSuccess()) {
            Application application = new Application();
            UserInfo userInfo = loginResponseInfo.getUserInfo();
            UserHolder.set(userInfo);
            System.out.println(userInfo.getEmail());
            Main.main.closeLoginWindow();
            application.setVisible(true);
            System.out.println("登陆成功");
        }
        else {
            System.out.println("登陆失败");
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        UserHolder.remove();
        System.out.println("断开连接");
    }
}
