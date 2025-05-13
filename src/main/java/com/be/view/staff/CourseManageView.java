package com.be.view.staff;
import com.be.controller.StaffController;
import com.be.model.Course;
import com.be.model.CourseApplication;
import com.be.model.Member;
import com.be.model.Staff;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class CourseManageView {

    Scanner scanner = new Scanner(System.in);
    private final EntityManager em;
    private final StaffController staffController;

    public CourseManageView(EntityManager em) {
        this.em = em;
        this.staffController = new StaffController(em);
    }

    //강의 생성 화면
    public class CourseCreateView {
        public void show(Staff staff) {
            long id;

            //교수가 작성한 신청서 조회
            List<CourseApplication> courseApplications = staffController.getCourseApplications();
            for (CourseApplication courseApplication : courseApplications) {
                System.out.println(courseApplication.getId());
                System.out.println(courseApplication.getCourseName());
                System.out.println(courseApplication.getProfessor());
                System.out.println(courseApplication.getClassroom());
            }

            System.out.println("생성할 강의 번호 선택 : ");
            id = scanner.nextLong();
            staffController.createCourse(id);
        }
    }

    //강의 수정 화면
    public class CourseUpdateView {
        public void show(Staff staff) {
            long id;

            //개설된 강의 조회
            CourseManageView courseManageView = new CourseManageView(em);
            CourseManageView.CourseCreateView view = courseManageView.new CourseCreateView();

            System.out.println("수정할 강의 번호 선택 : ");
            id = scanner.nextLong();
            staffController.updateCourse(id);
        }
    }

    //개설된 강의 목록 화면
    public class CreatedCourseView {
        public void show(Member member) {

            System.out.println(" -- 개설된 강의 목록 -- ");
            List<Course> courseList = staffController.getCreatedCourse();
            for (Course course : courseList) {
                System.out.println(course.getId());
                System.out.println(course.getCourseName());
                System.out.println(course.getProfessor());
                System.out.println(course.getClassroom());
            }
        }
    }

}
