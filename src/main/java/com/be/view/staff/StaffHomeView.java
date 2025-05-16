package com.be.view.staff;
import java.util.Scanner;
import com.be.model.Staff;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StaffHomeView {

    Scanner scanner = new Scanner(System.in);
    private final EntityManager em;
    public StaffHomeView(EntityManager em) {
        this.em = em;
    }

    public void show(Staff staff) {
        String[] menuItems = {
                "1. 강의 생성",
                "2. 강의 수정",
                "3. 강의 삭제",
                "4. 전체 강의 조회",
                "5. 유저 정보 관리",
                "6. 교수의 강의 수정 신청 목록",
                "7. 교수의 강의 삭제 신청 목록",
                "8. 로그아웃"
        };

        CourseManageView courseManageView = new CourseManageView(em);
        MemberManageView memberManageView = new MemberManageView(em);

        while (true) {
            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    CourseManageView.CourseCreateView courseCreateView = courseManageView.new CourseCreateView();
                    courseCreateView.show(staff);
                    break;
                case 2:
                    CourseManageView.CourseUpdateView courseUpdateView = courseManageView.new CourseUpdateView();
                    courseUpdateView.show(staff);
                    break;
                case 3:
                    // 삭제 로직
                    break;
                case 4:
                    CourseManageView.CreatedCourseView createdCourseView = courseManageView.new CreatedCourseView();
                    createdCourseView.show(staff);
                    break;
                case 5:
                    memberManageView.show(staff);
                    break;
                case 6:
                    CourseManageView.CourseUpdateRequestView courseUpdateRequestView = courseManageView.new CourseUpdateRequestView();
                    courseUpdateRequestView.show(staff);
                    break;
                case 7:
                    CourseManageView.CourseDeleteRequestView courseDeleteRequestView = courseManageView.new CourseDeleteRequestView();
                    courseDeleteRequestView.show(staff);
            }
            if (choice == 8) {
                break;
            }
        }
    }
}

