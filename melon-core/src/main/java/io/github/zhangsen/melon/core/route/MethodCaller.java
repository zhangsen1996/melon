package io.github.zhangsen.melon.core.route;

import io.netty.buffer.ByteBuf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodCaller {

    private int routeId;

    private Method callMethod;

    private Object target;

    public MethodCaller(Method callMethod, Object target) {
        this.callMethod = callMethod;
        this.target = target;
    }

    public Method getCallMethod() {
        return callMethod;
    }

    public void setCallMethod(Method callMethod) {
        this.callMethod = callMethod;
    }

    public void call(ByteBuf msg){
        try {
            callMethod.invoke(target,msg);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
