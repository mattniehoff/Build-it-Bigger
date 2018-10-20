package com.mattniehoff.jokes;

import org.junit.Test;

public class JavaJokerTest {

    @Test
    public void getJavaJokerJoke() {
        Joker joker = new JavaJoker();
        assert(joker.getJoke().length() > 0);
    }
}