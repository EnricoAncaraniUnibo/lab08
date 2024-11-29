package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumber;
import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberConsoleView;
import it.unibo.mvc.view.DrawNumberSwingView;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws SecurityException
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        Class dwc = DrawNumberConsoleView.class;
        Class dwv = DrawNumberSwingView.class;
        app.addView((DrawNumberView) dwc.getConstructor().newInstance());
        app.addView((DrawNumberView) dwc.getConstructor().newInstance());
        app.addView((DrawNumberView) dwc.getConstructor().newInstance());
        app.addView((DrawNumberView) dwv.getConstructor().newInstance());
        app.addView((DrawNumberView) dwv.getConstructor().newInstance());
        app.addView((DrawNumberView) dwv.getConstructor().newInstance());
    }
}
