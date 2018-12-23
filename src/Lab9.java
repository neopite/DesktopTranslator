package LabsKPI;

import java.util.Scanner;

public class Lab9 {
    public static String enterLine() {
        Scanner scanner = new Scanner(System.in);
        String line;
        System.out.println("Введите предложение:");
        line = scanner.nextLine();
        return line;
    }

    public static String[] splitLine(String line) {
        String[] str;
        str = line.split(" ");
        return str;
    }

    public static String reverse(String str) {
        String resultString = "";
        char[] charArray = str.toCharArray();
        for (int i = charArray.length - 1; i >= 0; i--) {
            resultString += charArray[i];
        }
        return resultString;

    }

    public static void procces(String[] str) {
        int count = 0;
        for (int x = 0; x < str.length; x++) {
            if (str[x].equalsIgnoreCase(reverse(str[x]))) {
                System.out.print("Это слово палиндром:  ");
                System.out.println(str[x]);
                count++;
            }

        }
        System.out.println("Кількість:"+count);
    }

    public static void main(String[] args) {

        procces(splitLine(enterLine()));
    }
}
