package io.github.zhangsen.melon.core.codec;

import io.github.zhangsen.melon.core.annotation.HandlerClass;
import io.github.zhangsen.melon.core.annotation.HandlerMethod;
import io.github.zhangsen.melon.core.route.MethodCaller;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CodecManager implements InstantiationAwareBeanPostProcessor {

    private static Map<Short, MethodCaller> handlerMethodMap = new ConcurrentHashMap<>();



    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        findHandler(bean,beanName);
        return bean;
    }

    private void findHandler(Object bean, String beanName){
        Class<?> beanClass = bean.getClass();
        HandlerClass classAnnotation = beanClass.getAnnotation(HandlerClass.class);
        if (classAnnotation == null) {
            return ;
        }
        Method[] declaredMethods = beanClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            HandlerMethod methodAnnotation = method.getAnnotation(HandlerMethod.class);
            if (methodAnnotation == null) {
                continue;
            }

            int packetId = methodAnnotation.value();
            if (packetId <= 0) {
                throw new RuntimeException("错误的packetID："+packetId);
            }
            if (handlerMethodMap.containsKey(packetId)){
                throw new RuntimeException("重复的："+packetId);
            }
            Parameter[] parameters = method.getParameters();

        }
    }
}
