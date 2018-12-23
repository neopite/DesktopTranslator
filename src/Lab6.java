package LabsKPI;

import java.util.Scanner;

public class Lab6 {
    static double accuracy;

    static public void calc(double number) {
        double y;
        while (0 >= number) {
            y = Math.pow(TaylorRow(number), 3) - TaylorRow(1 / number);
            System.out.println("x in area from -2 to 0:" + y);
            number += 0.5;
        }
        number = 0.5;
        while (number < 2 && number > 0) {
            y = TaylorRow(number) - TaylorRow(number / 2);
            System.out.println("x in area from 0 to 2:" + y);
            number += 0.5;
        }
    }

    static public double fact(int num) {
        double result = 1;
        while (num != 1) {
            result *= num;
            num--;
        }
        return result;
    }

    static public double TaylorRow(double x) {
        double itteration = 1;
        double sinx = 0;
        int y = -1;
        int n = -1;
        while (accuracy < Math.abs(itteration)) {
            y += 2;
            n++;
            itteration = Math.pow(-1, n) * (Math.pow(x, (2 * n + 1))) / fact(y);
            sinx += itteration;
        }
        return sinx;
    }

    public static void main(String[] args) {
        double y;
        double number;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите точность");
        accuracy = scanner.nextDouble();
        System.out.println("Введите аргумента sin");
        number = scanner.nextDouble();
        calc(number);
    }
}