package com.grace.unit;

import static com.grace.constants.TokenRegex.FOR_STMT_END;
import static com.grace.constants.TokenRegex.FOR_STMT_START;

public class Block extends Token {

    public final boolean isEnd;
    public final boolean isFor;

    public Block(String statement) {
        super(TokenType.BLOCK, statement);

        this.isEnd = this.isEndBlock();
        this.isFor = this.isForBlock();
    }

    private boolean isEndBlock() {
        return this.statement.matches(FOR_STMT_END);
    }

    private boolean isForBlock() {
        return this.statement.matches(FOR_STMT_START) ||
                this.statement.matches(FOR_STMT_END);
    }

}
