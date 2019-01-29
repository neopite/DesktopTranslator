package com.github.neophite.Translator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.github.neophite.Translator.TranslatorApp.LIST_OF_LANGUAGES;
import static com.github.neophite.Translator.TranslatorApp.findSizeOfVocab;
import static com.github.neophite.Translator.TranslatorApp.LIST;
import static com.github.neophite.Translator.TranslatorApp.COUNT_OF_LANGUAGES;

public class LanguageGuesser {
    /**
     * Function returns language after checking all the words of the sentence for max similarity with this language.
     *
     * @param sentence -sentence from user.
     * @return language from list(Eng,Rus,Ua,etc).
     */
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

    /**
     * Function updates counter of each language in COUNT_OF_LANGUAGES and
     * return how many times a word in a sentence belongs to the language.
     *
     * @param map    - dictionary.
     * @param number - counter.
     * @param list   - counter of each language in COUNT_OF_LANGUAGE.
     * @return update COUNT_OF_LANGUAGE.
     */
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

}
