package SimpleSearchEngine;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    static Elements e;
    static final Scanner in = new Scanner(System.in);


    public static void main(String[] args)  {

        String fileName = getFileName(args);
        getDataFrom(fileName);
        openMenu();
    }

    static String getFileName(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if ("--data".equalsIgnoreCase(args[i])) {
                return args[++i];
            }
        }
        return "";
    }

    static void getDataFrom(String fileN) {

        try  {
            Path p = Paths.get(fileN);
            e = new Elements(Files.readAllLines(p));
        } catch (Exception ex) {
            System.out.println("'There is no such file exception'\nBye!");
            System.exit(0);
        }
    }

    static void openMenu() {

        while (true) {
            System.out.print("\n== Menu ==" +
                    "\n1. Find an element" +
                    "\n2. Print all elements" +
                    "\n0. Exit\n");

            switch (Integer.parseInt(in.nextLine())) {
                case 1:
                    search();
                    break;
                case 2:
                    printAll();
                    break;
                case 0:
                    exit();
                    return;
                default:
                    System.out.println("\nIncorrect option! Try again.");
            }
        }
    }

    static void search() {

        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strat = in.nextLine().toUpperCase();

        System.out.printf("\nEnter data for %s search:\n", strat);
        String[] data = in.nextLine().toLowerCase().split("\\s+");

        e.searchFor(data, strat);
    }

    static void printAll() { e.printAll(); }

    static void exit() { System.out.println("\nBye!"); }
}
