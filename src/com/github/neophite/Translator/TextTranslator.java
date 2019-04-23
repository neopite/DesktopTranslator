package com.github.neophite.Translator;

import static com.github.neophite.Translator.TranslatorApp.*;

public class TextTranslator {
    public String translateToEnglish(String lang, String sentence) {
        String word = "";
        String str = "";
        switch (lang) {
            case "Eng":
                for (String words : sentence.split(" ")) {
                    word = findNonexistentWords(words);
                    if (word.isEmpty()) {
                        System.out.println("Word:" + words + " doesn`t exist in our dictionary" +
                                ",you can add this translation in dictionary and solve this problem");
                        str += "";
                    } else {
                        str += DICTIONARY_LOAD.get(firstLanguage).get(word) + " ";
                    }
                }

                break;
            default:
                for (String words : sentence.split(" ")) {
                    word = findNonexistentWords(words);
                    if (word.isEmpty()) {
                        System.out.println("Word:" + words + " doesn`t exist in our dictionary" +
                                ",you can add this translation in dictionary and solve this problem");
                        str += "";
                    } else {
                        str += DICTIONARY_LOAD.get(lang).get(word) + " ";
                    }
                }
        }
        return str;

    }

    public String translateFromEnglish(String str, String lang) {
        String inputWord = "";
        String words = "";
        for (String line : str.split(" ")) {
            inputWord = findNonexistentWords(line);
            if (inputWord.isEmpty()) {
                System.out.println("Word:" + line + " doesn`t exist in our dictionary" +
                        ",you can add this translation in dictionary and solve this problem");
                words += "";

            } else {
                words += DICTIONARY_LOAD_TO.get(lang).get(inputWord) + " ";
            }
        }

        return words;
    }

    /**
     * Method verifies the current word for existence in dictionaries.
     *
     * @param words - current input word.
     * @return if word exist in dictionary(return word),if word doesn`t exist (return empty word).
     */
    public String findNonexistentWords(String words) {
        int empty = 0;
        for (int count = 0; count < LIST.size(); count++) {
            if (!LIST.get(count).contains(words)) {
                empty++;
                if (empty == LIST.size()) {
                    words = "";
                }
            }
        }
        return words;
    }
}