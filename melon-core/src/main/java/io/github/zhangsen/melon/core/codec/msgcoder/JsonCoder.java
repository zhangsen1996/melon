package io.github.zhangsen.melon.core.codec.msgcoder;

import cn.hutool.json.JSONUtil;
import io.github.zhangsen.melon.core.common.Constant;
import io.netty.buffer.ByteBuf;

public class JsonCoder implements IDecode,IEncode{

    @Override
    public Object decode(ByteBuf msg,Class target) {

        int length = msg.readableBytes();
        byte[] bytes = new byte[length];
        msg.readBytes(bytes);
        String jsonBody = new String(bytes);
        Object bean = JSONUtil.toBean(jsonBody, target);
        return bean;
    }

    @Override
    public Object encode(Object msg) {
        return JSONUtil.toJsonStr(msg);
    }

    @Override
    public String type() {
        return Constant.DECODE_JSON;
    }
}
