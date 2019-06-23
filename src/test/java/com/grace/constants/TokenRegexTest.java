package com.grace.constants;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.grace.constants.TokenRegex.*;
import static org.junit.Assert.*;

public class TokenRegexTest {

    private Pattern TOKEN_REGEX = Pattern.compile(TOKEN);

    @Test
    public void print() {
        System.out.println(BLOCK_TOKEN);
        System.out.println(FOR_STMT_START);
        System.out.println(FOR_STMT_END);
        System.out.println(VAR_TOKEN);
    }

    @Test
    public void tokenTest() {
        List<String> matchedPatterns = Arrays.asList(
                "<? test ?>",
                "<?= hello ?>",
                "<? for a in arr ?>",
                "<?=world?>",
                "<? for ADDR in USER.info.addrs ?>",
                "<?= ADDR.addr1?>"
        );

        Matcher actual;
        for (String pattern : matchedPatterns) {
            actual = TOKEN_REGEX.matcher(pattern);
            assertTrue(actual.find());
        }

        List<String> notMatchedPatterns = Arrays.asList(
                "< test >",
                "<? not matched >",
                "<= failed ?>"
        );

        for (String pattern : notMatchedPatterns) {
            actual = TOKEN_REGEX.matcher(pattern);
            assertFalse(actual.find());
        }
    }

    @Test
    public void forStartTest() {
        Pattern p = Pattern.compile(FOR_STMT_START);

        List<String> matchedPatterns = Arrays.asList(
                "<?  for  ADDR   in   USER.info.addrs  ?>",
                "<? for ADDR in USER.info.addrs ?>"
        );

        Matcher actual;
        for (String pattern : matchedPatterns) {
            actual = p.matcher(pattern);
            assertTrue(actual.find());
        }

        List<String> notMatchedPatterns = Arrays.asList(
                "for ADDR in USER.info.addrs",
                " for ADDR in USER.info.addrs ",
                "forADDRinUSER",
                "<? for A in ",
                "for ABC DEF  ?>"
        );

        for (String pattern : notMatchedPatterns) {
            actual = p.matcher(pattern);
            assertFalse(actual.find());
        }
    }

    @Test
    public void forEndTest() {
        Pattern p = Pattern.compile(FOR_STMT_END);

        List<String> matchedPatterns = Arrays.asList(
                "<? endfor ?>",
                "<?  endfor  ?>"
        );

        Matcher actual;
        for (String pattern : matchedPatterns) {
            actual = p.matcher(pattern);
            assertTrue(actual.find());
        }

        List<String> notMatchedPatterns = Arrays.asList(
                "<? end for ?>",
                "< endfor >"
        );

        for (String pattern : notMatchedPatterns) {
            actual = p.matcher(pattern);
            assertFalse(actual.find());
        }
    }
}