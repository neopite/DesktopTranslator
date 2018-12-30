// Mini translater.

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TraslateProg {
    public static void main(String[] args) throws FileNotFoundException {
        String translation;
        String line;
        String[] rus = new String[100];
        File filePAth = new File("/home/yarik/IdeaProjects/Practice/TextFile.txt"); // файл со словарём в формате слово(Rus)-слово(Eng)
        Scanner read = new Scanner(new FileReader(filePAth));
        translation = EnterWord(); // Задаём слово на русском
        while (read.hasNextLine()) {
            line = read.nextLine();
            rus = line.split("-");
            if (rus[0].equalsIgnoreCase(translation)) {
                System.out.println("Translation is :" + rus[1]);
                break;
            }
        }
    }
    static public String EnterWord() {
        String word;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a word for translation from Russia to English");
        word = scanner.nextLine();
        return word;

    }
}
