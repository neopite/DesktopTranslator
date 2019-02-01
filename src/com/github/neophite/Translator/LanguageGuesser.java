package com.github.neophite.Translator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class LanguageGuesser {
    private static ArrayList<ArrayList<String>> LIST = new ArrayList<>();
    private static HashMap<Integer, String> LIST_OF_LANGUAGES = new HashMap<>();
    private static HashMap<String, Integer> COUNT_OF_LANGUAGES = new HashMap<>();

    LanguageGuesser(ArrayList<ArrayList<String>> LIST, HashMap<Integer,
            String> LIST_OF_LANGUAGE, HashMap<String, Integer> COUNT_OF_LANGUAGES) {
        this.LIST = LIST;
        this.LIST_OF_LANGUAGES = LIST_OF_LANGUAGE;
        this.COUNT_OF_LANGUAGES = COUNT_OF_LANGUAGES;
    }

    /**
     * Function returns language after checking all the words of the sentence for max similarity with this language.
     *
     * @param sentence -sentence from user.
     * @return language from list(Eng,Rus,Ua,etc).
     */
    public String languageDetection(String sentence) {
        for (String word : sentence.split(" ")) {
            for (int x = 0; x < LIST.size(); x++) {
                int size = findSizeOfVocab(LIST_OF_LANGUAGES.get(x));
                for (int z = 0; z < size; z++) {
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
    public HashMap<String, Integer> findLanguageForTranslation(HashMap<Integer, String> map, int number,
                                                               HashMap<String, Integer> list) {
        for (int x = 0; x < LIST_OF_LANGUAGES.size(); x++) {
            if (map.get(number).contains(LIST_OF_LANGUAGES.get(x))) {
                int count = list.get(LIST_OF_LANGUAGES.get(x));
                list.put(LIST_OF_LANGUAGES.get(x), count + 1);
            }
        }
        return list;
    }

    public int findSizeOfVocab(String language) {
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
}
