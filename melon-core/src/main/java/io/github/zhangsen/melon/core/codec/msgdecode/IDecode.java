package io.github.zhangsen.melon.core.codec.msgdecode;

import io.netty.buffer.ByteBuf;

public interface IDecode {

    Object decode(ByteBuf msg,Class target);

}
