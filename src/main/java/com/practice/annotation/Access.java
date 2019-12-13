package com.practice.annotation;

import com.practice.status.AccessPeople;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hzq
 * @date 2019/12/13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Access {

    AccessPeople[] value();

}
