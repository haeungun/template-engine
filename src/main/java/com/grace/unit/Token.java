package com.grace.unit;

public class Token {

    public final TokenType type;
    public final String statement;

    public Token(TokenType type, String statement) {
        this.type = type;
        this.statement = statement;
    }

    public boolean isBlock() {
        return TokenType.BLOCK.equals(this.type);
    }

    public boolean isVariable() {
        return TokenType.VAR.equals(this.type);
    }

    @Override
    public String toString() {
        return "Token{type=" + this.type.name() +
                ", statement=" + this.statement + "}";
    }

}
