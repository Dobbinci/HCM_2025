package com.be.view.staff;

import com.be.control.CourseManager;
import com.be.form.Course;
import com.be.service.Staff;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseUpdateView {
    public static void show(Staff staff){
        //view 로직

        Scanner scanner = new Scanner(System.in);
        int choice;
        choice = scanner.nextInt();
        staff.updateCourse(choice-1);
    }
}
