package com.hp.multidata.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Paul on 2018/8/11
 */
public class RoutingDS extends AbstractRoutingDataSource {
    AtomicInteger count = new AtomicInteger(0);

    private Integer readSize;

    public RoutingDS(Integer readSize) {
        this.readSize = readSize;
    }

    @Override
    protected Object determineCurrentLookupKey() {

        String typeKey = DSThreadLocal.getCurrentType();

        if (typeKey == null) {
            return DSType.write.getType();
        }

        if (typeKey.equals(DSType.write.getType())) {
            return DSType.write.getType();
        }

        //读库， 简单负载均衡
        int number = count.getAndAdd(1);
        int lookupKey = number % readSize;
        return DSType.read.getType() + (lookupKey + 1);
    }
}
