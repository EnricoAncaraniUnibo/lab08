package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;
import java.io.*;

public class DrawNumberConsoleView implements DrawNumberView{
    private DrawNumberController controller;
    
    @Override
    public void setController(final DrawNumberController observer) {
        this.controller = observer;
    }

    @Override
    public void start(){

    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void result(DrawResult res) {
        switch (res) {
            case YOURS_HIGH, YOURS_LOW -> {
                System.out.println(res.getDescription());
                return;
            }
            case YOU_WON -> System.out.println(res.getDescription()+"\n Starting a new game...");
            case YOU_LOST -> System.out.println(res.getDescription()+"\n Starting a new game...");
            default -> throw new IllegalStateException("Unknown game state");
        }
        controller.resetGame();
    }
    
}
