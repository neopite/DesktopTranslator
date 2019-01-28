package Translator;


import java.util.Scanner;

public class TextTranslator extends Main {

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
            System.err.println("Language(" + lang + ")is not contained in dictionaries");
            System.exit(-1);
        }
        return words;
    }
    public static void translateWholeSentence(){
        Scanner write = new Scanner(System.in);
        System.out.println("Enter sentence: ");
        String sentence = write.nextLine();
        firstLanguage = LanguageGuesser.languageDetection(sentence);
        System.out.println("Detected language : " + firstLanguage);
        System.out.println("To which Language translate");
        String secondLanguage = write.nextLine();
        if (!COUNT_OF_LANGUAGES.keySet().contains(firstLanguage)) {
            System.err.println("Sorry,it can`t be translated ");
            System.exit(0);
        }
        while (true) {
            if (firstLanguage.equalsIgnoreCase( "Eng")) {
                translateFromEnglish(sentence, secondLanguage);
                break;
            }
            if (secondLanguage.equalsIgnoreCase("Eng")) {
                translateToEnglish(secondLanguage, sentence);
                break;
            } else {
                translateFromEnglish(translateToEnglish(firstLanguage, sentence), secondLanguage);
                break;
            }
        }
    }
}
