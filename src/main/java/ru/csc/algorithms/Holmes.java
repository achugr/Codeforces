package ru.csc.algorithms;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: achugr
 * Date: 09.05.12
 * Time: 15:18
 */

public class Holmes {

    private static int truthSuspectsNumber;
    private static int suspectsNumber;
    private static int protectorsNumber;

    private static int[] testify;
    private static boolean[] sign;
    private static int[] protectors;
    private static int[] prosecutors;
    private static boolean[] offenders;
    private static int offendersCounter = 0;


    private static void readInput() {
        Scanner scanner = new Scanner(System.in);
        suspectsNumber = scanner.nextInt();
        truthSuspectsNumber = scanner.nextInt();
        scanner.nextLine();
        offenders = new boolean[suspectsNumber];
        prosecutors = new int[suspectsNumber];
        protectors = new int[suspectsNumber];
        sign = new boolean[suspectsNumber];
        testify = new int[suspectsNumber];

        Pattern pattern = Pattern.compile("(\\+|\\-)([\\d]+)");
        for (int i = 0; i < suspectsNumber; i++) {
            String penance = scanner.nextLine();
            Matcher m = pattern.matcher(penance);
            if (m.matches()) {
                testify[i] = Integer.valueOf(m.group(2)) - 1;
                if (m.group(1).equals("+")) {
                    sign[i] = true;
                    prosecutors[testify[i]]++;
                } else {
                    protectors[testify[i]]++;
                    protectorsNumber++;
                }
            }
        }
    }

    private static void detectOffenders() {
        for (int i = 0; i < suspectsNumber; i++) {
            if (prosecutors[i] + protectorsNumber - protectors[i] == truthSuspectsNumber) {
                offenders[i] = true;
                offendersCounter++;
            }
        }
    }

    private static void writeOutput() {
        for (int i = 0; i < suspectsNumber; i++) {
            if (sign[i]) {
                if (offenders[testify[i]]) {
                    if (offendersCounter == 1) {
                        System.out.println("Truth");
                    } else {
                        System.out.println("Not defined");
                    }
                } else {
                    System.out.println("Lie");
                }
            } else {
                if (offenders[testify[i]]) {
                    if (offendersCounter < 2) {
                        System.out.println("Lie");
                    } else {
                        System.out.println("Not defined");
                    }
                } else {
                    System.out.println("Truth");
                }
            }

        }

    }

    public static void main(String[] args) {
        readInput();
        detectOffenders();
        writeOutput();
    }
}
