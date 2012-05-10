import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Holmes {
    public static final String TRUTH = "Truth";
    public static final String NOT_DEFINED = "Not defined";
    public static final String LIE = "Lie";
    private final int truthSuspectsNumber;
    private final int suspectsNumber;
    private int protectorsNumber;

    private int[] testify;
    private boolean[] sign;
    private int[] protectors;
    private int[] prosecutors;
    private boolean[] offenders;
    private int offendersCounter = 0;

    public Holmes(final int suspectsNumber, final int truthSuspectsNumber) {
        this.suspectsNumber = suspectsNumber;
        this.truthSuspectsNumber = truthSuspectsNumber;
        offenders = new boolean[suspectsNumber];
        prosecutors = new int[suspectsNumber];
        protectors = new int[suspectsNumber];
        sign = new boolean[suspectsNumber];
        testify = new int[suspectsNumber];
    }

    public void addSuspect(final String sign, final int testify) {
        for (int i = 0; i < suspectsNumber; i++) {
            this.testify[i] = testify;
            if ("+".equals(sign)) {
                this.sign[i] = true;
                prosecutors[this.testify[i]]++;
            } else {
                protectors[this.testify[i]]++;
                protectorsNumber++;
            }
        }

    }

    private void detectOffenders() {
        for (int i = 0; i < suspectsNumber; i++) {
            if (prosecutors[i] + protectorsNumber - protectors[i] == truthSuspectsNumber) {
                offenders[i] = true;
                offendersCounter++;
            }
        }

        for (int i = 0; i < suspectsNumber; i++) {
            if (sign[i]) {
                if (offenders[testify[i]]) {
                    if (offendersCounter == 1) {
                        System.out.println(TRUTH);
                    } else {
                        System.out.println(NOT_DEFINED);
                    }
                } else {
                    System.out.println(LIE);
                }
            } else {
                if (offenders[testify[i]]) {
                    if (offendersCounter < 2) {
                        System.out.println(LIE);
                    } else {
                        System.out.println(NOT_DEFINED);
                    }
                } else {
                    System.out.println(TRUTH);
                }
            }

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int suspectsNumber = scanner.nextInt();
        final int truthSuspectsNumber = scanner.nextInt();
        scanner.nextLine();
        Holmes holmes = new Holmes(suspectsNumber, truthSuspectsNumber);


        Pattern pattern = Pattern.compile("(\\+|\\-)([\\d]+)");
        for (int i = 0; i < suspectsNumber; i++) {
            String penance = scanner.nextLine();
            Matcher m = pattern.matcher(penance);
            if (m.matches()) {
                holmes.addSuspect(m.group(1), Integer.valueOf(m.group(2)) - 1);
            }
        }

        holmes.detectOffenders();
    }
}
