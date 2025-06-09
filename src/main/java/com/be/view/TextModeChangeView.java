package com.be.view;


import java.util.Scanner;

import com.be.text_mode.Mode;
public class TextModeChangeView {


    public static void show(){

        Mode mode = Mode.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println("어떤 모드로 변경하시겠습니까");
        System.out.println("1. Color Mode");
        System.out.println("2. Dark Mode");
        System.out.println("3. 종료");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                mode.ColorMode();
            }
            case 2 -> {
                mode.DarkMode();
            }
            case 3 -> {
                return;
            }
            default -> {
                System.out.println("Wrong choice");
            }
        }
    }
}
