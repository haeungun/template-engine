package com.grace.unit;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class VariableTest {

    @Test
    public void varTest() {
        List<String> stmts = Arrays.asList(
                "<?= SUBJECT.name ?>",
                "<?=SUBJECT.name?>"
        );

        for (String stmt : stmts) {
            Variable varToken = new Variable(stmt);
            assertEquals("SUBJECT.name", varToken.attribute);
        }
    }

}