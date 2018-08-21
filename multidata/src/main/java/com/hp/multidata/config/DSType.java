package com.hp.multidata.config;

/**
 * 数据源类型
 * Created by Paul on 2018/8/11
 */
public enum DSType {

    read("read", "从库"),
    write("write", "主库");

    private String type;

    private String name;

    DSType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
