package com.grace.symbols;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Symbol {

    private final Object obj;

    public final boolean isObject;
    public final boolean isArray;

    public Symbol(Object obj) {
        this.obj = obj;

        this.isObject = obj instanceof JSONObject;
        this.isArray = obj instanceof JSONArray;
    }

    public boolean contains(String key) {
        if (this.isObject) {
            return ((JSONObject) this.obj).containsKey(key);
        }
        return false;
    }

    public Symbol get(String key) {
        if (this.isObject) {
            return new Symbol(((JSONObject) this.obj).get(key));
        }

        if (this.isArray) {
            int idx = Integer.parseInt(key);
            return new Symbol(((JSONArray) this.obj).get(idx));
        }

        return null;
    }

    public List<Symbol> getChildren() {
        if (this.isObject) {
            return this.getObjectChildren();
        }

        if (this.isArray) {
            return this.getArrayChildren();
        }

        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return this.obj.toString();
    }

    private List<Symbol> getObjectChildren() {
        List<Symbol> symbols = new ArrayList<>();
        JSONObject root = (JSONObject) this.obj;
        for (Object key : root.keySet()) {
            symbols.add(new Symbol(root.get(key)));
        }
        return symbols;
    }

    private List<Symbol> getArrayChildren() {
        List<Symbol> symbols = new ArrayList<>();
        JSONArray root = (JSONArray) this.obj;
        for (int i = 0; i < root.size(); i++) {
            symbols.add(new Symbol(root.get(i)));
        }
        return symbols;
    }
}
