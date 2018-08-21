package com.hp.multidata.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


/**
 * 数据源类型本地变量
 * Created by Paul on 2018/8/11
 */
public class DSTypeThreadLocal {

    private static Logger log = LoggerFactory.getLogger(DSTypeThreadLocal.class);

    //线程本地环境
    private static final ThreadLocal<String> dsType = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return dsType;
    }


    public static void setRead() {
        dsType.set(DSType.read.getType());
    }

    public static void setWrite() {
        dsType.set(DSType.write.getType());
    }

    public static String getCurrentType() {
        if (StringUtils.isEmpty(dsType.get())) {
            return DSType.read.getType();
        }
        return dsType.get();
    }

    public static void clear() {
        dsType.remove();
    }
}
