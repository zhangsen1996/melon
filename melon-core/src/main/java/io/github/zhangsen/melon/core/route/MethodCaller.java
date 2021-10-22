package io.github.zhangsen.melon.core.route;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodCaller {
    private Method callMethod;

    private Object bean;

    public MethodCaller(Method callMethod, Object bean) {
        this.callMethod = callMethod;
        this.bean = bean;
    }

    public Method getCallMethod() {
        return callMethod;
    }

    public void setCallMethod(Method callMethod) {
        this.callMethod = callMethod;
    }

    public void call(Object ...args){
        try {
            callMethod.invoke(bean,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
