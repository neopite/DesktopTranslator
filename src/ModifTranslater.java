import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class ModifTranslater {
    public static void main(String[] args) throws FileNotFoundException {
        String [] arrayOfLanguages={"Rus","Ua","Eng"};
        String line1 = "";
        Scanner write = new Scanner(System.in);
        System.out.println("Enter sentence: ");
        String sentence = write.nextLine();
        System.out.println("From which Language u want to translate");
        String firstLanguage = write.nextLine();
        System.out.println("To which Language translate");
        String secondLanguage = write.nextLine();
        if(!firstLanguage.equalsIgnoreCase("Ua") && !firstLanguage.equalsIgnoreCase("Eng") &&
                !firstLanguage.equalsIgnoreCase("Rus")){
            System.out.println("Sorry,it can`t be translated ");
            System.exit(0);
        }
        while (true) {
            if (firstLanguage.equalsIgnoreCase("Eng")) {
                translateFromEnglish(sentence, secondLanguage);
                break;
            }
            if (secondLanguage.equalsIgnoreCase("Eng")) {
                translateToEnglish(secondLanguage, sentence);
                break;
            }
            if (secondLanguage.equalsIgnoreCase("Rus")) {
                translateFromEnglish(translateToEnglish(firstLanguage, sentence), secondLanguage);
                break;
            } else if (secondLanguage.equalsIgnoreCase("Ua")) {
                translateFromEnglish(translateToEnglish(firstLanguage, sentence), secondLanguage);
                break;
            }

        }

    }

    public static HashMap<String, String> loadDictinory(String file) throws FileNotFoundException {
        HashMap<String, String> vocabular = new HashMap<>();
        Scanner read = new Scanner(new FileReader(file));
        while (read.hasNextLine()) {
            String line = read.nextLine();
            String[] lineArray = line.split("-");
            vocabular.put(lineArray[0], lineArray[1]);
        }
        return vocabular;
    }

    public static HashMap<String, String> loadDictinoryTo(String file) throws FileNotFoundException {
        HashMap<String, String> vocabular = new HashMap<>();
        Scanner read = new Scanner(new FileReader(file));
        while (read.hasNextLine()) {
            String line = read.nextLine();
            String[] lineArray = line.split("-");
            vocabular.put(lineArray[1], lineArray[0]);
        }
        return vocabular;
    }

    public static String translateToEnglish(String lang, String sentence) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        String str = "";
        switch (lang) {

            case "Eng":
                System.out.println("Which dictionary to use?");
                String dict = scanner.nextLine();
                for (String words : sentence.split(" ")) {
                    str += loadDictinory(dict).get(words) + " ";
                }
                System.out.println(str);
                break;

            default:
                for (String words : sentence.split(" ")) {
                    str += loadDictinory(lang).get(words) + " ";

                }
        }
        return str;

    }

    public static void translateFromEnglish(String str, String lang) throws FileNotFoundException {
        for (String line : str.split(" ")) {
            System.out.print(loadDictinoryTo(lang).get(line) + " ");
        }
    }
}


