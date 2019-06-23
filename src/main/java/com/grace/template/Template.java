package com.grace.template;

import com.grace.symbols.Environment;
import com.grace.symbols.Symbol;
import com.grace.unit.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Template {

    private final List<Token> tokens;
    private final Environment env;

    public Template(List<Token> tokens, Environment env) {
        this.tokens = tokens;
        this.env = env;
        System.out.println(env.toString());
    }

    public String render() {
        StringBuilder result = new StringBuilder();

        Iterator<Token> tokenIterator = this.tokens.iterator();
        while (tokenIterator.hasNext()) {
            Token token = tokenIterator.next();

            String output;
            switch (token.type) {
                case VAR:
                    System.out.println(token.statement);
                    output = this.handleVariable(new Variable(token.statement));
                    break;
                case BLOCK:
                    output = this.handleBlock(tokenIterator, this.env, token);
                    break;
                default:
                    output = token.statement;
            }

            result.append(output);
        }

        return result.toString();
    }

    private String handleVariable(Variable var) {
        Symbol symbol = this.env.find(var.attribute);
        return symbol.toString();
    }

    private String handleBlock(Iterator<Token> it, Environment prev, Token token) {
        return this.handleForLoop(it, prev, new For(token.statement));
    }

    private String handleForLoop(Iterator<Token> it, Environment prev, For forToken) {
        StringBuilder builder = new StringBuilder();

        Symbol symbol = prev.find(forToken.loop);
        List<Symbol> children = symbol.getChildren();

        List<Token> innerBlock = new ArrayList<>();

        int depth = 0;
        while (it.hasNext()) {
            Token currToken = it.next();
            if (currToken.isBlock()) {
                Block blockToken = new Block(currToken.statement);
                if (blockToken.isEnd) {
                    if (depth == 0) {
                        break;
                    } else {
                        depth--;
                    }
                } else {
                    depth++;
                }
            }
            innerBlock.add(currToken);
        }

        for (Symbol child : children) {
            Environment e = new Environment(prev);
            e.put(forToken.each, child);

            Template template = new Template(innerBlock, e);
            builder.append(template.render());
        }

        return builder.toString();
    }
}
