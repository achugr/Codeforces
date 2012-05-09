import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: achugr
 * Date: 08.05.12
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
public class Matching {

    private static final HashSet<String> sourceWords = new HashSet<String>();
    private static final HashSet<String> shortWords = new HashSet<String>();

    public void readInput(){
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            int wordsNumber = scanner.nextInt();
            while (scanner.hasNext()){
                sourceWords.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void main(String[] args) {
        Matching task = new Matching();
        task.readInput();
        System.out.println(Arrays.toString(sourceWords.toArray()));
    }
}
