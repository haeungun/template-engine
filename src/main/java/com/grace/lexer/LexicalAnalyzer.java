package com.grace.lexer;

import com.grace.unit.For;
import com.grace.unit.Token;
import com.grace.unit.TokenType;
import com.grace.unit.Variable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.grace.constants.TokenRegex.*;

public class LexicalAnalyzer {
    private static final Pattern TOKEN_PATTERN = Pattern.compile(TOKEN);

    public List<Token> analyze(String s) {
        List<Token> tokens = new ArrayList<>();

        Matcher m = TOKEN_PATTERN.matcher(s);

        int idx = 0;
        while (m.find()) {
            int start = m.start();
            if (idx != start) {
                String nonDelim = s.substring(idx, start);
                tokens.add(new Token(TokenType.NONE, nonDelim));
            }

            String delimiter = m.group();

            if (delimiter.matches(BLOCK_TOKEN)) {
                if (this.isForStatement(delimiter)) {
                    tokens.add(new For(delimiter));
                }
            }

            if (delimiter.matches(VAR_TOKEN)) {
                tokens.add(new Variable(delimiter));
            }

            int end = m.end();
            idx = end;
        }

        if (idx != s.length()) {
            String nonDelim = s.substring(idx);
            tokens.add(new Token(TokenType.NONE, nonDelim));
        }

        return tokens;
    }

    private boolean isForStatement(String s) {
        return s.matches(FOR_STMT_START) || s.matches(FOR_STMT_END);
    }
}

