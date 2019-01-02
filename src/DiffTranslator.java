import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class DiffTranslator {
    public static void main(String[] args) throws FileNotFoundException {
        File file=new File("TextFile.txt");
        Scanner scanner=new Scanner(new FileReader(file));
        HashMap<String, String> vocabular = new HashMap<>();
        while(scanner.hasNextLine()){
            String line=scanner.nextLine();
            String [] lineArray=line.split("-");
            System.out.println(lineArray.length);
            vocabular.put(lineArray[0],lineArray[1]);
        }
        System.out.println("Enter a sentence :");
        String line = enterSentence();
        for (String words : line.split(" ")) {
            System.out.print(vocabular.get(words) + " ");
        }
    }

    public static String enterSentence() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

