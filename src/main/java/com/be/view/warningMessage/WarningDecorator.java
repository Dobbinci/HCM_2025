package com.be.view.warningMessage;


public abstract class WarningDecorator implements WarningComponent {
    protected WarningComponent component;

    public WarningDecorator(WarningComponent component) {
        this.component = component;
    }

    @Override
    public void showWarning(String message) {
        component.showWarning(message);
    }
}
