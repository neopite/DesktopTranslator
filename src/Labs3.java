package LabsKPI;

import java.util.Scanner;

import static java.lang.Math.pow;

public class Labs3 {
    static double x, y;
    static int itteraation;
    static double result2 = 1;
    static double result1;

    public static void callGreg() {
        for (int j = 0; j <= itteraation; j++) {
            result1 += pow(-1, j) * 1 / (2 * j + 1);

        }
        result1=result1*4;
        System.out.println("Gregory: " + result1);
    }

    public static void callVall() {
        for (int j = 2; j <= itteraation; j += 2) {
            x = ((double) j / (j - 1));
            y = ((double) j / (j + 1));
            result2 *= x * y;
        }
        result2=result2*2;
        System.out.println("Valesa : " + result2);

    }

    public static void comparison() {

        double comparison1, comparison2;
        System.out.println("Comparison with PI");
        comparison1 = Math.PI - result1;
        comparison2 = Math.PI - result2;
        System.out.println("Comparison 1 row of Gregory with PI: " + comparison1 + " Comparison 2 row of Vales with : " + comparison2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a count of itteration");
        itteraation = scanner.nextInt();
        callGreg();
        callVall();
        comparison();
    }
}
