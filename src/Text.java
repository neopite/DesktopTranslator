import javax.script.ScriptContext;
import java.io.*;
import java.util.Scanner;

public class Text {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter("/home/yarik/IdeaProjects/Practice/TextFile.txt"));
        String text;
        while (!(text = br.readLine()).equalsIgnoreCase("ESC")) {
            bw.write(text + " ");
            bw.flush();

        }
        br.close();
        bw.close();
    }

}
