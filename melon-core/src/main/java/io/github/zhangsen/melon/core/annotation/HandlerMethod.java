package io.github.zhangsen.melon.core.annotation;

import io.github.zhangsen.melon.core.codec.msgdecode.IDecode;
import io.github.zhangsen.melon.core.common.Constant;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HandlerMethod {
    int value();
    String decode() default Constant.DECODE_JSON;
}
