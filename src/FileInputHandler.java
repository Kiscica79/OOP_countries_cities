import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileInputHandler {


    public static List<String> getLinesOfTextFileWithScanner(String filePath) {
        List<String> strings = new ArrayList<>();
        File file = new File(filePath);

        try {
            Scanner sc = new Scanner(file);
            while ((sc.hasNextLine())) {
                strings.add(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found!");
        }
        return strings;
    }

    public static List<String> getLinesOfTextFile(String filePath) {
        List<String> strings = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (String line; (line = bufferedReader.readLine()) != null; ) {
                strings.add(line);
            }

        } catch (IOException e) {
            System.out.println("Error: wrong path!\n" + e.getMessage());
        }

        return strings;
    }



}
