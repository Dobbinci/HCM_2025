package com.be.text_mode.Implementor;

public class ConsoleColorMode implements TextModeImplementor {
    @Override
    public void applyColor() {
        System.out.print("\u001B[94m");
    }

    @Override
    public String getModeMessage() {
        return "컬러모드로 지정합니다.";
    }

    @Override
    public String getModeName() {
        return "Color Mode";
    }
}

