package LabsKPI;
import java.util.Scanner;
public class Lab7 {

    public static void createArray(int [] a,int [] b) {

        for (int x = 0; x < a.length; x++) {
            a[x] = (int) ((Math.random() * 5000) + 50);
            b[x] = (int) ((Math.random() * 5000) + 50);
        }
    }

    public static void showNull(int[]a,int[]b) {
        for (int z = 0; z < a.length; z++) {
            for (int count = 0; count < b.length; count++) {
                if (a[z] == b[count]) {
                    a[z] = 0;
                    System.out.println(z + " :" + a[z]);
                    System.out.println("Заміна елемента "+z+"  на нуль");
                }
            }
        }

    }
    public static void main(String[] args) {
        int numberOfEl;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Введіть розмірність масиву");
        numberOfEl=scanner.nextInt();
        int [] a=new int[numberOfEl];
        int [] b=new int [numberOfEl];
        createArray(a,b);
        showNull(a,b);

    }
}
