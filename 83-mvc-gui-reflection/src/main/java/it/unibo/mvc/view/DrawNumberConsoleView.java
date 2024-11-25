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
        while (true) {
            this.game();
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void game() {
        System.out.println("Write a number to try, R to reset, and Q to quit");
        BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
        String tr;
        while (true) {
            try {
                tr = reader.readLine();
                switch (tr) {
                    case "R":
                        controller.resetGame();
                        break;
                    case "Q":
                        controller.quit();
                        break;
                    default:
                        try {
                            controller.newAttempt(Integer.parseInt(tr));
                        } catch (Exception exception) {
                            System.out.println("An integer please..");
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println("Unknow command");
            }
        }
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
        while (true) {
            this.game();
        }
    }
    
}
