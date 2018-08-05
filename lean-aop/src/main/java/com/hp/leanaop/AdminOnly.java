package com.hp.leanaop;

import java.lang.annotation.*;

/**
 * Created by Paul on 2018/8/3
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminOnly {
}
