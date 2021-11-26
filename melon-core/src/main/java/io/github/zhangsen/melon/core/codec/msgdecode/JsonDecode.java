package io.github.zhangsen.melon.core.codec.msgdecode;

import cn.hutool.json.JSONUtil;
import io.netty.buffer.ByteBuf;

public class JsonDecode implements IDecode{

    @Override
    public Object decode(ByteBuf msg,Class target) {

        int length = msg.readableBytes();
        byte[] bytes = new byte[length];
        msg.readBytes(bytes);
        String jsonBody = new String(bytes);
        Object bean = JSONUtil.toBean(jsonBody, target);
        return bean;
    }
}
