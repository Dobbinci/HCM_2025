package com.be.text_mode;


import com.be.text_mode.Abstraction.ConsoleTextOutput;
import com.be.text_mode.Abstraction.TextOutput;
import com.be.text_mode.Implementor.ConsoleColorMode;


public class Mode {
    private static final Mode instance = new Mode();
    private TextModeState state;
    private TextOutput output;

    public Mode() {
        this.state = ColorMode.getInstance();
        this.output = new ConsoleTextOutput(new ConsoleColorMode());
        output.display();
    }

    public static Mode getInstance() {
        return instance;}

    public void setState(TextModeState state) {
        this.state = state;}

    public void setOutput(TextOutput output) {
        this.output = output;}

    public TextOutput getOutput() {
        return output;}

    public String getCurrentMode() {
        return output.getModeName();}

    public void ColorMode() {
        state.changeColorMode(this);}

    public void DarkMode() {
        state.changeDarkMode(this);}

    public void printSameModeMessage() {
        output.printSameModeMessage();}

}

