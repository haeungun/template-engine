package com.grace.constants;

import static com.grace.constants.TokenRegex.Keyword.*;

public class TokenRegex {
    public static final String WORD = "([\\w]+)";
    public static final String WORD_WITH_ATTR = "([\\w.\\*]+)";

    public static final String WILD_CARD = "\\*";

    public static final String VAR_TOKEN_START = "<\\?=[\\s]*";
    public static final String VAR_TOKEN_END = "[\\s]*\\?>";
    public static final String VAR_TOKEN = String.format("%s(.+?)%s",
            VAR_TOKEN_START, VAR_TOKEN_END);

    public static final String BLOCK_TOKEN_START = "[ |\t]*<\\?[\\s]+";
    public static final String BLOCK_TOKEN_END = "[\\s]+\\?>[\n]?";
    public static final String BLOCK_TOKEN = String.format("%s(.+?)%s",
            BLOCK_TOKEN_START, BLOCK_TOKEN_END);

    public static final String TOKEN = String.format("%s|%s",
            VAR_TOKEN, BLOCK_TOKEN);

    public static final String FOR_STMT_START = String.format(
            "%s%s\\s+%s\\s+%s\\s+%s%s",
            BLOCK_TOKEN_START, FOR, WORD, IN, WORD_WITH_ATTR, BLOCK_TOKEN_END
    );

    public static final String FOR_STMT_END = String.format(
            "%s%s%s", BLOCK_TOKEN_START, END_FOR, BLOCK_TOKEN_END
    );

    public class Keyword {
        public static final String FOR = "for";
        public static final String IN = "in";
        public static final String END_FOR = "endfor";
    }
}
