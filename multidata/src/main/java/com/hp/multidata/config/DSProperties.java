package com.hp.multidata.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

/**
 * 数据库属性
 * Created by Paul on 2018/8/11
 */
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DSProperties {

    private Integer readSize;

    private Class<? extends DataSource> type;

    private String mapperLocations;

    private DB write;

    private List<DB> reads;

    public Integer getReadSize() {
        return readSize;
    }

    public void setReadSize(Integer readSize) {
        this.readSize = readSize;
    }

    public Class<? extends DataSource> getType() {
        return type;
    }

    public void setType(Class<? extends DataSource> type) {
        this.type = type;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }

    public List<DB> getReads() {
        return reads;
    }

    public void setReads(List<DB> reads) {
        this.reads = reads;
    }

    public DB getWrite() {
        return write;
    }

    public void setWrite(DB write) {
        this.write = write;
    }

    public static class DB {
        private String url;
        private String username;
        private String password;
        private String driverClassName;
        private int minIdle;
        private int maxActive;
        private int maxIdle;
        private int initialSize;
        private int timeBetweenEvictionRunsMillis = 1800000;
        private int minEvictableIdleTimeMillis = 30000;
        private String validationQuery = "select 'x'";
        private boolean testWhileIdle = true;
        private boolean testOnBorrow = false;
        private boolean testOnReturn = false;
        private boolean poolPreparedStatements = true;
        private int maxPoolPreparedStatementPerConnectionSize = 50;
        private boolean removeAbandoned = true;
        private String filters = "stat";
        private int validationInterval = 30000;
        private int validationQueryTimeout = 5;
        private int maxWait = 10000;
        private int removeAbandonedTimeout = 60;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxActive() {
            return maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }

        public int getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getInitialSize() {
            return initialSize;
        }

        public void setInitialSize(int initialSize) {
            this.initialSize = initialSize;
        }

        public int getTimeBetweenEvictionRunsMillis() {
            return timeBetweenEvictionRunsMillis;
        }

        public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
            this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        }

        public int getMinEvictableIdleTimeMillis() {
            return minEvictableIdleTimeMillis;
        }

        public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
            this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
        }

        public String getValidationQuery() {
            return validationQuery;
        }

        public void setValidationQuery(String validationQuery) {
            this.validationQuery = validationQuery;
        }

        public boolean isTestWhileIdle() {
            return testWhileIdle;
        }

        public void setTestWhileIdle(boolean testWhilteIdle) {
            this.testWhileIdle = testWhilteIdle;
        }

        public boolean isTestOnBorrow() {
            return testOnBorrow;
        }

        public void setTestOnBorrow(boolean testOnBorrow) {
            this.testOnBorrow = testOnBorrow;
        }

        public boolean isTestOnReturn() {
            return testOnReturn;
        }

        public void setTestOnReturn(boolean testOnReturn) {
            this.testOnReturn = testOnReturn;
        }

        public boolean isPoolPreparedStatements() {
            return poolPreparedStatements;
        }

        public void setPoolPreparedStatements(boolean poolPreparedStatements) {
            this.poolPreparedStatements = poolPreparedStatements;
        }

        public int getMaxPoolPreparedStatementPerConnectionSize() {
            return maxPoolPreparedStatementPerConnectionSize;
        }

        public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
            this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
        }

        public boolean isRemoveAbandoned() {
            return removeAbandoned;
        }

        public void setRemoveAbandoned(boolean removeAbandoned) {
            this.removeAbandoned = removeAbandoned;
        }

        public String getFilters() {
            return filters;
        }

        public void setFilters(String filters) {
            this.filters = filters;
        }

        public int getValidationInterval() {
            return validationInterval;
        }

        public void setValidationInterval(int validationInterval) {
            this.validationInterval = validationInterval;
        }

        public int getValidationQueryTimeout() {
            return validationQueryTimeout;
        }

        public void setValidationQueryTimeout(int validationQueryTimeout) {
            this.validationQueryTimeout = validationQueryTimeout;
        }

        public int getMaxWait() {
            return maxWait;
        }

        public void setMaxWait(int maxWait) {
            this.maxWait = maxWait;
        }

        public int getRemoveAbandonedTimeout() {
            return removeAbandonedTimeout;
        }

        public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
            this.removeAbandonedTimeout = removeAbandonedTimeout;
        }
    }

}
