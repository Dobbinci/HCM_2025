package com.be.text_mode;


import com.be.text_mode.Abstraction.ConsoleTextOutput;
import com.be.text_mode.Implementor.ConsoleDarkMode;
import com.be.text_mode.Implementor.ConsoleColorMode;

public class DarkMode implements TextModeState {
    private static final DarkMode instance = new DarkMode();

    private DarkMode() {}

    public static DarkMode getInstance() {
        return instance;
    }

    @Override
    public void changeColorMode(Mode mode) {
        mode.setState(ColorMode.getInstance());
        mode.setOutput(new ConsoleTextOutput(new ConsoleColorMode()));
        mode.getOutput().display();
    }

    @Override
    public void changeDarkMode(Mode mode) {
        if (mode.getCurrentMode().equals("Dark Mode")) {
            mode.printSameModeMessage();
            return;
        }
        mode.setState(DarkMode.getInstance());
        mode.setOutput(new ConsoleTextOutput(new ConsoleDarkMode()));
        mode.getOutput().display();
    }
}

