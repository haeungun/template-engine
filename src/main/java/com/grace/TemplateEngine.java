package com.grace;

import com.grace.io.FileReader;
import com.grace.io.FileWriter;
import com.grace.lexer.LexicalAnalyzer;
import com.grace.symbols.Environment;
import com.grace.symbols.Symbol;
import com.grace.template.Template;
import com.grace.unit.Token;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

public class TemplateEngine {
    public static void main(String[] args) throws ParseException {
        if (args.length != 4) {
            System.out.println("Usage: " +
                    "templatePath dataName dataPath outputPath");
            return;
        }

        String templatePath = args[0];
        String dataName = args[1];
        String dataPath = args[2];
        String outputPath = args[3];

        String templateString = FileReader.read(templatePath);
        String dataString = FileReader.read(dataPath);

        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        List<Token> tokens = analyzer.analyze(templateString);

        JSONParser parser = new JSONParser();
        Environment env = new Environment();
        env.put(dataName, new Symbol(parser.parse(dataString)));

        Template template = new Template(tokens, env);
        String output = template.render();

        System.out.println("\n\n\n==================================");
        System.out.println(output);

        FileWriter.write(outputPath, output);
    }
}
