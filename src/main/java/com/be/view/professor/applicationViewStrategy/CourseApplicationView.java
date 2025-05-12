package com.be.view.professor.applicationViewStrategy;

import com.be.control.CourseManager;
import com.be.model.CourseApplication;
import com.be.model.Member;
import com.be.model.Professor;

import java.util.ArrayList;
import java.util.Scanner;

public class CourseApplicationView {

    // 강의 신청서 작성 화면
    public static class CreateView implements ApplicationViewStrategy {

        public void show(Professor professor) {
            Scanner scanner = new Scanner(System.in);

            System.out.println(" -- 강의 등록 -- \n");
            System.out.print("강의명 : ");
            String courseName = scanner.nextLine();
            System.out.print("교수명 : ");
            String professorName = scanner.nextLine();
            System.out.print("학기 : ");
            String semester = scanner.nextLine();
            System.out.print("학점 : ");
            String credit = scanner.nextLine();
            System.out.print("정원 : ");
            String capacity = scanner.nextLine();
            System.out.print("강의실 : ");
            String classroom = scanner.nextLine();
            System.out.print("강의계획서 : ");
            String content = scanner.nextLine();

            //Apply create course
            professor.applyCreateCourse(courseName, professorName, semester, credit, capacity, classroom, content);
        }
    }

    // 강의 신청서 삭제 화면
    public static class DeleteView implements ApplicationViewStrategy {

        public void show(Professor professor) {
            Scanner scanner = new Scanner(System.in);

            int index;
            CourseManager manager = CourseManager.getInstance();
            if (!manager.getCourseApplications().isEmpty()) {
                //강의 목록 조회
                ListView profCourseApplicationViewStrategy = new ListView();
                profCourseApplicationViewStrategy.show(professor);

                System.out.print("삭제할 신청서의 번호 입력 : ");
                index = scanner.nextInt();

                //Apply delete course
                professor.applyDeleteCourse(--index);
            } else {
                System.out.println("신청 목록이 비어있습니다.");
            }
        }
    }

    // 강의 신청서 수정 화면
    public static class UpdateView implements ApplicationViewStrategy {

        public void show(Professor professor) {

            // 강의 수정 로직
            Scanner scanner = new Scanner(System.in);
            int index;
            CourseManager manager = CourseManager.getInstance();
            if (!manager.getCourseApplications().isEmpty()) {
                //강의 목록 조회
                ListView profCourseApplicationViewStrategy = new ListView();
                profCourseApplicationViewStrategy.show(professor);

                System.out.print("수정할 신청서의 번호 입력 : ");
                index = scanner.nextInt();

                //Apply update course
                professor.applyUpdateCourse(--index);
            } else {
                System.out.println("신청 목록이 비어있습니다.");
            }
        }
    }

    // 강의 신청서 리스트 화면
    public static class ListView implements ApplicationViewStrategy {

        @Override
        public void show(Professor professor) {
            CourseManager manager = CourseManager.getInstance();
            ArrayList<CourseApplication> arrayList = manager.getCourseApplications();
            System.out.println(" -- 강의 신청서 목록 -- ");

            //강의 목록 반환 로직
            if (!arrayList.isEmpty()) {
                int widthNo = 4;
                int widthName = 15;
                int widthProfessor = 10;
                int widthSemester = 10;
                int widthCredit = 10;
                int widthCapacity = 10;
                int widthClassroom = 10;
                int widthContent = 10;

                // 전체 너비 계산 (필드 + 구분자)
                int totalWidth = widthNo + widthName + widthProfessor + widthSemester
                        + widthCredit + widthCapacity + widthClassroom + widthContent
                        + 9 * 3   // 컬럼 사이 구분자(" | ")
                        + 2;      // 양쪽 테두리("|","|")

                // 구분선 생성
                String line = String.format("+%s+", "-".repeat(totalWidth - 2));

                // 헤더 출력
                System.out.println(line);
                System.out.printf("| %-" + widthNo + "s | "
                                + "%-" + widthName + "s | "
                                + "%-" + widthProfessor + "s | "
                                + "%-" + widthSemester + "s | "
                                + "%-" + widthCredit + "s | "
                                + "%-" + widthCapacity + "s | "
                                + "%-" + widthClassroom + "s | "
                                + "%-" + widthContent + "s |\n",
                        "No", "Course Name", "Professor", "Semester",
                        "Credit", "Capacity", "Classroom", "Content"
                );
                System.out.println(line);

                // 데이터 출력
                int index = 0;
                for (CourseApplication courseApplication : arrayList) {
                    System.out.printf("| %" + widthNo + "s | "
                                    + "%-" + widthName + "s | "
                                    + "%-" + widthProfessor + "s | "
                                    + "%-" + widthSemester + "s | "
                                    + "%-" + widthCredit + "s | "
                                    + "%-" + widthCapacity + "s | "
                                    + "%-" + widthClassroom + "s | "
                                    + "%-" + widthContent + "s |\n",
                            ++index,
                            courseApplication.getCourseName(),
                            courseApplication.getProfessorName(),
                            courseApplication.getSemester(),
                            courseApplication.getCredit(),
                            courseApplication.getCapacity(),
                            courseApplication.getClassroom(),
                            courseApplication.getContent()
                    );
                }
                System.out.println(line);
            }
            else {
                System.out.println("신청 목록이 비었습니다.");
            }
        }
    }

}
