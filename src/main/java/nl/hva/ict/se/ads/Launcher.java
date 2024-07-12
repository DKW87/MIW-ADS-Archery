package nl.hva.ict.se.ads;

import comparator.*;


import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author Danny KWANT
 * @studentnumber 500955184
 *
 * Big-O Conclusion:
 * In my project, insertionSort and selectionSort both have O(n^2) complexity, making them inefficient for large datasets.
 * In contrast, collectionSort uses Timsort with O(n log n), offering better performance for larger lists.
 * Using a Comparator allows flexible sorting criteria, and a boolean for descending order enhances functionality.
 * Overall, Collections.sort is the optimal choice for efficiency due to being O(n log n).
 */
public class Launcher {

    private static long timeMs;

    public static void main(String[] args) {
        startTime();
        List<Archer> unsortedArcherList = Archer.generateArchers(150000);
        for (Archer archer : unsortedArcherList) {
            System.out.println(archer);
        }
        stopTime();
        pressEnterToContinue();
        System.out.println();

        // switched id and last name around since id is already sorted correctly after generating
        System.out.println("------- bezig met sorteren op achternaam ---------");
        printAndTimeSortedList(unsortedArcherList, new LastNameComparator(), false,
                false, true);

        System.out.println("------- bezig met sorteren op Id ---------");
        printAndTimeSortedList(unsortedArcherList, new IdComparator(), false,
                false, false);

        System.out.println("------- bezig met sorteren op hoogste score (schema 1) ---------");
        printAndTimeSortedList(unsortedArcherList, new Schema1Comparator(), true,
                false, true);

        System.out.println("------- bezig met sorteren op top rondes (schema 2)---------");
        printAndTimeSortedList(unsortedArcherList, new Schema2Comparator(), true,
                false, false);

        // make 2 copies of the Schema2 sorted list to sort back to Schema1 for efficiency testing
        List<Archer> sortedArcherListCollectionSort = unsortedArcherList;
        List<Archer> sortedArcherListBasicSort = unsortedArcherList;

        System.out.println("------- controleren efficiëntie van sorteer algoritmes ---------");

        System.out.println("\n------- bezig met Java collectionSort ---------");
        printAndTimeSortedList(sortedArcherListCollectionSort, new Schema1Comparator(), false,
                true, false);

        System.out.println("------- bezig met eigen basicSort (insertion/selection) ---------");
        printAndTimeSortedList(sortedArcherListBasicSort, new Schema1Comparator(), false,
                false, true);

    } // main

    // methods
    private static void printAndTimeSortedList(List<Archer> archerList, Comparator<Archer> comparator, boolean descending, boolean useJavaCollections, boolean useInsertionSort) {
        startTime(); // start tracking processing time
        if (useJavaCollections) ChampionSelector.collectionSort(archerList, comparator, descending);
        else ChampionSelector.basicSort(archerList, comparator, descending, useInsertionSort);
        for (Archer archer : archerList) System.out.println(archer);
        stopTime(); // end tracking and report processing time to user
        pressEnterToContinue(); // allows user to check processing time of request before continuing to next process
        System.out.println(); // empty line for formatting
    }

    private static void startTime() {
        timeMs = System.currentTimeMillis();
    }

    private static void stopTime() {
        System.out.printf("%nVerwerkingstijd voor deze actie was: %d Ms %n", System.currentTimeMillis() - timeMs);
    }

    private static void pressEnterToContinue() {
        Scanner userInput = new Scanner(System.in);
        System.out.print("\nDruk op enter om verder te gaan...");
        userInput.nextLine();
    }

} // class
