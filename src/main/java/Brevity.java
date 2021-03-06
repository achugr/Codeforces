import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

public class Brevity {

    private final static String NOT_A_STRING = "NOT_A_STRING";

    private final Map<String, Set<String>> graph = new LinkedHashMap<String, Set<String>>();

    public void addWord(final String word) {
        graph.put(word, brevityWordGenerator(word));
    }

    private boolean findPath(final String word, final Map<String, String> matching, final Map<String, Boolean> visited) {
        visited.put(word, true);

        for (final Map.Entry<String, String> e : matching.entrySet()) {
            final String value = e.getValue();
            final String key = e.getKey();
            if (graph.get(word).contains(key) && (NOT_A_STRING.equals(value) || !visited.get(value) && (findPath(value, matching, visited)))) {
                matching.put(key, word);
                return true;
            }
        }

        return false;
    }

    public void findShort() throws FileNotFoundException {

        final Map<String, String> matching = new HashMap<String, String>();

        for (final Map.Entry<String, Set<String>> e : graph.entrySet()) {
            for (final String s : e.getValue()) {
                if (!matching.containsKey(s)) {
                    matching.put(s, NOT_A_STRING);
                }
            }
        }

        for (final String word : graph.keySet()) {
            final Map<String, Boolean> visited = new HashMap<String, Boolean>();
            for (final String w : graph.keySet()) {
                visited.put(w, false);
            }
            findPath(word, matching, visited);
        }

        final Map<String, String> reverseMatching = new HashMap<String, String>();
        for (final Map.Entry<String, String> e : matching.entrySet()) {
            if (!NOT_A_STRING.equals(e.getValue())) {
                reverseMatching.put(e.getValue(), e.getKey());
            }
        }

        final PrintWriter pw = new PrintWriter("output.txt");
        if (reverseMatching.size() != graph.size()) {
            pw.println(-1);
        } else {
            for (final String k : graph.keySet()) {
                pw.println(reverseMatching.get(k));
            }
        }
        pw.close();
    }

    public static void main(final String[] args) throws FileNotFoundException {
        final Brevity brevity = new Brevity();

        final Scanner scanner = new Scanner(new BufferedReader(new FileReader("input.txt")));
        int wordCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < wordCount; i++) {
            brevity.addWord(scanner.nextLine());
        }

        brevity.findShort();
    }

    private static Set<String> brevityWordGenerator(final String word) {
        int wordLength = word.length();

        final Set<String> brevityWords = new HashSet<String>();
        for (int j = 0; j < wordLength; j++) {
            walk(word.substring(j, j + 1), j + 1, word.length(), word, brevityWords);
        }

        return brevityWords;
    }

    private static void walk(final String seq, final int end, final int fullSize, final String initial, final Set<String> brevityWords) {
        brevityWords.add(seq);

        if (seq.length() == 4) {
            return;
        }

        for (int i = end; i < fullSize; i++) {
            walk(seq + initial.charAt(i), i + 1, fullSize, initial, brevityWords);
        }
    }
}
