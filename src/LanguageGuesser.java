package Translator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LanguageGuesser extends Main {

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
