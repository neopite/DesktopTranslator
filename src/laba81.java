package LabsKPI;

import java.util.Arrays;
import java.util.Scanner;

public class laba81 {
    static public int getMin(int[] array){
        int min = array[0];
        for(int it = 1;it < array.length;it++)
        {
            if(array[it] < min){
                min = array[it];
            }
        }
        return min;
    }
    static public int getMax(int[]array) // Максимальная строка в массиве
    {
        int max1=0;
        for (int it = 0; it <array.length; it++) {
            if (array[it] > max1) {
                max1 = array[it];
            }
        }
        return max1;
    }

    static public void setArray(int[][] array) { //Наполнение массива
        for (int itter = 0; itter < array.length; itter++) {
            for (int itter1 = 0; itter1 < array[itter].length; itter1++) {
                array[itter][itter1] = (int) (Math.random() * 100 + 100);
            }
        }
    }


    static public int[] row(int[][] array, int[] sumOfrows) {
        int max = 0;
        int sumRow = 0;
        for (int itter = 0; itter < array.length; itter++) {
            for (int itter1 = 0; itter1 < array[itter].length; itter1++) {
                sumRow += array[itter][itter1];
            }
            max = sumRow;
            sumOfrows[itter] = max;
            sumRow = 0;
        }
        return sumOfrows;
    }
    static public void calc(int [] a,int b[]){
        double result;
        result=(double)(getMax(a)+getMax(b))/(1+getMin(a)*getMin(b));
        System.out.println("Результат:"+result);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x, y;
        System.out.println("Введите количество строк массива");
        x = scan.nextInt();
        System.out.println("Введите количество столбцов массива");
        y = scan.nextInt();
        int[][] a = new int[x][y];
        int[][] b = new int[x][y];
        int[] a1 = new int[x];
        int[] b1 = new int[x];
        int[] sumOfrows = new int[x];
        setArray(a);
        setArray(b);
        a1 = row(a, sumOfrows);
        b1 = row(b, sumOfrows);
        calc(a1,b1);
    }
}
