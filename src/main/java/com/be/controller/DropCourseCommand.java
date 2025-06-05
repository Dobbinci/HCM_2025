package com.be.controller;

import com.be.model.EnrolledCourse;

//ConcreteCommand
public class DropCourseCommand implements Command {
    private StudentControllerFacade studentControllerFacade;
    private int index;
    private EnrolledCourse removedCourse;


    public DropCourseCommand(StudentControllerFacade studentControllerFacade, int index){
        this.studentControllerFacade = studentControllerFacade;
        this.index = index;
    }

    @Override
    public void execute() {
        removedCourse = studentControllerFacade.dropCourse(index);
    }

    @Override
    public void undo() {
        studentControllerFacade.enrollCourseForUndo(removedCourse);
    }
}
