package LabsKPI;

import java.util.Scanner;
public class Laba {
    public static void main(String [] args)
    {
        double AB,BC,CA; //Отрезки треуголиника
        double S,P,p; //Площадь, периметр треугольника,полупериметр
        int[] x=new int[4];
        int[] y=new int[4];
        Scanner scanner=new Scanner(System.in);
        System.out. println ( "Задайте координаты вершин");
        for(int i=1;i<x.length;i++)
        {
            System.out.println("Задайте " + i + " x");
            x[i] = scanner.nextInt();
            System.out.println("Задайте " + i + " y");
            y[i] = scanner.nextInt();
        }
        //Периметр треугольника
        AB = Math.sqrt(Math.pow((x[2] - x[1]), 2) + Math.pow(y[2] - y[1], 2));
        BC = Math.sqrt(Math.pow((x[3] - x[2]), 2) + Math.pow(y[3] - y[2], 2));
        CA = Math.sqrt(Math.pow((x[1] - x[3]), 2) + Math.pow(y[1] - y[3], 2));
        P = AB + BC + CA;
        System.out.println("Периметр треугольника=" + P);

        //Площадь треугольника
        p=(AB+BC+CA)/2;
        S=Math.sqrt(p*(p-AB)*(p-BC)*(p-CA));
        System.out.println("Площадь треугольника="+S);
    }
}

