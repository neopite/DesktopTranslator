package LabsKPI;

public class Laba5 {

    public static boolean check(int palidrom) {
        for (int n = 2; n < 10; n++) {
            if (palidrom % n == 0)
                 return false;
        }
        return true;
    }

    public static void procces(int palidrom) {
        int iter = 0;
        while (true) {
            palidrom++;
            if (check(palidrom)) {
                if (palidrom % 10 == (palidrom / 10000) % 10 && (palidrom / 10) % 10 == (palidrom / 1000) % 10) {
                    System.out.println("Простий п`ятизначний паліндром:" + palidrom);
                    iter++;
                }
            }
            if (iter == 10) {
                break;
            }
        }
    }

    public static void main(String[] args) {

        procces(10000);
    }

}

