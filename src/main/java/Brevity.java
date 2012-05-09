import java.util.*;

public class Brevity {

    private final static String NOT_A_STRING = "NOT_A_STRING";

    private final Map<String, Set<String>> graph = new LinkedHashMap<String, Set<String>>();

    public void addWord(final String word) {
        graph.put(word, brevityWordGenerator(word));
    }

    private boolean findPath(final String word, final Map<String, String> matching, final Map<String, Boolean> visited) {
        visited.put(word, true);

        for (Map.Entry<String, String> e : matching.entrySet()) {
            String value = e.getValue();
            String key = e.getKey();
            if (graph.get(word).contains(e.getKey()) && (NOT_A_STRING.equals(value) || !visited.get(value) && findPath(value, matching, visited))) {
                matching.put(key, word);
                return true;
            }
        }

        return false;
    }

    public void findShort() {

        Map<String, String> matching = new HashMap<String, String>();

        for (Map.Entry<String, Set<String>> e : graph.entrySet()) {
            for (String s : e.getValue()) {
                if (!matching.containsKey(s)) {
                    matching.put(s, NOT_A_STRING);
                }
            }
        }

        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        for (String word : graph.keySet()) {
            findPath(word, matching, visited);
        }

        final Map<String, String> reverseMatching = new HashMap<String, String>();
        for (Map.Entry<String, String> e : matching.entrySet()) {
            if (!NOT_A_STRING.equals(e.getValue())) {
                reverseMatching.put(e.getValue(), e.getKey());
            }
        }

        if (reverseMatching.size() != graph.size()) {
            System.out.println(-1);
        } else {
            for (String k: graph.keySet()) {
                System.out.println(reverseMatching.get(k));
            }
        }
    }

    public static void main(final String[] args) {
        Brevity brevity = new Brevity();

        Scanner scanner = new Scanner(System.in);
        int wordCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < wordCount; i++) {
            brevity.addWord(scanner.nextLine());
        }

        brevity.findShort();
    }

    private static Set<String> brevityWordGenerator(final String word) {
        int wordLength = word.length();

        final Set<String> brevityWords = new HashSet<String>();
        for (int i = 1; i <= 4; i++) {
            for (int j = 0; j <= wordLength - i; j++) {
                brevityWords.add(word.substring(j, j + i));
            }
        }

        return brevityWords;
    }
}
