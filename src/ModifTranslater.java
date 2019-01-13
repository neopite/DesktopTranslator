import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ModifTranslater {
    public static void main(String[] args) throws FileNotFoundException {
        String firstLanguage = "";
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Eng");
        lines.add("Rus");
        lines.add("Ua");
        Scanner write = new Scanner(System.in);
        System.out.println("Enter sentence: ");
        String sentence = write.nextLine();
        if (languageDetection(sentence, lines).equalsIgnoreCase("Rus")) {
            firstLanguage = languageDetection(sentence, lines);
        } else if (languageDetection(sentence, lines).equalsIgnoreCase("Ua")) {
            firstLanguage = languageDetection(sentence, lines);
        } else if (languageDetection(sentence, lines).equalsIgnoreCase("Eng")) {
            firstLanguage = languageDetection(sentence, lines);
        }
        System.out.println("Detected language : "+firstLanguage);
        System.out.println("To which Language translate");
        String secondLanguage = write.nextLine();
        if (!lines.contains(firstLanguage)) {
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
            if (secondLanguage.equalsIgnoreCase("Rus") || secondLanguage.equalsIgnoreCase("Ua")) {
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

    public static ArrayList<String> listOfWords(String language) throws FileNotFoundException {
        ArrayList<String> listOFword = new ArrayList<>();
        Scanner read = new Scanner(new FileReader(language));
        if (language.equalsIgnoreCase("Eng")) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                listOFword.add(line);
            }
        } else if (language.equalsIgnoreCase("Ua")) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] array = line.split("-");
                listOFword.add(array[0]);
            }
        } else if (language.equalsIgnoreCase("Rus")) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] array = line.split("-");
                listOFword.add(array[0]);
            }
        }
        return listOFword;
    }

    public static String languageDetection(String sentence, ArrayList<String> listOfLanguages) throws FileNotFoundException {
        int rus = 0;
        int ua = 0;
        int eng = 0;
        int count = 0;
        for (String word : sentence.split(" ")) {
            for (String language : listOfLanguages) {
                for (int x = 0; x < listOfWords(language).size(); x++) {
                    if (word.contains(listOfWords(language).get(x))) {
                        if (language.contains("Rus")) {
                            rus++;
                        } else if (language.contains("Eng")) {
                            eng++;
                        } else if (language.contains("Ua")) {
                            ua++;
                        }
                    }
                }

            }
        }
        String max = "";
        if (eng < rus && ua < rus) {
            max = "Rus";
        } else if (eng < ua && rus < ua) {
            max = "Ua";
        } else if (rus < eng && ua < eng) {
            max = "Eng";
        }
        return max;

    }

}




