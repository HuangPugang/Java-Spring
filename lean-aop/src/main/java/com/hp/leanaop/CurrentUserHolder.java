package com.hp.leanaop;

/**
 * Created by Paul on 2018/8/3
 */
public class CurrentUserHolder {
    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static String get() {
        return holder.get();
    }

    public static void set(String user) {
        holder.set(user);
    }
}
