package com.lyy.lock_sales;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *参数级的注解，用于注解自定义类型的参数
 * Created by luyuanyuan on 2017/9/19.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockedComplexObject {

    String field() default "";//含有成员变量的复杂对象中需要加锁的成员变量，如一个商品对象的商品ID
}
