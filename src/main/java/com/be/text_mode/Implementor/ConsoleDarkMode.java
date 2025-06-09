package com.be.text_mode.Implementor;

public class ConsoleDarkMode implements ColorModeImplementor {
    @Override
    public void applyColor() {
        System.out.println("\u001B[97m" + "다크 모드로 설정합니다.");
    }

    @Override
    public String getModeName() {
        return "DARK";
    }
}
