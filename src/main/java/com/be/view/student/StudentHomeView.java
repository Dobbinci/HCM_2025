package com.be.view.student;


import com.be.controller.Command;
import com.be.controller.DropCourseCommand;
import com.be.controller.RemoteControl;
import com.be.controller.StudentControllerFacade;
import com.be.dto.CourseDTO;
import com.be.dto.EnrolledCourseDTO;
import com.be.view.courseDisplayDeco.ConcreteCourseDisplay;
import com.be.view.courseDisplayDeco.CourseDisplayComponent;
import com.be.view.courseDisplayDeco.CourseDisplayNormalDeco;
import com.be.view.textModeChangeView;
import com.be.view.warningMessage.WarningComponent;
import com.be.view.warningMessage.WarningConcreteComponent;
import com.be.view.warningMessage.WarningProfessorDecorator;
import com.be.view.warningMessage.WarningStudentDecorator;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
public class StudentHomeView {
    private final EntityManager em;
    private final StudentControllerFacade studentControllerFacade;

    public void show() {

        String[] menuItems = {
                "1. 수강 신청",
                "2. 수강 취소",
                "3. 수강목록 조회",
                "4. 개설강의 조회",
                "5. 출력 모드 변경",
                "6. 로그 아웃"
        };

        while (true) {
            System.out.println("메뉴");
            for (String items : menuItems) {
                System.out.println(items);
            }
            int choice = new Scanner(System.in).nextInt();

            switch (choice) {
                case 1:
                    CourseEnrollView();
                    break;
                case 2:
                    CourseDropView();
                    break;
                case 3:
                    EnrolledCourseListView();
                    break;
                case 4:
                    openCourseView();
                    break;
                case 5:
                    textModeChangeView.show();
                case 6:
                    return;
                default:
                    // 잘못된 메뉴 선택 시 학생용 경고 메시지 출력
                    WarningComponent studentWarning = new WarningStudentDecorator(new WarningConcreteComponent());
                    studentWarning.showWarning("잘못된 메뉴입니다.");
            }
        }
    }

    public void CourseEnrollView() {
        List<CourseDTO> courseDTOs = studentControllerFacade.loadCourseList();
        if (!courseDTOs.isEmpty()) {
            int widthNo = 4;
            int widthName = 15;
            int widthProfessor = 10;
            int widthSemester = 10;
            int widthCredit = 10;
            int widthCapacity = 10;
            int widthClassroom = 10;
            int widthContent = 10;

            int totalWidth = widthNo + widthName + widthProfessor + widthSemester
                    + widthCredit + widthCapacity + widthClassroom + widthContent
                    + 9 * 3
                    + 2;
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
            for (CourseDTO courseDTO : courseDTOs) {
                System.out.printf("| %" + widthNo + "s | "
                                + "%-" + widthName + "s | "
                                + "%-" + widthProfessor + "s | "
                                + "%-" + widthSemester + "s | "
                                + "%-" + widthCredit + "s | "
                                + "%-" + widthCapacity + "s | "
                                + "%-" + widthClassroom + "s | "
                                + "%-" + widthContent + "s |\n",
                        ++index, courseDTO.getCourseName(),
                        courseDTO.getProfessorName(), courseDTO.getSemester(),
                        courseDTO.getCredit(), courseDTO.getCapacity(),
                        courseDTO.getClassroom(), courseDTO.getContent()
                );
            }
            System.out.println(line);

            System.out.println("강의를 검색하시겠습니가? (Y/N): ");
            String choice;
            Scanner scanner = new Scanner(System.in);
            choice = scanner.next();
            scanner.nextLine();

            if (choice.equals("Y")) {
                System.out.print("과목명을 입력하세요 :");
                String keyword;
                keyword = scanner.nextLine();

                List<CourseDTO> filteredCourses = studentControllerFacade.search(keyword);

                if (filteredCourses.isEmpty()) {
                    System.out.println("검색 결과가 없습니다.");
                } else {
                    for (int i = 0; i < filteredCourses.size(); i++) {
                        CourseDTO course = filteredCourses.get(i);
                        System.out.printf("[%d]. 강의명: %s | 교수명: %s | 학기: %s | 학점: %s | 정원: %s | 강의실: %s | 강의내용: %s\n",
                                i + 1, course.getCourseName(),
                                course.getProfessorName(), course.getSemester(),
                                course.getCredit(), course.getCapacity(),
                                course.getClassroom(), course.getContent());
                    }

                    System.out.print("수강 신청할 강의의 번호를 입력하세요: ");
                    int idx = scanner.nextInt() - 1;

                    if (idx >= 0 && idx < filteredCourses.size()) {
                        CourseDTO selected = filteredCourses.get(idx);
                        int originalIndex = filteredCourses.indexOf(selected);
                        if (originalIndex != -1) {
                            studentControllerFacade.enrollCourse(originalIndex);
                            System.out.println("수강 신청 완료!");
                        } else {
                            System.out.println("강의를 찾을 수 없습니다.");
                        }
                    } else {
                        System.out.println("잘못된 번호입니다.");
                    }
                }
            } else {
                System.out.println("수강 신청할 강의의 번호를 입력하세요: ");
                int courseIndex = new Scanner(System.in).nextInt() - 1;
                if (courseIndex >= 0 && courseIndex < courseDTOs.size()) {
                    studentControllerFacade.enrollCourse(courseIndex);
                    System.out.println("강의 수강 신청 완료!\n");
                } else {
                    System.out.println("잘못된 번호입니다.");
                }
            }
        }
    }

    public void CourseDropView() {
        if (EnrolledCourseListView()) {
            System.out.println("수강 취소할 강의의 번호를 입력하세요: ");
            int courseIndex = new Scanner(System.in).nextInt() - 1;
            if (courseIndex >= 0) {

                Command dropCourse = new DropCourseCommand(studentControllerFacade, courseIndex);
                RemoteControl remoteControl = new RemoteControl();
                remoteControl.setCommand(dropCourse);
                remoteControl.pressButton();

                //studentController.dropCourse(courseIndex);
                EnrolledCourseListView();
                System.out.println("수강 취소되었습니다.");

                String yesOrNo = "";
                System.out.println("실수로 취소하셨나요? 다시 복구할 수 있어요!");
                Scanner scanner = new Scanner(System.in);
                System.out.print("복구하시겠습니까? (Y/N): ");
                yesOrNo = scanner.nextLine();

                if (yesOrNo.equals("Y")) {
                    remoteControl.pressUndo();
                    EnrolledCourseListView();
                    System.out.println("복구되었습니다.");
                }

            } else {
                System.out.println("잘못된 번호입니다.");
            }
        }
    }

    public boolean EnrolledCourseListView() {
        List<EnrolledCourseDTO> enrolledCourseDTOs = studentControllerFacade.loadEnrolledCourseList();
        if (!enrolledCourseDTOs.isEmpty()) {
            int widthNo = 4;
            int widthName = 15;
            int widthProfessor = 10;
            int widthSemester = 10;
            int widthCredit = 10;
            int widthCapacity = 10;
            int widthClassroom = 10;
            int widthContent = 10;

            int totalWidth = widthNo + widthName + widthProfessor + widthSemester
                    + widthCredit + widthCapacity + widthClassroom + widthContent
                    + 9 * 3
                    + 2;
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
            for (EnrolledCourseDTO enrolledCourseDTO : enrolledCourseDTOs) {
                System.out.printf("| %" + widthNo + "s | "
                                + "%-" + widthName + "s | "
                                + "%-" + widthProfessor + "s | "
                                + "%-" + widthSemester + "s | "
                                + "%-" + widthCredit + "s | "
                                + "%-" + widthCapacity + "s | "
                                + "%-" + widthClassroom + "s | "
                                + "%-" + widthContent + "s |\n",
                        ++index, enrolledCourseDTO.getCourseName(),
                        enrolledCourseDTO.getProfessorName(), enrolledCourseDTO.getSemester(),
                        enrolledCourseDTO.getCredit(), enrolledCourseDTO.getCapacity(),
                        enrolledCourseDTO.getClassroom(), enrolledCourseDTO.getContent()
                );
            }
            System.out.println(line);
            return true;
        } else {
            System.out.println("수강 신청한 강의가 없습니다.");

            return false;
        }

    }

    public void openCourseView() {
        List<CourseDTO> courseDTOs = studentControllerFacade.loadCourseList();
        if (!courseDTOs.isEmpty()) {
            // 일반인 전용 강의 목록 출력
            CourseDisplayComponent courseDisplayNormalDeco = new CourseDisplayNormalDeco(new ConcreteCourseDisplay());
            courseDisplayNormalDeco.displayCourse(courseDTOs);

            // 사용자 입력
            System.out.print("자세히 볼 강의를 선택하세요 : ");
            Scanner scanner = new Scanner(System.in);
            int selectedIndex = scanner.nextInt();

            // 인덱스 유효성 검사 후 상세 정보 출력
            if (selectedIndex >= 1 && selectedIndex <= courseDTOs.size()) {
                CourseDTO selectedCourse = courseDTOs.get(selectedIndex - 1);
                System.out.println("\n[상세 강의 정보]");
                System.out.println("강의명 : " + selectedCourse.getCourseName());
                System.out.println("교수명 : " + selectedCourse.getProfessorName());
                System.out.println("학기   : " + selectedCourse.getSemester());
                System.out.println("학점   : " + selectedCourse.getCredit());
                System.out.println("정원   : " + selectedCourse.getCapacity());
                System.out.println("강의실 : " + selectedCourse.getClassroom());
                System.out.println("설명   : " + selectedCourse.getContent());
            } else {
                System.out.println("잘못된 번호입니다.");
            }
        } else {
            System.out.println("강의가 없습니다.");
        }
    }

}