import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Stanislav Fedii
 * Date: 9/20/17
 * Time: 9:43 PM
 */
public class Question1 {

    /*
    * Given a string, reverse each word in it and reverse the entire string.
    * For example, if the given string is “hope you are doing well”, then the output should be “llew gniod era uoy epoh”.
    * Assume that all characters in the string are lower case.
    * Create a method called reverseEverything(String s) where ‘s’ is the
    * input from the user in order to solve this question.
    */

    public static void main(String[] args) {
        String panagram = "The quick brown fox jumps over the lazy dog";
        System.out.println(String.format("Original string: %s", panagram));
        System.out.println(String.format("Reverse entire string: %s", reverseEverything(panagram)));
        System.out.println(String.format("Reverse word by word: %s", reverseEachWord(panagram)));
    }

    /**
     * Reverses the sentence.
     * @param s sentence to reverse
     * @return reversed string
     */
    private static String reverseEverything(String s) {
        String result = "";
        char[] characters = s.toCharArray();
        for (int i = characters.length - 1; i >= 0; i--) {
            result = result + characters[i];
        }

        return result;
    }

    /**
     * Reverses the sentence word by word
     * @param s sentence to reverse
     * @return result of the reversal
     */
    private static String reverseEachWord(String s) {
        List<String> words = Arrays.asList(s.split(" "));
        Collections.reverse(words);
        return String.join(" ", words);
    }
}
