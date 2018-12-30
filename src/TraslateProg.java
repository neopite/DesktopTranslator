// Mini translater.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TraslateProg {

    public static void main(String[] args) throws FileNotFoundException {

        String[] translationArray = new String[100];
        File filePAth = new File("TextFile.txt"); // файл со словарём
        // в формате слово(Rus)-слово(Eng)
        Scanner read = new Scanner(new FileReader(filePAth));
        String translation = enterWord(); // Задаём слово на русском
        while (read.hasNextLine()) {
            String line = read.nextLine();
            translationArray = line.split("-");
            if (translationArray[0].equalsIgnoreCase(translation)) {
                System.out.println("Translation is :" + translationArray[1]);
                break;
            }
        }
    }

    static public String enterWord() {
        String word;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word for translation from Russia to English");
        word = scanner.nextLine();
        return word;
    }
}
