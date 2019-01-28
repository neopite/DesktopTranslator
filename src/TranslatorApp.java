package Translator;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TranslatorApp extends TextTranslator{
    public static ArrayList<String> putTextInArray(String file) {
        int count = 0;
        ArrayList<String> array = new ArrayList<>();
        try (Scanner read = new Scanner(new FileReader(file))) {
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] arrayOfWords = line.split(";"); //separator between language and sentence in TextInput.txt
                array.add(count, arrayOfWords[1]);
                count++;
            }
        } catch (FileNotFoundException error) {
            System.err.println("File" + file + "not found,please try again");
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
            System.err.println("File" + file + "not found,please try again");
            System.exit(-1);
        }

        return array;
    }

    public static ArrayList<String> translateText(ArrayList<String> arrayOfTextLines, ArrayList<String> reputArrayOfTextLines) {
        int count = 0;
        String text;
        ArrayList<String> listOfWords = new ArrayList<>();
        for (int x = 0; x < LanguageGuesser.findSizeOfVocab(textFile); x++) {
            firstLanguage = LanguageGuesser.languageDetection(arrayOfTextLines.get(x));
            System.out.println(arrayOfTextLines.get(x) + " " + firstLanguage);
            while (true) {
                if (firstLanguage.equalsIgnoreCase("Eng")) {
                    text = translateFromEnglish(arrayOfTextLines.get(x), reputArrayOfTextLines.get(x));
                    break;
                } else if (reputArrayOfTextLines.get(x).equalsIgnoreCase("Eng")) {
                    text = translateToEnglish(reputArrayOfTextLines.get(x), arrayOfTextLines.get(x));
                    break;
                } else {
                    text = translateFromEnglish(translateToEnglish(firstLanguage, arrayOfTextLines.get(x)), reputArrayOfTextLines.get(x));
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
        File file = new File(outputTextFile);
        try (FileWriter writer = new FileWriter(file)) {
            for (int x = 0; x < LanguageGuesser.findSizeOfVocab(textFile); x++) {
                writer.write(text.get(x) + "\n");
            }
        } catch (IOException error) {
            System.err.println("File" + file + "not found");
            System.exit(-1);
        }
    }
    public static void translateFunction(){
        putTranslatedTextInOutputFile(translateText(putTextInArray(textFile), putTextInHashMap(textFile)));
    }
}
