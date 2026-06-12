import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    static ArrayList<String> history = new ArrayList<>();

    static int totalConversions = 0;
    static int totalAnalyses = 0;
    static int lastInput = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n======================================");
            System.out.println(" SMART NUMBER SYSTEM ANALYZER PRO ");
            System.out.println("======================================");
            System.out.println("1. Decimal Converter");
            System.out.println("2. Number Analyzer");
            System.out.println("3. ASCII Viewer");
            System.out.println("4. View History");
            System.out.println("5. Statistics Dashboard");
            System.out.println("6. Export Report");
            System.out.println("7. Exit");
            System.out.println("======================================");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    convertDecimal(sc);
                    break;

                case 2:
                    analyzeNumber(sc);
                    break;

                case 3:
                    asciiViewer(sc);
                    break;

                case 4:
                    viewHistory();
                    break;

                case 5:
                    showStatistics();
                    break;

                case 6:
                    exportHistory();
                    break;

                case 7:
                    System.out.println("Program Closed.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void convertDecimal(Scanner sc) {

        System.out.print("Enter Decimal Number: ");
        int num = sc.nextInt();

        lastInput = num;
        totalConversions++;

        String binary = Integer.toBinaryString(num);
        String octal = Integer.toOctalString(num);
        String hex = Integer.toHexString(num).toUpperCase();

        System.out.println("\n=================================");
        System.out.println(" CONVERSION RESULT ");
        System.out.println("=================================");

        System.out.println("Decimal      : " + num);
        System.out.println("Binary       : " + binary);
        System.out.println("Octal        : " + octal);
        System.out.println("Hexadecimal  : " + hex);

        System.out.println("\nBit Weight Visualization");

        int weight = 1 << (binary.length() - 1);

        for (int i = 0; i < binary.length(); i++) {
            System.out.print(weight + "\t");
            weight /= 2;
        }

        System.out.println();

        for (char bit : binary.toCharArray()) {
            System.out.print(bit + "\t");
        }

        System.out.println();

        history.add("Converted Decimal " + num +
                " -> Binary=" + binary +
                ", Octal=" + octal +
                ", Hex=" + hex);
    }

    public static void analyzeNumber(Scanner sc) {

        System.out.print("Enter Number: ");
        int num = sc.nextInt();

        totalAnalyses++;
        lastInput = num;

        String binary = Integer.toBinaryString(num);

        System.out.println("\n=================================");
        System.out.println(" NUMBER ANALYSIS REPORT ");
        System.out.println("=================================");

        System.out.println("Decimal      : " + num);
        System.out.println("Binary       : " + binary);
        System.out.println("Octal        : " +
                Integer.toOctalString(num));

        System.out.println("Hexadecimal  : " +
                Integer.toHexString(num).toUpperCase());

        System.out.println("Bits Used    : " +
                binary.length());

        if (num % 2 == 0)
            System.out.println("Odd/Even     : Even");
        else
            System.out.println("Odd/Even     : Odd");

        if (isPrime(num))
            System.out.println("Prime        : Yes");
        else
            System.out.println("Prime        : No");

        if ((num & (num - 1)) == 0 && num > 0)
            System.out.println("Power Of Two : Yes");
        else
            System.out.println("Power Of Two : No");

        System.out.println();

        System.out.println("Binary Visualization");

        for (char bit : binary.toCharArray())
            System.out.print("+---");
        System.out.println("+");

        for (char bit : binary.toCharArray())
            System.out.print("| " + bit + " ");
        System.out.println("|");

        for (char bit : binary.toCharArray())
            System.out.print("+---");
        System.out.println("+");

        history.add("Analyzed Number " + num);
    }

    public static boolean isPrime(int n) {

        if (n <= 1)
            return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {

            if (n % i == 0)
                return false;
        }

        return true;
    }

    public static void asciiViewer(Scanner sc) {

        System.out.print("Enter ASCII Number (0-127): ");

        int num = sc.nextInt();

        if (num >= 0 && num <= 127) {

            char ch = (char) num;

            System.out.println("\nASCII Character : " + ch);
            System.out.println("ASCII Code      : " + num);

            history.add("ASCII Lookup : " +
                    num + " -> " + ch);

        } else {

            System.out.println("Invalid ASCII Code");
        }
    }

    public static void viewHistory() {

        System.out.println("\n=================================");
        System.out.println(" HISTORY ");
        System.out.println("=================================");

        if (history.isEmpty()) {

            System.out.println("No Records Found.");
            return;
        }

        for (String record : history) {

            System.out.println(record);
        }
    }

    public static void showStatistics() {

        System.out.println("\n=================================");
        System.out.println(" STATISTICS DASHBOARD ");
        System.out.println("=================================");

        System.out.println("Total Conversions : " +
                totalConversions);

        System.out.println("Total Analyses    : " +
                totalAnalyses);

        System.out.println("Last Input        : " +
                lastInput);

        System.out.println("History Entries   : " +
                history.size());
    }

    public static void exportHistory() {

        try {

            FileWriter fw =
                    new FileWriter("SmartReport.txt");

            fw.write(
                    "SMART NUMBER SYSTEM ANALYZER PRO\n");

            fw.write(
                    "====================================\n");

            for (String record : history) {

                fw.write(record + "\n");
            }

            fw.close();

            System.out.println(
                    "Report Exported Successfully!");

            System.out.println(
                    "File Name : SmartReport.txt");

        } catch (IOException e) {

            System.out.println(
                    "Error While Exporting File.");
        }
    }
}