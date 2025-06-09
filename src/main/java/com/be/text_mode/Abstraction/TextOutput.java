package com.be.text_mode.Abstraction;

import com.be.text_mode.Implementor.ColorModeImplementor;

public abstract class TextOutput {
    protected ColorModeImplementor implementor;

    public TextOutput(ColorModeImplementor implementor) {
        this.implementor = implementor;
    }

    public abstract void display();
    public String getModeName() {
        return implementor.getModeName();
    }
}
