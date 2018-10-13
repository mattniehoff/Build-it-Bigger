package com.udacity.gradle.builditbigger.backend;

import com.mattniehoff.jokes.JavaJoker;
import com.mattniehoff.jokes.Joker;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;
    private Joker joker;

    public String getData() {
        if (joker == null) {
            joker = new JavaJoker();
        }

        return joker.getJoke();
    }

    public void setData(String data) {
        myData = data;
    }
}