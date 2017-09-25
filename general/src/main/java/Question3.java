import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/20/17
 * Time: 9:54 PM
 */
public class Question3 {

    /*
    There is a file containing a word and its possible meanings (like a Dictionary). The contents of the file look like this:

    Apple – a fruit, a tech firm
    Table – an object, contains rows and columns when used in context of computers
    Orange – a fruit

    Given a path to the file do the following:

    a)	Create a method called doesFileExist(String path) which takes the path of the file and tells the user if
    the file exists at that path or not. Assume all paths are relative to your project structure.
    If the file does not exist, catch the requisite exception.
    b)	Read each word and its possible meanings and print them out. Your output should look like this:

    Word1
    Meaning 1                    and
    Meaning 2
    Word2
    Meaning1
    Meaning2

    Use appropriate data structures wherever necessary.
     */

    public static void main(String[] args) {
        String validLocation = "general/dictionary.txt";
        String invalidLocation = "fjghsduklfgfjl sdklgs";
        System.out.println(doesFileExist(validLocation));
        System.out.println(doesFileExist(invalidLocation));
        System.out.println();
        System.out.println("Valid behavior example:");
        outputEntriesJava8(validLocation);
//        outputEntries(validLocation);

//        System.out.println("Example of exceptions being caught:");
//        outputEntriesJava8(invalidLocation);
//        outputEntries(invalidLocation);
    }

    /**
     * Tests whether the file specified by the filepath exists. Also verifies whether it is a file and not a folder.
     * @param path filepath to test
     * @return result of the test
     */
    static boolean doesFileExist(String path) {
        File file = new File(path);
        System.out.println(file.getAbsoluteFile());

        return file.isFile() && file.exists();
    }

    /**
     * Outputs entries of the file found in the provided path. Method uses Java8-specific methods and classes.
     * @param path filepath
     */
    static void outputEntriesJava8(String path) {
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(line -> {
                String[] wordMeaningsPair = line.split(" – ");
                System.out.println(wordMeaningsPair[0]);
                for (String meaning : wordMeaningsPair[1].split(", ")) {
                    System.out.println(meaning);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Outputs entries of the file found in the provided path. Method uses pre-Java8-specific methods and classes.
     * @param path filepath
     */
    static void outputEntries(String path) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new File(path)));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] wordMeaningsPair = line.split(" – ");
                System.out.println(wordMeaningsPair[0]);
                for (String meaning : wordMeaningsPair[1].split(", ")) {
                    System.out.println(meaning);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
