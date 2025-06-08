package com.be.text_mode;


public class Mode {
    private static final Mode instance = new Mode();
    private TextModeState state;

    public Mode() {
        state = ColorMode.getInstance();
        System.out.println("\u001B[94m");
    }
    public static Mode getInstance() {
        return instance;
    }

    public void setState(TextModeState state) {
        this.state = state;
    }
    public void ColorMode(){
        state.changeColorMode(this);
    }
    public void DarkMode(){
        state.changeDarkMode(this);
    }
}
