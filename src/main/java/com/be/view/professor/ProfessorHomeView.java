package com.be.view.professor;

import com.be.controller.ProfessorController;
import com.be.view.professor.applicationViewStrategy.*;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@AllArgsConstructor
public class ProfessorHomeView {

    EntityManager em;
    ProfessorController professorController;

    public void show() {
        String[] menuItems = {
                "1. 강의생성 신청 등록",
                "2. 강의생성 신청 조회",
                "3. 강의생성 신청 수정",
                "4. 강의생성 신청 삭제",
                "5. 나의 강의 조회",
                "6. 강의수정 신청 등록",
                "7. 강의삭제 신청 등록",
                "8. 로그아웃"
        };
        Map<Integer, ApplicationViewStrategy> strategyMap = new HashMap<>();
        strategyMap.put(1, new CourseApplicationCreateView(professorController));
        strategyMap.put(2, new CourseApplicationListView(professorController));
        strategyMap.put(3, new CourseApplicationUpdateView(professorController));
        strategyMap.put(4, new CourseApplicationDeleteView(professorController));
        strategyMap.put(5, new CourseListView(professorController));
        strategyMap.put(6, new CourseUpdateRequestView(professorController));
        strategyMap.put(7, new CourseDeleteRequestView(professorController));

        ProfessorMenuContext context = new ProfessorMenuContext();
        Scanner scanner = new Scanner(System.in);

        while (true) { //로그아웃 시 home 로직이 종료될 수 있도록 수정

            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = scanner.nextInt();

            if (choice == 8) {
                System.out.println("로그아웃");
                break;
            }

            ApplicationViewStrategy strategy = strategyMap.get(choice);
            if (strategy != null) {
                context.setStrategy(strategy);           // 전략을 설정하고
                context.show();      // 전략을 실행
            } else {
                System.out.println("잘못된 메뉴입니다.");
            }
        }
    }
}
