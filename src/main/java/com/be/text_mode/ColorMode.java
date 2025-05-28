package com.be.text_mode;

public class ColorMode implements TextModeState {
    private static ColorMode instance = new ColorMode();
    private ColorMode() { }

    public static ColorMode getInstance() {
        return instance;
    } //싱글톤


    @Override
    public void changeColorMode(Mode state) {
        System.out.println("\u001B[94m" +  "이미 컬러모드입니다.");

    }

    @Override
    public void changeDarkMode(Mode state) {
        state.setState(DarkMode.getInstance());
        System.out.println("\u001B[97m" + "다크모드로 변경합니다.");

    }
}