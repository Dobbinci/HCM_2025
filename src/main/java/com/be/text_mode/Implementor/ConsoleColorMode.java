package com.be.text_mode.Implementor;

public class ConsoleColorMode implements ColorModeImplementor {
    @Override
    public void applyColor() {
        System.out.println("\u001B[94m" + "컬러 모드로 설정합니다.");
    }

    @Override
    public String getModeName() {
        return "COLOR";
    }
}

