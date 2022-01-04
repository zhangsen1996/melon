package io.github.zhangsen.melon.core.codec;

import cn.hutool.json.JSONUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ProtocolEncoder extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        /*if (msg instanceof Integer){
            out.writeInt((Integer) msg);
        }
        if (msg instanceof String){
            out.writeBytes(((String) msg).getBytes());
        }*/
        String s = JSONUtil.toJsonStr(msg);
        byte[] bytes = s.getBytes();
        out.writeInt(1);
        out.writeInt(bytes.length);
        out.writeBytes(bytes);

    }
}
