package com.github.neophite.Translator;

import static com.github.neophite.Translator.TranslatorApp.DICTIONARY_LOAD;
import static com.github.neophite.Translator.TranslatorApp.firstLanguage;
import static com.github.neophite.Translator.TranslatorApp.DICTIONARY_LOAD_TO;

public class TextTranslator {
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

}
