package Translator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public final static ArrayList<ArrayList<String>> LIST = new ArrayList<>();
    public final static HashMap<String, Integer> COUNT_OF_LANGUAGES = new HashMap<>();
    public final static HashMap<Integer, String> LIST_OF_LANGUAGES = new HashMap<>();
    public final static HashMap<String, HashMap<String, String>> DICTIONARY_LOAD = new HashMap<>();
    public final static HashMap<String, HashMap<String, String>> DICTIONARY_LOAD_TO = new HashMap<>();
    public static String firstLanguage;
    public static String outputTextFile = "TextOutput";
    public static String textFile = "TextInput";

    public static void main(String[] args) {
        putVocabularsAndDictionary();
        makeAChoice();

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

    public static HashMap<String, String> loadDictinory(String file) {
        HashMap<String, String> vocabular = new HashMap<>();
        try (Scanner read = new Scanner(new FileReader(file))) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] lineArray = line.split("-");
                vocabular.put(lineArray[0], lineArray[1]);
            }
        } catch (FileNotFoundException error) {
            System.err.println("File(" + file + ") not found,please try again");
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
            System.err.println("File(" + file + ") not found,please try again");
            System.exit(-1);
        }
        return vocabular;
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
            System.err.println("File(" + file + ") not found,please try again");
            System.exit(-1);
        }
        return count;
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

    public static ArrayList<String> listOfWords(String language) {
        ArrayList<String> listOFword = new ArrayList<>();
        return readFile(listOFword, language);
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
            System.err.println("File(" + language + ") not found,please try again");
            System.exit(-1);
        }
        return listOfWord;
    }

    public static int enterANumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1:Translate sentence from one language to another");
        System.out.println("2:Translate text and put translated text to another *.txt");
        int choice = scanner.nextInt();
        return choice;
    }

    public static void makeAChoice() {
        switch (enterANumber()) {
            case 1:
                TextTranslator.translateWholeSentence();
                break;
            case 2:
                TranslatorApp.translateFunction();
                break;
        }
    }
}
