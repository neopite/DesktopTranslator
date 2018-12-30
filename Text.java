import javax.script.ScriptContext;
import java.io.*;
import java.util.Scanner;

public class Text {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        File file=new File("/home/yarik/IdeaProjects/Practice/TextFile.txt");
        Scanner scanner=new Scanner(new FileReader(file));
        String text;
        while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());

        }

    }

}
