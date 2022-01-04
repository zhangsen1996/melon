package io.github.zhangsen.melon.core.codec.msgcoder;

import io.netty.buffer.ByteBuf;

public interface IEncode {

    Object encode(Object msg);

    String type();
}
