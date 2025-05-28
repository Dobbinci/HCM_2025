package com.be.view;

import java.util.Scanner;

import com.be.text_mode.ColorMode;
import com.be.text_mode.DarkMode;
import com.be.text_mode.Mode;
public class textModeChangeView {


    public static void show(){

        Mode mode = Mode.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("어떤모드로 변경하시겠습니까");
        System.out.println("1.Color Mode");
        System.out.println("2.Dark Mode");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                mode.ColorMode();
            }
            case 2 -> {
                mode.DarkMode();
            }
            default -> {
                System.out.println("Wrong choice");
            }
        }
    }
}
