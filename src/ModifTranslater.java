import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ModifTranslater {
    final static ArrayList<ArrayList<String>> LIST = new ArrayList<>();
    final static HashMap<String, Integer> COUNT_OF_LANGUAGES = new HashMap<>();
    final static HashMap<Integer, String> LIST_OF_LANGUAGES = new HashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        putVocabulars();
        setAListOfLanguages();
        putNewLanguage();
        String firstLanguage = "";
        Scanner write = new Scanner(System.in);
        System.out.println("Enter sentence: ");
        String sentence = write.nextLine();
        firstLanguage = languageDetection(sentence, LIST_OF_LANGUAGES, COUNT_OF_LANGUAGES);
        System.out.println("Detected language : " + firstLanguage);
        System.out.println("To which Language translate");
        String secondLanguage = write.nextLine();
        if (!COUNT_OF_LANGUAGES.keySet().contains(firstLanguage)) {
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
            }else
                translateFromEnglish(translateToEnglish(firstLanguage, sentence), secondLanguage);
                break;
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
        return readFile(listOFword,language);
    }

    public static String languageDetection(String sentence, HashMap<Integer, String> numbersOfLanguages,
                                           HashMap<String, Integer> list) throws FileNotFoundException {
        for (String word : sentence.split(" ")) {
            for (int x = 0; x < LIST.size(); x++) {
                for (int z = 0; z < findSizeOfVocab(LIST_OF_LANGUAGES.get(x)); z++) {
                    if (word.equalsIgnoreCase((LIST.get(x).get(z)))) {
                        findLanguageForTranslation(LIST_OF_LANGUAGES, x, COUNT_OF_LANGUAGES);

                    }
                }

            }
        }
        String language = "";
        int maxValueInMap = (Collections.max(COUNT_OF_LANGUAGES.values()));
        for (Map.Entry<String, Integer> entry : COUNT_OF_LANGUAGES.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                language = entry.getKey();
            }
        }

        return language;

    }

    public static int findSizeOfVocab(String language) throws FileNotFoundException {
        int count = 0;
        File file = new File(language);
        Scanner read = new Scanner(new FileReader(file));
        while (read.hasNext()) {
            String line = read.nextLine();
            count++;
        }
        return count;
    }

    public static void putVocabulars() throws FileNotFoundException {
        final ArrayList<String> ENG = listOfWords("Eng");
        final ArrayList<String> RUS = listOfWords("Rus");
        final ArrayList<String> UA = listOfWords("Ua");
        LIST.add(0, ENG);
        LIST.add(1, RUS);
        LIST.add(2, UA);
        COUNT_OF_LANGUAGES.put("Rus", 0);
        COUNT_OF_LANGUAGES.put("Eng", 0);
        COUNT_OF_LANGUAGES.put("Ua", 0);

    }

    public static void setAListOfLanguages() {
        LIST_OF_LANGUAGES.put(0, "Eng");
        LIST_OF_LANGUAGES.put(1, "Rus");
        LIST_OF_LANGUAGES.put(2, "Ua");
    }

    public static HashMap<String, Integer> findLanguageForTranslation(HashMap<Integer, String> map, int number,   //Не знаю,но думаю что я немного наблизился к тому что ты говорил(но это не точно)
                                                                      HashMap<String, Integer> list) {
        for(int x=0;x<LIST_OF_LANGUAGES.size();x++) {
            if (map.get(number).contains(LIST_OF_LANGUAGES.get(x))) {
                int count = list.get(LIST_OF_LANGUAGES.get(x));
                list.put(LIST_OF_LANGUAGES.get(x), count + 1);
            }
        }
        return list;
    }

    public static void putNewLanguage() throws FileNotFoundException {
        Scanner write = new Scanner(System.in);
        System.out.println("Enter new language");
        String language = write.nextLine();
        LIST_OF_LANGUAGES.put(LIST_OF_LANGUAGES.size(), language);
        COUNT_OF_LANGUAGES.put(language, 0);
        final ArrayList<String> lang=listOfWords(language);
        LIST.add(LIST.size(),lang);

    }

    public static ArrayList<String> readFile(ArrayList<String> listOFword, String language) throws FileNotFoundException {
        Scanner read = new Scanner(new FileReader(language));
        if (language.equalsIgnoreCase("Eng")) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                listOFword.add(line);
            }
        } else
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] array = line.split("-");
                listOFword.add(array[0]);
            }
        return listOFword;
    }
}
