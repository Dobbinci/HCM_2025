package com.be.controller;

import com.be.model.EnrolledCourse;

//ConcreteCommand
public class DropCourseCommand implements Command {
    private StudentController studentController;
    private int index;
    private EnrolledCourse removedCourse;


    public DropCourseCommand(StudentController studentController, int index){
        this.studentController = studentController;
        this.index = index;
    }

    @Override
    public void execute() {
        removedCourse = studentController.dropCourse(index);
    }

    @Override
    public void undo() {
        studentController.enrollCourseForUndo(removedCourse);
    }
}
