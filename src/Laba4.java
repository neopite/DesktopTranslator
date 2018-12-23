package LabsKPI;

import java.util.Scanner;

public class Laba4 {

    public static double setAccuracy() {
        double accuracy;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть точність обрахунку");
        accuracy = scanner.nextDouble();
        return accuracy;
    }

    public static void row() {
        double result = 1;
        double result2 = 1;
        double factor = 0;
        double accuracy = setAccuracy();
        while (1 / result > accuracy) {
            ++factor;
            result *= factor;
            result2 += 1 / result;
            System.out.println(factor + ": " + result2);
            if (accuracy > 1 / result) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        row();
    }
}


