package com.example.demo.service.protocol;

import com.example.demo.pojo.info.Info;
import com.example.demo.pojo.info.LoginRequestInfo;
import com.example.demo.pojo.info.RegisterRequestInfo;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class MessageCodec extends ByteToMessageCodec<Info> {
    private final Gson gson = new Gson();
    @Override
    protected void encode(ChannelHandlerContext ctx, Info info, ByteBuf out) throws Exception {
        //获取 InfoType
        out.writeInt(info.getInfoType());
        // 将 Info 对象转换为 JSON 字符串
        String json = gson.toJson(info);

        System.out.println("++++++++++++++++++++++++++++");
        System.out.println(json);
        System.out.println("++++++++++++++++++++++++++++");

        // 将 JSON 字符串写入ByteBuf
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //获取Infotype
        int infoType = in.readInt();
        // 读取 JSON 字符串的长度
        int length = in.readInt();
        // 读取 JSON 字符串的内容
        byte[] bytes = new byte[length];
        in.readBytes(bytes);
        String json = new String(bytes, StandardCharsets.UTF_8);
        // 将 JSON 字符串转换为 Info 对象
        Info info = new Info() {
            @Override
            public int getInfoType() {
                return infoType;
            }
        };

//        System.out.println(info);
//        System.out.println("---------------------------------------");

        info = ((Info) gson.fromJson(json, Info.getInfoClass(info.getInfoType())));
        // 将解析得到的 Info 对象添加到输出列表中
        if(info instanceof LoginRequestInfo loginRequestInfo) {
            out.add(loginRequestInfo);
        }
        else if(info instanceof RegisterRequestInfo registerRequestInfo) {
            out.add(registerRequestInfo);
        }
//        String gsons = gson.toJson(info);
//        System.out.println(gsons);
//
//        System.out.println(info);
//        info;
        // 将解析得到的 Info 对象添加到输出列表中
//        out.add(info);
    }
}