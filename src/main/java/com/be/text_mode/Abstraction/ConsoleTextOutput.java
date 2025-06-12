package com.be.text_mode.Abstraction;


import com.be.text_mode.Implementor.TextModeImplementor;

public class ConsoleTextOutput implements TextOutput {
    private final TextModeImplementor implementor;

    public ConsoleTextOutput(TextModeImplementor implementor) {
        this.implementor = implementor;
    }


    @Override
    public void display() {
        implementor.applyColor();
        System.out.println(implementor.getModeMessage());
    }



    @Override
    public String getModeName() {
        return implementor.getModeName();
    }

    @Override
    public void printSameModeMessage() {
        implementor.applyColor();
        System.out.println("이미 " + implementor.getModeName() + "입니다.");
    }
}
