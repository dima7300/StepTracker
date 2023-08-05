package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StepTracker {
    private static int[] dayMonthJanuary = new int[30];
    private static int[] dayMonthFebruary = new int[28];
    private static int[] dayMonthMarch = new int[30];
    private static int[] dayMonthApril = new int[30];
    private static int[] dayMonthMay = new int[30];
    private static int[] dayMonthJune = new int[30];
    private static int[] dayMonthJuly = new int[30];
    private static int[] dayMonthAugust = new int[30];
    private static int[] dayMonthSeptember = new int[30];
    private static int[] dayMonthOctober = new int[30];
    private static int[] dayMonthNovember = new int[30];
    private static int[] dayMonthDecember = new int[30];

    private static HashMap<Integer, int[]> month = new HashMap<>();
    private static ArrayList<String> printMonthList = new ArrayList<>();
    private static int targetStep = 10_000;

    public static void insertMonthHashTable() {
        month.put(0, dayMonthJanuary);
        month.put(1, dayMonthFebruary);
        month.put(2, dayMonthMarch);
        month.put(3, dayMonthApril);
        month.put(4, dayMonthMay);
        month.put(5, dayMonthJune);
        month.put(6, dayMonthJuly);
        month.put(7, dayMonthAugust);
        month.put(8, dayMonthSeptember);
        month.put(9, dayMonthOctober);
        month.put(10, dayMonthNovember);
        month.put(11, dayMonthDecember);

        printMonthList.add(0, "Январь");
        printMonthList.add(1, "Февраль");
        printMonthList.add(2, "Март");
        printMonthList.add(3, "Апрель");
        printMonthList.add(4, "Май");
        printMonthList.add(5, "Июнь");
        printMonthList.add(6, "Июль");
        printMonthList.add(7, "Август");
        printMonthList.add(8, "Сентябрь");
        printMonthList.add(9, "Октябрь");
        printMonthList.add(10, "Ноябрь");
        printMonthList.add(11, "Декабрь");
    }

    private static void printMonth() {
        int n = 1;
        for (String x : printMonthList) {
            System.out.println(n + " - " + x);
            n++;
        }
    }

    public static void stepCount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите месяц");
        printMonth();
        int monthNumber;
        int step;
        int day;
        while (true) {
            try {
                monthNumber = sc.nextInt();
                if (monthNumber < 1 || monthNumber > 12) {
                    System.out.println("Такого месяца нет в списке\n" +
                            "Повторите попытку");
                    printMonth();
                } else {
                    while (true) {
                        try {
                            System.out.println("Укажите день от 1 до " + month.get(monthNumber - 1).length);
                            day = sc.nextInt();
                            if (day < 1 || day > month.get(monthNumber - 1).length) {
                                System.out.println("День выходит за границы месяца");
                            } else {
                                System.out.println("Введите колличество шагов");
                                step = sc.nextInt();
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Такой команды нет");
                            printMonth();
                            sc.next();
                        }
                    }
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Такой команды нет");
                printMonth();
                sc.next();
            }
        }
        month.get(monthNumber - 1)[day - 1] = step;
    }

    public static void statisticMonth() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите месяц");
        printMonth();
        int monthNumberStatistic;
        int dayStatistic;
        while (true) {
            try {
                monthNumberStatistic = sc.nextInt();
                if (monthNumberStatistic < 1 || monthNumberStatistic > 12) {
                    System.out.println("Такого месяца нет в списке\n" +
                            "Повторите попытку");
                    printMonth();
                } else {
                    int quantityStepMonth = 0;
                    int maxStepMonth = 0;
                    int stepAvg = 0;
                    double distance = 0.0;
                    double calories = 0;
                    int countRangeStep = 0;
                    int j = 1;
                    int count = 1;
                    for (int i = 0; i < month.get(monthNumberStatistic - 1).length; i++) {
                        System.out.println(i + 1 + " день: " + month.get(monthNumberStatistic - 1)[i]);
                        quantityStepMonth += month.get(monthNumberStatistic - 1)[i];
                        if (maxStepMonth < month.get(monthNumberStatistic - 1)[i]) {
                            maxStepMonth = month.get(monthNumberStatistic - 1)[i];
                        }
                        if (j < month.get(monthNumberStatistic - 1).length){
                            if (month.get(monthNumberStatistic - 1)[j] >= targetStep && month.get(monthNumberStatistic - 1)[i] >= targetStep ){
                                count++;
                            } else {
                                if (count > countRangeStep) countRangeStep = count;
                                count = 1;
                            }
                            j++;
                        }

                    }
                    stepAvg = quantityStepMonth / month.get(monthNumberStatistic - 1).length;
                    distance = (quantityStepMonth * 75) / 100_000;
                    calories = (quantityStepMonth * 50) / 1000;
                    System.out.println("Общее количество шагов в месяц - " + quantityStepMonth);
                    System.out.println("Максимальное пройденое количество шагов в месяце - " + maxStepMonth);
                    System.out.println("Среднее колличество шагов - " + stepAvg);
                    System.out.println("Дистанция - " + distance + " км");
                    System.out.println("Количество сожженых килокалорий - " + calories);
                    System.out.println("Лучшая серия - " + countRangeStep);

                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Такой команды нет");
                printMonth();
                sc.next();
            }
        }
    }

    public static void rangeStep() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите новую цель по количесву шагов");
                targetStep = sc.nextInt();
                if (targetStep > 0) {
                    System.out.println("Новая цель записана\n" +
                            "Цель " + targetStep + " шагов");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Такой команды нет");
                sc.next();
            }
        }

    }

}
