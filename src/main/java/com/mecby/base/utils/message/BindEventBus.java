package com.mecby.base.utils.message;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by jerry on 2017/8/28 0028.
 * eventBus的注解类
 *
 /**
 * 是否注册事件分发
 *
 * @return 在需要使用的时候,在activity前加上此注解

 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindEventBus {

}


