import java.util.Scanner;

/**
 * @author daddy-bear
 *         Date: 09.05.12 - 23:17
 */

public class Message {

    public final static String PSEUDO_SYMBOLS;

    static {
        String tmp = new String();
        for (int i = 0; i < 2000; i++) {
            tmp += "@";
        }

        PSEUDO_SYMBOLS = tmp;
    }

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final String source = PSEUDO_SYMBOLS + scanner.nextLine() + PSEUDO_SYMBOLS;

        final String message = scanner.nextLine();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= source.length() - message.length(); i++) {
            final String subString = source.substring(i, i + message.length());
            min = Math.min(min, difference(subString, message));
        }
        System.out.println(min);
    }

    private static int difference(final String str1, final String str2) {
        int difference = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                difference++;
            }
        }

        return difference;
    }
}
