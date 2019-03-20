package com.example.cse.jokesapi;

public class JokeModel {


    String joke;
    public JokeModel(String joke) {
        this.joke = joke;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
