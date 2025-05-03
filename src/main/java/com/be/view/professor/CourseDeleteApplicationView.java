package com.be.view.professor;

import com.be.control.CourseManager;
import com.be.service.Professor;
import com.be.view.View;

import java.util.Scanner;

public class CourseDeleteApplicationView implements View {

    private Professor professor;
    private MyCourseApplicationView myCourseApplicationView;

    public CourseDeleteApplicationView(Professor professor, MyCourseApplicationView myCourseApplicationView) {
        this.professor = professor;
        this.myCourseApplicationView = myCourseApplicationView;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        int index;

        if (!CourseManager.getCourseApplications().isEmpty()) {
            //강의 목록 조회
            myCourseApplicationView.show();

            System.out.print("삭제할 신청서의 번호 입력 : ");
            index = scanner.nextInt();

            //삭제 요청
            professor.applyDeleteCourse(--index);
        } else {
            System.out.println("신청 목록이 비어있습니다.");
        }
    }
}
