package io.github.zhangsen.melon.core.route;

import io.github.zhangsen.melon.core.codec.msgcoder.IDecode;
import io.github.zhangsen.melon.core.codec.msgcoder.MsgDecodeManager;
import io.github.zhangsen.melon.core.session.ISession;
import io.netty.buffer.ByteBuf;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodCaller {
    private static final Log logger = LogFactory.getLog(MethodCaller.class);
    private int routeId;

    private Method callMethod;

    private Object target;

    private String msgDecodeType;

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

    public void execute(ISession session,ByteBuf msg){
        try {
            IDecode msgDecode = MsgDecodeManager.getMsgDecode(msgDecodeType);
            if (msgDecode == null) {
                logger.error("not found msgDecode by type:"+msgDecodeType);
                return;
            }
            callMethod.invoke(target,session,msgDecode.decode(msg,Object.class));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
