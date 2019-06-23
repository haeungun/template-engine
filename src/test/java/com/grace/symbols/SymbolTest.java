package com.grace.symbols;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SymbolTest {

    JSONObject object;
    JSONArray array;

    @Before
    public void setUp() throws ParseException {
        JSONParser parser = new JSONParser();

        this.object = (JSONObject) parser.parse("{\n" +
                "    \"info\":{\n" +
                "        \"name\":{\n" +
                "            \"family\":\"Doe\",\n" +
                "            \"given\":\"John\",\n" +
                "            \"middle\":\"Siva\"\n" +
                "        },\n" +
                "        \"addrs\":[\n" +
                "            {\"addr1\":\"AAA\",\"addr2\":\"BBB\"},\n" +
                "            {\"addr1\":\"123\",\"addr2\":\"234\"}\n" +
                "        ]\n" +
                "    },\n" +
                "    \"membership\":{\n" +
                "        \"grade\": \"SILVER\",\n" +
                "        \"id\": \"67890\"\n" +
                "    },\n" +
                "    \"age\": \"25\"\n" +
                "}\n");
        this.array = (JSONArray) parser.parse("[\n" +
                "      {\"addr1\":\"AAA\",\"addr2\":\"BBB\"},\n" +
                "      {\"addr1\":\"123\",\"addr2\":\"234\"},\n" +
                "      {\"addr1\":\"ABC\",\"addr2\":\"DEF\"},\n" +
                "      {\"addr1\":\"GRACE\",\"addr2\":\"HELLO\"}\n" +
                "    ]");
    }

    @Test
    public void get() {
        Symbol objSymbol = new Symbol(this.object);

        Symbol actual = objSymbol.get("info");

        Symbol name = actual.get("name");
        assertEquals("John", name.get("given").toString());
        assertEquals("Siva", name.get("middle").toString());
        assertEquals("Doe", name.get("family").toString());

        Symbol addr = actual.get("addrs");
        assertEquals(2, addr.getChildren().size());
    }

}