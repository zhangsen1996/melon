package io.github.zhangsen.melon.core.codec.msgcoder;

import io.netty.buffer.ByteBuf;

public interface IDecode {

    Object decode(ByteBuf msg,Class target);

    String type();
}
