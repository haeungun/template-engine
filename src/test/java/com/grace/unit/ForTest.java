package com.grace.unit;

import org.junit.Test;

import static org.junit.Assert.*;

public class ForTest {

    @Test
    public void forStartTest() {
        String stmt = "<? for SUBJECT in QUIZ.subjects ?>";

        For forToken = new For(stmt);

        assertEquals("SUBJECT", forToken.each);
        assertEquals("QUIZ.subjects", forToken.loop);
        assertFalse(forToken.isEnd);
    }

    @Test
    public void forStartTest2() {
        String stmt = "<? for Q in SUBJECT.quizzes ?>";

        For forToken = new For(stmt);

        assertEquals("Q", forToken.each);
        assertEquals("SUBJECT.quizzes", forToken.loop);
        assertFalse(forToken.isEnd);
    }

    @Test
    public void forEndTest() {
        String stmt = "<? endfor ?>";

        For forToken = new For(stmt);

        assertTrue(forToken.isEnd);
    }
}