package com.company;

import java.util.HashMap;

public class Const {
    private static HashMap<String, Integer> data = new HashMap<>();
    private static Const instance = null;

    private Const() {

    }

    public static Const get() {
        if (instance == null) instance = new Const();
        return instance;
    }

    public void setConst(String name, Integer value) {
        data.put(name, value);
    }

    public Integer getConst(String name) {
        return data.get(name);
    }

}
