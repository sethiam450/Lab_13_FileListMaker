/*
 * Decompiled with CFR 0.150.
 */

import java.util.Scanner;

public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.println("\n" + prompt + ": ");
        } while ((retString = pipe.nextLine()).length() == 0);
        return retString;
    }

    public static int getInt(Scanner pipe, String prompt) {
        int retInt = 0;
        boolean validInput = false;
        do {
            System.out.println("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retInt = pipe.nextInt();
                validInput = true;
                continue;
            }
            if (!pipe.hasNext() || pipe.hasNextInt()) continue;
            System.out.println("\nInvalid input! You must input an integer!");
            String string = pipe.nextLine();
        } while (!validInput);
        return retInt;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double retDouble = 0.0;
        boolean validInput = false;
        do {
            System.out.println("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retDouble = pipe.nextDouble();
                validInput = true;
                continue;
            }
            if (!pipe.hasNext() || pipe.hasNextDouble()) continue;
            System.out.println("\nInvalid input! You must input a number!");
            String string = pipe.nextLine();
        } while (!validInput);
        return retDouble;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int rangeMin, int rangeMax) {
        int retInt = 0;
        boolean validInput = false;
        int input = 0;
        do {
            String string;
            System.out.println("\n" + prompt + " [" + rangeMin + " - " + rangeMax + "]: ");
            input = 0;
            if (pipe.hasNextInt()) {
                input = pipe.nextInt();
                if (input > rangeMax || input < rangeMin) {
                    System.out.println("\nInvalid input! You must input an integer in the specified range!");
                    string = pipe.nextLine();
                    continue;
                }
                if (input < rangeMin || input > rangeMax) continue;
                retInt = input;
                validInput = true;
                continue;
            }
            if (!pipe.hasNext() || pipe.hasNextInt()) continue;
            System.out.println("\nInvalid input! You must input an integer in the specified range!");
            input = 0;
            string = pipe.nextLine();
        } while (!validInput);
        return retInt;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double rangeMin, double rangeMax) {
        double retDouble = 0.0;
        boolean validInput = false;
        double input = 0.0;
        do {
            String string;
            System.out.println("\n" + prompt + " [" + rangeMin + " - " + rangeMax + "]: ");
            input = 0.0;
            if (pipe.hasNextDouble()) {
                input = pipe.nextDouble();
                if (input > rangeMax || input < rangeMin) {
                    System.out.println("\nInvalid input! You must input a number in the specified range!");
                    string = pipe.nextLine();
                    continue;
                }
                if (!(input >= rangeMin) || !(input <= rangeMax)) continue;
                retDouble = input;
                validInput = true;
                continue;
            }
            if (!pipe.hasNext() || pipe.hasNextInt()) continue;
            System.out.println("\nInvalid input! You must input an integer in the specified range!");
            input = 0.0;
            string = pipe.nextLine();
        } while (!validInput);
        return retDouble;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean validInput = false;
        do {
            System.out.println("\n" + prompt + " [Y/N]: ");
            if (!pipe.hasNextLine()) continue;
            String input = pipe.nextLine();
            if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("yes")) {
                validInput = true;
                return true;
            }
            if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                validInput = true;
                return false;
            }
            System.out.println("\nInvalid input! Your input must be yes or no!");
            Object var3_3 = null;
        } while (!validInput);
        return true;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        boolean validInput = false;
        String input = null;
        do {
            System.out.println("\n" + prompt + ": ");
            if (!pipe.hasNextLine()) continue;
            input = pipe.nextLine();
            if (input.matches(regEx)) {
                validInput = true;
                continue;
            }
            System.out.println("Invaid input! Your input must be in the specified format!");
            input = null;
        } while (!validInput);
        return input;
    }
}

