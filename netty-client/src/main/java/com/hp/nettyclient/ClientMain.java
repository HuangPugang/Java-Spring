package com.hp.nettyclient;

/**
 * Created by Paul on 2018/8/30
 */
public class ClientMain {

    public static void main(String[] args) throws Exception {
        new Client().connect("localhost",8888);
    }
}
