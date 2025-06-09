package com.be.view.professor;

import com.be.controller.ProfessorControllerFacade;
import com.be.view.professor.applicationViewStrategy.*;
import com.be.view.TextModeChangeView;
import com.be.view.warningMessage.WarningComponent;
import com.be.view.warningMessage.WarningConcreteComponent;
import com.be.view.warningMessage.WarningProfessorDecorator;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@AllArgsConstructor
public class ProfessorHomeView {

    EntityManager em;
    ProfessorControllerFacade professorControllerFacade;

    public void show() {
        String[] menuItems = {
                "1. 강의생성 신청 등록",
                "2. 강의생성 신청 조회",
                "3. 강의생성 신청 수정",
                "4. 강의생성 신청 삭제",
                "5. 나의 강의 조회",
                "6. 강의수정 신청 등록",
                "7. 강의삭제 신청 등록",
                "8. 출력 모드 변경",
                "9. 로그 아웃"
        };
        Map<Integer, ApplicationViewStrategy> strategyMap = new HashMap<>();
        strategyMap.put(1, new CourseApplicationCreateView(professorControllerFacade));
        strategyMap.put(2, new CourseApplicationListView(professorControllerFacade));
        strategyMap.put(3, new CourseApplicationUpdateView(professorControllerFacade));
        strategyMap.put(4, new CourseApplicationDeleteView(professorControllerFacade));
        strategyMap.put(5, new CourseListView(professorControllerFacade));
        strategyMap.put(6, new CourseUpdateRequestView(professorControllerFacade));
        strategyMap.put(7, new CourseDeleteRequestView(professorControllerFacade));

        ProfessorMenuContext context = new ProfessorMenuContext();
        Scanner scanner = new Scanner(System.in);

        while (true) { //로그아웃 시 home 로직이 종료될 수 있도록 수정

            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = scanner.nextInt();

            if (choice==8){
                TextModeChangeView.show();
            }

            else if (choice == 9) {
                System.out.println("로그아웃");
                break;
            }

            ApplicationViewStrategy strategy = strategyMap.get(choice);
            if (strategy != null) {
                context.setStrategy(strategy);           // 전략을 설정하고
                context.show();      // 전략을 실행
            } else {
                // 잘못된 메뉴 선택 시 교수용 경고 메시지 출력
                WarningComponent professorWarning = new WarningProfessorDecorator(new WarningConcreteComponent());
                professorWarning.showWarning("잘못된 메뉴입니다.");
            }
        }
    }
}
