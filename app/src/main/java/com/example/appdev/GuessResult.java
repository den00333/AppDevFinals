package com.example.appdev;

public class GuessResult {
    private int userGuess;
    private String output;

    public GuessResult(int userGuess, String output) {
        this.userGuess = userGuess;
        this.output = output;
    }

    public int getUserGuess() {
        return userGuess;
    }

    public String getOutput() {
        return output;
    }

}
