import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ModifTranslater {
    final static ArrayList<ArrayList<String>> LIST=new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        final ArrayList<String> ENG_LIST_OF_WORDS = listOfWords("Eng");
        final ArrayList<String> RUS_LIST_OF_WORDS = listOfWords("Rus");
        final ArrayList<String> UA_LIST_OF_WORDS = listOfWords("Ua");
        LIST.add(ENG_LIST_OF_WORDS);
        LIST.add(RUS_LIST_OF_WORDS);
        LIST.add(UA_LIST_OF_WORDS);
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
        System.out.println("Detected language : " + firstLanguage);
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
        ArrayList<String> listOfReuslt = new ArrayList<>();
        HashMap<String, Integer> list = new HashMap<>();
        list.put("Rus", 0);
        list.put("Eng", 0);
        list.put("Ua", 0);
        boolean flag = true;
        for (String word : sentence.split(" ")) {
            for (String language : listOfLanguages) {
                for (int x = 0; x < LIST.size(); x++) {
                    if (word.contains(listOfWords(language).get(x))) {
                        int count;
                        if (language.contains("Rus")) {
                            count = list.get("Rus");
                            list.put("Rus", count + 1);
                        } else if (language.contains("Eng")) {
                            count = list.get("Eng");
                            list.put("Eng", count + 1);
                        } else if (language.contains("Ua")) {
                            count = list.get("Ua");
                            list.put("Ua", count + 1);
                        }
                    }
                }

            }
        }
        String language = "0";
        int maxValueInMap = (Collections.max(list.values()));
        for (Map.Entry<String, Integer> entry : list.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                language = entry.getKey();
            }
        }

        return language;

    }

}




