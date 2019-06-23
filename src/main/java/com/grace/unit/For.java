package com.grace.unit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.grace.constants.TokenRegex.FOR_STMT_END;
import static com.grace.constants.TokenRegex.FOR_STMT_START;

public class For extends Block {
    private static final Pattern START = Pattern.compile(FOR_STMT_START);

    public final boolean isEnd;

    public final String each;
    public final String loop;

    public For(String stmt) {
        super(stmt);

        this.isEnd = stmt.matches(FOR_STMT_END);

        if (!this.isEnd) {
            String[] variables = this.extractVariables();
            this.each = variables[0];
            this.loop = variables[1];
        } else {
            this.each = null;
            this.loop = null;
        }
    }

    private String[] extractVariables() {
        String[] variables = new String[2];

        Matcher m = START.matcher(this.statement);
        if (m.find()) {
            variables[0] = m.group(1);
            variables[1] = m.group(2);
        }

        return variables;
    }

    @Override
    public String toString() {
        return "For{each=" + this.each +
                ", loop=" + this.loop +
                ", Token=" + super.toString() + "}";
    }
}
