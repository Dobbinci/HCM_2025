package com.be.text_mode;

public class DarkMode implements TextModeState{

    private static DarkMode instance = new DarkMode();
    private DarkMode() { }

    public static DarkMode getInstance() {
        return instance;
    } //싱글톤

    @Override
    public void changeColorMode(Mode state) {
        System.out.println("\u001B[94m" + "컬러모드로 변경합니다.");
        state.setState(ColorMode.getInstance());

    }

    @Override
    public void changeDarkMode(Mode state) {
        System.out.println("\u001B[97m" + "이미 다크모드입니다.");
    }
}
