import java.util.*;

public class DiffTranslator {
    public static void main(String[] args)  {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("Привет", "Hello");
        hashMap.put("Мир", "World");
        hashMap.put("Пока", "Bye");
        hashMap.put("Никита", "Nikita");
        System.out.println("Enter a sentence :");
        String line = enterSentence();
        for (String words : line.split(" ")) {
            System.out.print(hashMap.get(words) + " ");
        }
    }

    public static String enterSentence() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

