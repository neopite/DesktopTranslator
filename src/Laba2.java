package LabsKPI;

import java.util.Scanner;

public class Laba2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x, y, A, interval;
        System.out.println("Enter x:");

        x = scanner.nextInt();
        if (x < 0) {
            y = x;
        } else {
            System.out.println("Enter A");
            A = scanner.nextInt();
            y = A;
        }
        System.out.println("Result:y=" + y);
    }
}


