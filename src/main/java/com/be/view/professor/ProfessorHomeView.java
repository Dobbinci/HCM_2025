package com.be.view.professor;

import com.be.service.Professor;
import com.be.view.LoginView;
import com.be.view.professor.applicationViewStrategy.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProfessorHomeView {

    public static void show(Professor professor) {
        String[] menuItems = {
                "1. 강의 등록",
                "2. 강의 조회",
                "3. 강의 수정",
                "4. 강의 삭제",
                "5. 로그아웃"
        };
        Map<Integer, ApplicationViewStrategy> strategyMap = new HashMap<>();
        strategyMap.put(1, new CourseCreateApplicationViewStrategy());
        strategyMap.put(2,  new ProfCourseApplicationViewStrategy());
        strategyMap.put(3, new CourseUpdateApplicationViewStrategy());
        strategyMap.put(4, new CourseDeleteApplicationViewStrategy());

        ProfessorMenuContext context = new ProfessorMenuContext();
        Scanner scanner = new Scanner(System.in);

        while (true) { //로그아웃 시 home 로직이 종료될 수 있도록 수정

            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = scanner.nextInt();

            if (choice == 5) {
                System.out.println("로그아웃");
                break;
            }

            ApplicationViewStrategy strategy = strategyMap.get(choice);
            if (strategy != null) {
                context.setStrategy(strategy);           // 전략을 설정하고
                context.show(professor);      // 전략을 실행
            } else {
                System.out.println("잘못된 메뉴입니다.");
            }
        }
    }
}
