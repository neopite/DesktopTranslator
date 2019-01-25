import java.io.*;
import java.util.*;

public class ModifTranslater {
    final static ArrayList<ArrayList<String>> LIST = new ArrayList<>();
    final static HashMap<String, Integer> COUNT_OF_LANGUAGES = new HashMap<>();
    final static HashMap<Integer, String> LIST_OF_LANGUAGES = new HashMap<>();
    final static HashMap<String, HashMap<String, String>> DICTIONARY_LOAD = new HashMap<>();
    final static HashMap<String, HashMap<String, String>> DICTIONARY_LOAD_TO = new HashMap<>();
    static String firstLanguage;

    public static void main(String[] args) {
        putVocabularsAndDictionary();
        makeAChoice();
    }

    public static HashMap<String, String> loadDictinory(String file) {
        HashMap<String, String> vocabular = new HashMap<>();
        try (Scanner read = new Scanner(new FileReader(file))) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] lineArray = line.split("-");
                vocabular.put(lineArray[0], lineArray[1]);
            }
        } catch (FileNotFoundException error) {
            System.out.println("File(" + file + ") not found,please try again");
            System.exit(-1);
        }
        return vocabular;
    }

    public static HashMap<String, String> loadDictinoryTo(String file) {
        HashMap<String, String> vocabular = new HashMap<>();
        try (Scanner read = new Scanner(new FileReader(file))) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] lineArray = line.split("-");
                vocabular.put(lineArray[1], lineArray[0]);
            }
        } catch (FileNotFoundException error) {
            System.out.println("File(" + file + ") not found,please try again");
            System.exit(-1);
        }
        return vocabular;
    }

    public static String translateToEnglish(String lang, String sentence) {
        String str = "";
        switch (lang) {
            case "Eng":
                for (String words : sentence.split(" ")) {
                    str += DICTIONARY_LOAD.get(firstLanguage).get(words) + " ";
                }
                System.out.print(str);
                break;
            default:
                for (String words : sentence.split(" ")) {
                    str += DICTIONARY_LOAD.get(lang).get(words) + " ";
                }
        }
        return str;
    }

    public static String translateFromEnglish(String str, String lang) {
        String words = "";
        try {
            for (String line : str.split(" ")) {
                System.out.print(DICTIONARY_LOAD_TO.get(lang).get(line) + " ");
                words += DICTIONARY_LOAD_TO.get(lang).get(line) + " ";
            }
        } catch (NullPointerException error) {
            System.out.println("Language(" + lang + ")is nще contained in dictionaries");
            System.exit(-1);
        }
        return words;
    }

    public static ArrayList<String> listOfWords(String language) {
        ArrayList<String> listOFword = new ArrayList<>();
        return readFile(listOFword, language);
    }

    public static String languageDetection(String sentence) {
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

    public static int findSizeOfVocab(String language) {
        int count = 0;
        File file = new File(language);
        try (Scanner read = new Scanner(new FileReader(file))) {
            while (read.hasNext()) {
                String line = read.nextLine();
                count++;
            }
        } catch (FileNotFoundException error) {
            System.out.println("File(" + file + ") not found,please try again");
            System.exit(-1);
        }
        return count;
    }

    public static void putVocabularsAndDictionary() {
        final ArrayList<String> ENG = listOfWords("Eng");
        final ArrayList<String> RUS = listOfWords("Rus");
        final ArrayList<String> UA = listOfWords("Ua");
        HashMap<String, String> RusDictionary = loadDictinory("Rus");
        HashMap<String, String> UaDictionary = loadDictinory("Ua");
        HashMap<String, String> RusDictionaryTo = loadDictinoryTo("Rus");
        HashMap<String, String> UaDictionaryTo = loadDictinoryTo("Ua");
        LIST.add(0, ENG);
        LIST.add(1, RUS);
        LIST.add(2, UA);
        LIST_OF_LANGUAGES.put(0, "Eng");
        LIST_OF_LANGUAGES.put(1, "Rus");
        LIST_OF_LANGUAGES.put(2, "Ua");
        COUNT_OF_LANGUAGES.put("Rus", 0);
        COUNT_OF_LANGUAGES.put("Eng", 0);
        COUNT_OF_LANGUAGES.put("Ua", 0);
        DICTIONARY_LOAD.put("Rus", RusDictionary);
        DICTIONARY_LOAD.put("Ua", UaDictionary);
        DICTIONARY_LOAD_TO.put("Rus", RusDictionaryTo);
        DICTIONARY_LOAD_TO.put("Ua", UaDictionaryTo);
    }

    public static HashMap<String, Integer> findLanguageForTranslation(HashMap<Integer, String> map, int number,
                                                                      HashMap<String, Integer> list) {
        for (int x = 0; x < LIST_OF_LANGUAGES.size(); x++) {
            if (map.get(number).contains(LIST_OF_LANGUAGES.get(x))) {
                int count = list.get(LIST_OF_LANGUAGES.get(x));
                list.put(LIST_OF_LANGUAGES.get(x), count + 1);
            }
        }
        return list;
    }

    public static void putNewLanguage() {
        Scanner write = new Scanner(System.in);
        System.out.println("Enter new language");
        String language = write.nextLine();
        HashMap<String, String> dictionaryNewLanguage = loadDictinory(language);
        HashMap<String, String> dictionaryToNewLanguage = loadDictinoryTo(language);
        LIST_OF_LANGUAGES.put(LIST_OF_LANGUAGES.size(), language);
        COUNT_OF_LANGUAGES.put(language, 0);
        final ArrayList<String> lang = listOfWords(language);
        LIST.add(LIST.size(), lang);
        DICTIONARY_LOAD.put(language, dictionaryNewLanguage);
        DICTIONARY_LOAD_TO.put(language, dictionaryToNewLanguage);
    }

    public static ArrayList<String> readFile(ArrayList<String> listOfWord, String language) {
        try (Scanner read = new Scanner(new FileReader(language))) {
            if (language.equalsIgnoreCase("Eng")) {
                while (read.hasNextLine()) {
                    String line = read.nextLine();
                    listOfWord.add(line);
                }
            } else {
                while (read.hasNextLine()) {
                    String line = read.nextLine();
                    String[] array = line.split("-");
                    listOfWord.add(array[0]);
                }
            }
        } catch (FileNotFoundException error) {
            System.out.println("File(" + language + ") not found,please try again");
            System.exit(-1);
        }
        return listOfWord;
    }


    public static ArrayList<String> putTextInArray(String file) {
        int count = 0;
        ArrayList<String> array = new ArrayList<>();
        try (Scanner read = new Scanner(new FileReader(file))) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] arrayOfWords = line.split(";");
                array.add(count, arrayOfWords[1]);
                count++;

            }
        } catch (FileNotFoundException error) {
            System.out.println("File" + file + "not found,please try again");
            System.exit(-1);
        }
        return array;
    }

    public static ArrayList<String> putTextInHashMap(String file) {
        int count = 0;
        ArrayList<String> array = new ArrayList<>();
        try (Scanner read = new Scanner(new FileReader(file))) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] arrayOfWords = line.split(";");
                array.add(count, arrayOfWords[0]);
                count++;
            }
        } catch (FileNotFoundException error) {
            System.out.println("File" + file + "not found,please try again");
            System.exit(-1);
        }

        return array;
    }

    public static ArrayList<String> translateText(ArrayList<String> array, ArrayList<String> reputArray) {
        int count = 0;
        String text;
        ArrayList<String> listOfWords = new ArrayList<>();
        for (int x = 0; x < findSizeOfVocab("TextInput"); x++) {
            firstLanguage = languageDetection(array.get(x));
            System.out.println(array.get(x) + " " + firstLanguage);
            while (true) {
                if (firstLanguage.equalsIgnoreCase("Eng")) {
                    text = translateFromEnglish(array.get(x), reputArray.get(x));
                    break;
                } else if (reputArray.get(x).equalsIgnoreCase("Eng")) {
                    text = translateToEnglish(reputArray.get(x), array.get(x));
                    break;
                } else {
                    text = translateFromEnglish(translateToEnglish(firstLanguage, array.get(x)), reputArray.get(x));
                    break;
                }
            }
            listOfWords.add(count, text);
            count++;
            System.out.println();
        }
        return listOfWords;
    }

    public static void putTranslatedTextInOutputFile(ArrayList<String> text) {
        File file = new File("TextOutput");
        try (FileWriter writer = new FileWriter(file)) {
            for (int x = 0; x < findSizeOfVocab("TextInput"); x++) {
                writer.write(text.get(x) + "\n");
            }
        } catch (IOException error) {
            System.out.println("File" + file + "not found");
            System.exit(-1);
        }
    }

    public static void makeAChoice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1:Translate sentence from one language to another");
        System.out.println("2:Translate text and put translated text to another *.txt");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                Scanner write = new Scanner(System.in);
                System.out.println("Enter sentence: ");
                String sentence = write.nextLine();
                firstLanguage = languageDetection(sentence);
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
                    } else
                        translateFromEnglish(translateToEnglish(firstLanguage, sentence), secondLanguage);
                    break;
                }
                break;
            case 2:
                putTranslatedTextInOutputFile(translateText(putTextInArray("TextInput"), putTextInHashMap("TextInput")));
                break;

        }
    }
}
