package io.github.zhangsen.melon.core.net.codec.json;

import cn.hutool.json.JSONUtil;
import io.github.zhangsen.melon.core.net.codec.Codec;
import io.github.zhangsen.melon.core.net.packet.AbstractPacket;
import io.netty.buffer.ByteBuf;

import static io.netty.util.CharsetUtil.UTF_8;


public class JsonCodec implements Codec {

    public AbstractPacket decode(int packetId, ByteBuf byteBuf, Class<? extends AbstractPacket> packetClass){
        String string = byteBuf.toString(UTF_8);
        AbstractPacket abstractPacket = JSONUtil.toBean(string, packetClass);
        return abstractPacket;
    }


    public void encode(Object msg, ByteBuf out){
        String jsonStr = JSONUtil.toJsonStr(msg);
        byte[] bytes = jsonStr.getBytes();
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }

}
