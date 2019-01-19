import java.sql.SQLOutput;
import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку из слов с пробелами");
        String line = scanner.nextLine();
        System.out.println("Введите количество символов в слове");
        int amount=scanner.nextInt();
        foundWord(line,amount);

    }
    public static void foundWord(String sentence,int amoutOfChars){
        for(String words:sentence.split(" "))
        {
            if(words.toCharArray().length==amoutOfChars){
                System.out.println(words);
            }
        }
    }
}

