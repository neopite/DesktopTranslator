package com.github.neophite.Translator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TranslatorApp {
    protected final static ArrayList<ArrayList<String>> LIST = new ArrayList<>();
    private final static HashMap<String, Integer> COUNT_OF_LANGUAGES = new HashMap<>();
    private final static ArrayList<String> LIST_OF_LANGUAGES = new ArrayList<>();
    protected final static HashMap<String, HashMap<String, String>> DICTIONARY_LOAD = new HashMap<>();
    protected final static HashMap<String, HashMap<String, String>> DICTIONARY_LOAD_TO = new HashMap<>();
    protected static String firstLanguage;
    private static String outputTextFile = "TextOutput";
    private static String textFile = "TextInput";
    private static LanguageGuesser guesser = new LanguageGuesser(LIST, LIST_OF_LANGUAGES, COUNT_OF_LANGUAGES);
    private static TranslatorApp appTranslator = new TranslatorApp();
    private static TextTranslator txtTrans = new TextTranslator();
    private static HashMap<Integer,String> hashMapOfWords = new HashMap<>();
    private static HashMap<Integer,String> hashMapOfChars = new HashMap<>();
    private static String[] delimenters = {",", ";", ".", "?", "!", ":", "@", "(", ")", "{", "}", "*", "/", " "};
    private static ArrayList<String> delims = new ArrayList<>();

    public static void main(String[] args) {

        String finalStr="";
        appTranslator.putVocabularsAndDictionary();
        for (int ittter = 0; ittter < delimenters.length; ittter++) {
            delims.add(delimenters[ittter]);
        }
        int count=appTranslator.putTextInHashMaps("TextInput");
        System.out.println(hashMapOfChars.size());
        System.out.println(hashMapOfWords.size());
        for (int itter = 0; itter < hashMapOfChars.size(); itter++) {
            if(hashMapOfChars.get(itter)!=null){
                finalStr+=hashMapOfChars.get(itter);
            } else if(hashMapOfWords.get(itter)!=null){
                finalStr+=hashMapOfWords.get(itter);
            }
        }
        System.out.println(finalStr);
        System.out.println(count);
        //appTranslator.makeAChoice();
    }

    public void putVocabularsAndDictionary() {
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
        LIST_OF_LANGUAGES.add(0, "Eng");
        LIST_OF_LANGUAGES.add(1, "Rus");
        LIST_OF_LANGUAGES.add(2, "Ua");
        COUNT_OF_LANGUAGES.put("Rus", 0);
        COUNT_OF_LANGUAGES.put("Eng", 0);
        COUNT_OF_LANGUAGES.put("Ua", 0);
        DICTIONARY_LOAD.put("Rus", RusDictionary);
        DICTIONARY_LOAD.put("Ua", UaDictionary);
        DICTIONARY_LOAD_TO.put("Rus", RusDictionaryTo);
        DICTIONARY_LOAD_TO.put("Ua", UaDictionaryTo);
    }

    /**
     * Dictionary format(Any language - English translation).Put dictionary in HashMap.
     *
     * @param file - name of the dictionary.
     * @return vocabular - dictionary in HashMap.
     */
    public HashMap<String, String> loadDictinory(String file) {
        HashMap<String, String> vocabular = new HashMap<>();
        try (Scanner read = new Scanner(new FileReader(file))) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] lineArray = line.split("-"); // "-" separator between words
                vocabular.put(lineArray[0], lineArray[1]);
            }
        } catch (FileNotFoundException error) {
            System.err.println("File(" + file + ") not found,please try again");
            System.exit(-1);
        }
        return vocabular;
    }

    /**
     * Same function but translation of the English put in place of the key.
     *
     * @param file- name of the dictionary.
     * @return vocabular - dictionary in HashMap.
     */
    public HashMap<String, String> loadDictinoryTo(String file) {
        HashMap<String, String> vocabular = new HashMap<>();
        try (Scanner read = new Scanner(new FileReader(file))) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] lineArray = line.split("-");  // "-" separator between words
                vocabular.put(lineArray[1], lineArray[0]);
            }
        } catch (FileNotFoundException error) {
            System.err.println("File(" + file + ") not found,please try again");
            System.exit(-1);
        }
        return vocabular;
    }

    public void putNewLanguage() {
        Scanner write = new Scanner(System.in);
        System.out.println("Enter new language");
        String language = write.nextLine();
        HashMap<String, String> dictionaryNewLanguage = loadDictinory(language);
        HashMap<String, String> dictionaryToNewLanguage = loadDictinoryTo(language);
        LIST_OF_LANGUAGES.add(LIST_OF_LANGUAGES.size(), language);
        COUNT_OF_LANGUAGES.put(language, 0);
        final ArrayList<String> lang = listOfWords(language);
        LIST.add(LIST.size(), lang);
        DICTIONARY_LOAD.put(language, dictionaryNewLanguage);
        DICTIONARY_LOAD_TO.put(language, dictionaryToNewLanguage);
    }

    public ArrayList<String> listOfWords(String language) {
        ArrayList<String> listOFword = new ArrayList<>();
        return readFile(listOFword, language);
    }

    public ArrayList<String> readFile(ArrayList<String> listOfWord, String language) {
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

    public int enterANumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do?");
        System.out.println("1:Translate sentence from one language to another");
        System.out.println("2:Translate text and put translated text to another *.txt");
        System.out.println("3:Write down a new language and continue work");
        int choice = scanner.nextInt();
        return choice;
    }

    public void makeAChoice() {
        switch (enterANumber()) {
            case 1:
                translateWholeSentence();
                break;
            /*case 2:
                translateFunction();
                break;*/
            case 3:
                putNewLanguage();
                makeAChoice();
                break;
        }
    }

    public int putTextInHashMaps(String file) {
        int count = 0;
        ArrayList<String> array = new ArrayList<>();
        try (Scanner read = new Scanner(new FileReader(file))) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] arrayOfWords = line.split("((?<=[-\\t,;.?!:@\\[\\](){}_*/\\s])|(?=[-\\t,;.?!:@\\[\\](){}_*/\\s]))"); //separator between language and sentence in TextInput.txt
                for (int itter = 0; itter < arrayOfWords.length; itter++) {
                    System.out.println(itterG);
                    if (delims.contains(arrayOfWords[itter])) {
                        hashMapOfChars.put(itter, arrayOfWords[itter]);
                    }else {
                        hashMapOfWords.put(itter,arrayOfWords[itter]);
                    }
                    count++;
                }
            }
        } catch (FileNotFoundException error) {
            System.err.println("File" + file + "not found,please try again");
            System.exit(-1);
        }
        return count;
    }

    public ArrayList<String> translateText(ArrayList<String> arrayOfTextLines,
                                           ArrayList<String> reputArrayOfTextLines, TextTranslator translator, LanguageGuesser guesser, TranslatorApp app) {
        int count = 0;
        String text;
        ArrayList<String> listOfWords = new ArrayList<>();
        for (int x = 0; x < guesser.findSizeOfVocab(textFile); x++) {
            firstLanguage = guesser.languageDetection(arrayOfTextLines.get(x));
            while (true) {
                if (firstLanguage.equalsIgnoreCase("Eng")) {
                    text = translator.translateFromEnglish(arrayOfTextLines.get(x), reputArrayOfTextLines.get(x));
                    System.out.println(text);
                    break;
                } else if (reputArrayOfTextLines.get(x).equalsIgnoreCase("Eng")) {
                    text = translator.translateToEnglish(reputArrayOfTextLines.get(x), arrayOfTextLines.get(x));
                    System.out.println(text);
                    break;
                } else {
                    text = translator.translateFromEnglish(translator.translateToEnglish
                            (firstLanguage, arrayOfTextLines.get(x)), reputArrayOfTextLines.get(x));
                    System.out.println(text);
                    break;
                }
            }
            listOfWords.add(count, text);
            count++;
            System.out.println();
        }
        return listOfWords;
    }

    public void putTranslatedTextInOutputFile(ArrayList<String> text) {
        File file = new File(outputTextFile);
        try (FileWriter writer = new FileWriter(file)) {
            for (int x = 0; x < guesser.findSizeOfVocab(textFile); x++) {
                writer.write(text.get(x) + "\n");
            }
        } catch (IOException error) {
            System.err.println("File" + file + "not found");
            System.exit(-1);
        }
    }

   /* public void translateFunction() {
        putTranslatedTextInOutputFile(translateText(putTextInArray(textFile), putTextInHashMap(textFile), txtTrans, guesser, appTranslator));
    }*/

    public void translateWholeSentence() {
        Scanner write = new Scanner(System.in);
        System.out.println("Enter sentence: ");
        String sentence = write.nextLine();
        firstLanguage = guesser.languageDetection(sentence);
        System.out.println("Detected language : " + firstLanguage);
        System.out.println("To which Language translate");
        String secondLanguage = write.nextLine();
        if (!COUNT_OF_LANGUAGES.keySet().contains(firstLanguage)) {
            System.err.println("Sorry,it can`t be translated ");
            System.exit(0);
        }
        while (true) {
            if (firstLanguage.equalsIgnoreCase("Eng")) {
                System.out.println(txtTrans.translateFromEnglish(sentence, secondLanguage));
                break;
            }
            if (secondLanguage.equalsIgnoreCase("Eng")) {
                System.out.println(txtTrans.translateToEnglish(secondLanguage, sentence));
                break;
            } else {
                System.out.println(txtTrans.translateFromEnglish(txtTrans
                        .translateToEnglish(firstLanguage, sentence), secondLanguage));
                break;
            }
        }
    }
}