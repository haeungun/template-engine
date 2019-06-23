package com.grace.symbols;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

import static com.grace.constants.TokenRegex.WILD_CARD;

public class Environment {

    private final Map<String, Symbol> symbols;
    protected Environment prev;

    public Environment() {
        this.symbols = new HashMap<>();
        this.prev = null;
    }

    public Environment(Environment prev) {
        this.symbols = new HashMap<>();
        this.prev = prev;
    }

    public void put(String key, Symbol sym) {
        this.symbols.put(key, sym);
    }

    public Symbol get(String key) {
        for (Environment e = this; e != null; e = e.prev)  {
            Symbol found = e.symbols.get(key);
            if (!Objects.isNull(found)) {
                return found;
            }
        }
        return null;
    }

    public Symbol find(String attribute) {
        Symbol symbol = null;
        String[] attributes = attribute.split("\\.");

        if (this.hasWildCard(attributes)) {
            return this.lookUp(attributes);
        }

        for (String attr : attributes) {
            if (Objects.isNull(symbol)) {
                symbol = this.get(attr);
            } else {
                symbol = symbol.get(attr);
            }
        }
        return symbol;
    }

    @Override
    public String toString() {
        return "Environment{symbols=" + this.symbols + "}";
    }

    private boolean hasWildCard(String[] attributes) {
        for (String attr : attributes) {
            if (this.isWildCard(attr)) {
                return true;
            }
        }
        return false;
    }

    private boolean isWildCard(String s) {
        return s.matches(WILD_CARD);
    }

    private Symbol lookUp(String[] attributes) {
        JSONObject result = new JSONObject();

        List<Symbol> candidates = null;
        Symbol symbol = null;

        int i;
        for (i = 0; i < attributes.length; i++) {
            String attr = attributes[i];
            if (this.isWildCard(attr)) {
                if (symbol == null) {
                    candidates = new ArrayList<>();
                    for (String k : this.symbols.keySet()) {
                        candidates.add(this.symbols.get(k));
                    }
                } else {
                    candidates = symbol.getChildren();
                }
                i++;
                break;
            }

            if (symbol == null) {
                symbol = this.get(attr);
            } else {
                symbol = symbol.get(attr);
            }
        }

        int count = 0;
        List<String> subAttr = new ArrayList<>();
        for (;i < attributes.length; i++) {
            subAttr.add(attributes[i]);
        }

        for (Symbol candidate : candidates) {
            for (String s : subAttr) {
                candidate = candidate.get(s);
            }
            result.put(count++, candidate.toString());
        }

        return new Symbol(result);
    }
}
