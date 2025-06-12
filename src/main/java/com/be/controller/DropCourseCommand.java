package com.be.controller;

import com.be.model.EnrolledCourse;
import com.be.model.Student;

//ConcreteCommand
public class DropCourseCommand implements Command {
    private StudentControllerFacade studentControllerFacade;
    private int index;
    private EnrolledCourse removedCourse;
    private final Student student;


    public DropCourseCommand(Student student, StudentControllerFacade studentControllerFacade, int index){
        this.studentControllerFacade = studentControllerFacade;
        this.index = index;
        this.student = student;
    }

    @Override
    public void execute() {
        removedCourse = studentControllerFacade.dropCourse(student, index);
    }

    @Override
    public void undo() {
        studentControllerFacade.enrollCourseForUndo(removedCourse);
    }
}
