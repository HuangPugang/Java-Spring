package com.hp.multidata.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


/**
 * Created by Paul on 2018/8/11
 */
public class DSThreadLocal {

    private static Logger log = LoggerFactory.getLogger(DSThreadLocal.class);

    //线程本地环境
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }


    public static void setRead() {
        local.set(DSType.read.getType());
    }

    public static void setWrite() {
        local.set(DSType.write.getType());
    }

    public static String getCurrentType() {
        if (StringUtils.isEmpty(local.get())) {
            return DSType.read.getType();
        }
        return local.get();
    }

    public static void clear() {
        local.remove();
    }
}
