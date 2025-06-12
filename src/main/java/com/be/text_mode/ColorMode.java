package com.be.text_mode;


import com.be.text_mode.Abstraction.ConsoleTextOutput;
import com.be.text_mode.Implementor.ConsoleColorMode;

public class ColorMode implements TextModeState {
    private static final ColorMode instance = new ColorMode();

    private ColorMode() {}

    public static ColorMode getInstance() {
        return instance;
    }

    @Override
    public void changeColorMode(Mode mode) {
        if (mode.getCurrentMode().equals("Color Mode")) {
            mode.printSameModeMessage();
            return;
        }
        mode.setState(ColorMode.getInstance());
        mode.setOutput(new ConsoleTextOutput(new ConsoleColorMode()));
        mode.getOutput().display();
    }

    @Override
    public void changeDarkMode(Mode mode) {
        mode.setState(DarkMode.getInstance());
        mode.setOutput(new ConsoleTextOutput(new com.be.text_mode.Implementor.ConsoleDarkMode()));
        mode.getOutput().display();
    }
}

