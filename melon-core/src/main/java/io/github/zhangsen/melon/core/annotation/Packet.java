package io.github.zhangsen.melon.core.annotation;

import io.github.zhangsen.melon.core.common.Constant;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Packet {

    int packetId();

    String codec() default Constant.DECODE_JSON;
}
