package com.be.controller;

import com.be.dto.CourseDTO;
import com.be.dto.EnrolledCourseDTO;
import com.be.model.Course;
import com.be.model.EnrolledCourse;
import com.be.model.Student;
import com.be.repository.CourseRepository;
import com.be.repository.GenericRepository;
import com.be.repository.impl.CourseRepoImpl;
import com.be.repository.impl.GenericRepoImpl;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class StudentController implements BaseController {

    private final EntityManager em;

    public void enrollCourse(int index) {
        GenericRepository<Course, Long> courseRepo = new GenericRepoImpl<>(em, Course.class);
        GenericRepository<EnrolledCourse, Long> enrolledCourseRepo = new GenericRepoImpl<>(em, EnrolledCourse.class);
        GenericRepository<Student, Long> memberRepo = new GenericRepoImpl<>(em, Student.class);

        Course course = courseRepo.findAll().get(index);
        EnrolledCourse enrolledCourse = EnrolledCourse.builder()
                .student(memberRepo.findById(1L)) // 예시로 1L을 사용, 실제로는 학생 ID를 받아와야 함
                .course(course)
                .build();

        enrolledCourseRepo.save(enrolledCourse);
    }

    public List<EnrolledCourseDTO> loadEnrolledCourseList() {
        GenericRepository<EnrolledCourse, Long> enrolledCourseRepo = new GenericRepoImpl<>(em, EnrolledCourse.class);

        // 학생 ID를 받아와야 함, 예시로 1L을 사용
        List<EnrolledCourse> enrolledCourses = enrolledCourseRepo.findByStudentId(1L); // 예시로 1L을 사용, 실제로는 학생 ID를 받아와야 함
        return enrolledCourses.stream()
                .map(enrolledCourse -> new EnrolledCourseDTO(
                        enrolledCourse.getId(),
                        enrolledCourse.getCourse().getCourseName(),
                        enrolledCourse.getCourse().getProfessor().getName(),
                        enrolledCourse.getCourse().getSemester(),
                        enrolledCourse.getCourse().getCredit(),
                        enrolledCourse.getCourse().getCapacity(),
                        enrolledCourse.getCourse().getClassroom(),
                        enrolledCourse.getCourse().getContent()
                )).toList();
    }

    public void dropCourse(int index) {
        GenericRepository<EnrolledCourse, Long> enrolledCourseRepo = new GenericRepoImpl<>(em, EnrolledCourse.class);
        List<EnrolledCourse> enrolledCourses = enrolledCourseRepo.findByStudentId(1L); // 예시로 1L을 사용, 실제로는 학생 ID를 받아와야 함

        EnrolledCourse enrolledCourse = enrolledCourses.get(index);
        enrolledCourseRepo.delete(enrolledCourse);
        System.out.println("강의 수강 취소 완료!\n");
    }

    public List<CourseDTO> loadCourseList() {
        GenericRepository<Course, Long> courseRepo = new GenericRepoImpl<>(em, Course.class);
        List<Course> courseList = courseRepo.findAll();

        return courseList.stream()
                .map(course -> new CourseDTO(
                        course.getId(),
                        course.getCourseName(),
                        course.getProfessorName(),
                        course.getSemester(),
                        course.getCredit(),
                        course.getCapacity(),
                        course.getClassroom(),
                        course.getContent(),
                        course.getProfessor().getId())).toList();
    }
}
