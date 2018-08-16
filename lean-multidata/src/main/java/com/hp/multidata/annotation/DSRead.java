package com.hp.multidata.annotation;

import java.lang.annotation.*;
/**
 * Created by Paul on 2018/8/11
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DSRead {

}
