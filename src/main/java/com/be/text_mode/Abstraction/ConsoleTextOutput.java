package com.be.text_mode.Abstraction;

import com.be.text_mode.Implementor.ColorModeImplementor;

public class ConsoleTextOutput extends TextOutput {
    public ConsoleTextOutput(ColorModeImplementor implementor) {
        super(implementor);
    }

    @Override
    public void display() {
        implementor.applyColor();
    }
}
