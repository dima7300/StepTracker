package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        StepTracker.insertMonthHashTable();
        Scanner sc = new Scanner(System.in);
        printMenu();
        int userInput;
        while (true) {
            try {
                userInput = sc.nextInt();
                if (userInput < 0 || userInput > 4) System.out.println("Такой команды нет");
                else if (userInput == 1) {
                    StepTracker.stepCount();
                } else if (userInput == 2) {
                    StepTracker.statisticMonth();
                } else if (userInput == 3) {
                    StepTracker.rangeStep();
                } else {
                    System.out.println("Выход");
                    break;
                }
                System.out.println("Выберите из списка");
                printMenu();
            } catch (InputMismatchException e) {
                System.out.println("Такой команды нет");
                printMenu();
                sc.next();
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Ввести колличество шагов за определенный день\n" +
                "2. Напечатать статистику за определенный месяц\n" +
                "3. Изменить цель по колличеству шагов в день\n" +
                "4. Выйти из приложения");
    }
}
