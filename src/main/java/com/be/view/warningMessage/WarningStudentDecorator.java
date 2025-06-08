package com.be.view.warningMessage;

public class WarningStudentDecorator extends WarningDecorator {

    public WarningStudentDecorator(WarningComponent component) {
        super(component);
    }

    @Override
    public void showWarning(String message) {
        // 추가적인 경고 로직
        showStudentWarning();
        super.showWarning(message);
    }

    // 학생 이모지와 함께 관련 경고 메시지 처리
    private void showStudentWarning() {
        System.out.println("\n👨‍🎓 저기 학생!, 주의가 필요합니다!");
    }
}
