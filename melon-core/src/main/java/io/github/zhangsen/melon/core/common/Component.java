package io.github.zhangsen.melon.core.common;

public interface Component {

    void start();
    default String getName(){
        return this.getClass().getSimpleName();
    }
}
