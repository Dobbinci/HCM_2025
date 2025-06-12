package com.be.text_mode.Implementor;


public class ConsoleDarkMode implements TextModeImplementor {
    @Override
    public void applyColor() {
        System.out.print("\u001B[97m");
    }

    @Override
    public String getModeMessage() {
        return "다크모드로 지정합니다.";
    }

    @Override
    public String getModeName() {
        return "Dark Mode";
    }
}
