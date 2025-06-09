package com.be.view.warningMessage;

public class WarningConcreteComponent implements WarningComponent {
    @Override
    public void showWarning(String message) {
        System.out.println("경고: " + message);
    }
}
