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

    private int truthSuspectsNumber;
    private int suspectsNumber;
    private int protectorsNumber;

    private int[] testify;
    private boolean[] sign;
    private int[] protectors;
    private int[] prosecutors;
    private boolean[] offenders;
    private int offendersCounter = 0;
    private static final String PLUS = "+";
    private static final String TRUTH = "Truth";
    private static final String LIE = "Lie";
    private static final String NOT_DEFINED = "Not defined";



    private void readInput() {
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
                if (PLUS.equals(m.group(1))) {
                    sign[i] = true;
                    prosecutors[testify[i]]++;
                } else {
                    protectors[testify[i]]++;
                    protectorsNumber++;
                }
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
    }

    private void writeOutput() {
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
        Holmes holmes = new Holmes();
        holmes.readInput();
        holmes.detectOffenders();
        holmes.writeOutput();
    }
}
