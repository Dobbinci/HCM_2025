package com.be.view.warningMessage;

public class WarningStaffDecorator extends WarningDecorator {

    public WarningStaffDecorator(WarningComponent component) {
        super(component);
    }

    @Override
    public void showWarning(String message) {
        // 추가적인 경고 로직
        showStaffWarning();
        super.showWarning(message);
    }

    // 직원 이모지와 함께 관련 경고 메시지 처리
    public void showStaffWarning() {
        System.out.println("\n👨‍💼 직원님~!, 주의가 필요합니다!");
    }
}
