package com.grace.unit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.grace.constants.TokenRegex.VAR_TOKEN;

public class Variable extends Token {
    private static final Pattern p = Pattern.compile(VAR_TOKEN);

    public final String attribute;

    public Variable(String stmt) {
        super(TokenType.VAR, stmt);

        this.attribute = this.extractAttribute();
    }

    private String extractAttribute() {
        Matcher m = p.matcher(this.statement);

        if (m.find()) {
            return m.group(1);
        }

        return null;
    }

    @Override
    public String toString() {
        return "Variable{attribute=" + this.attribute +
                ", Token=" + super.toString() + "}";
    }
}
