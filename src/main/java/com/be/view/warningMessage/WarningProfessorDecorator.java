package com.be.view.warningMessage;

public class WarningProfessorDecorator extends WarningDecorator {

    public WarningProfessorDecorator(WarningComponent component) {
        super(component);
    }

    @Override
    public void showWarning(String message) {
        // 추가적인 경고 로직
        showProfessorWarning();
        super.showWarning(message);
    }

    // 교수 이모지와 함께 관련 경고 메시지 처리.
    public void showProfessorWarning() {
        System.out.println("\n👨‍🏫 교수님~~~, 주의가 필요합니다!");
    }
}
