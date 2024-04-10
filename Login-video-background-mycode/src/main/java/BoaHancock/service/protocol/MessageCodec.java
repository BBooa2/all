package BoaHancock.service.protocol;

import BoaHancock.pojo.info.Info;
import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class MessageCodec extends ByteToMessageCodec<Info> {

    private final Gson gson = new Gson();
    @Override
    protected void encode(ChannelHandlerContext ctx, Info info, ByteBuf out) throws Exception {
        //获取 Infotype
        out.writeInt(info.getInfoType());
        // 将 Info 对象转换为 JSON 字符串
        String json = gson.toJson(info);
//        System.out.println(json);
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
        info = (Info) gson.fromJson(json, Info.getInfoClass(info.getInfoType()));
        String gsons = gson.toJson(info);
        System.out.println("________________________________");
        System.out.println(gsons);
        System.out.println("________________________________");
//        System.out.println(info.getClass());
        // 将解析得到的 Info 对象添加到输出列表中
        out.add(info);
    }
}
