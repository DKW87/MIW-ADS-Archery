package nl.hva.ict.se.ads;

import comparator.*;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Danny KWANT
 * @studentnumber 500955184
 *
 * This assignment is about comparing and sorting in different ways.
 * I created a program used in an archery competition.
 * Each archer shoots 3 arrows per round, and the competition lasts 10 rounds.
 * Various scores are possible.
 * For each arrow, the score must be recorded, along with the round in which it was shot.
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

        // variables
        final String[] sortingLabels = new String[] {"unsortedList", "sortLastName", "sortId", "sortSchema1",
                "sortSchema2", "collectionSort", "basicSort"};
        long[] processingTimeSorting = new long[7];

        startTime();
        List<Archer> unsortedArcherList = Archer.generateArchers(2000);
        for (Archer archer : unsortedArcherList) {
            System.out.println(archer);
        }
        stopTime();
        processingTimeSorting[0] = timeMs;

        Collections.shuffle(unsortedArcherList);

        System.out.println();

        // switched id and last name around since id is already sorted correctly after generating
        System.out.println("------- bezig met sorteren op achternaam ---------");
        printAndTimeSortedList(unsortedArcherList, new LastNameComparator(), false,
                false, false);
        processingTimeSorting[1] = timeMs;

        Collections.shuffle(unsortedArcherList);

        System.out.println("------- bezig met sorteren op Id ---------");
        printAndTimeSortedList(unsortedArcherList, new IdComparator(), false,
                false, true);
        processingTimeSorting[2] = timeMs;

        Collections.shuffle(unsortedArcherList);

        System.out.println("------- bezig met sorteren op hoogste score (schema 1) ---------");
        printAndTimeSortedList(unsortedArcherList, new Schema1Comparator(), true,
                false, false);
        processingTimeSorting[3] = timeMs;

        Collections.shuffle(unsortedArcherList);

        System.out.println("------- bezig met sorteren op top rondes (schema 2)---------");
        printAndTimeSortedList(unsortedArcherList, new Schema2Comparator(), true,
                false, true);
        processingTimeSorting[4] = timeMs;

        Collections.shuffle(unsortedArcherList);

        // make a copy of Schema2 sorted list to sort back to Schema1 for efficiency testing
        List<Archer> unsortedArcherListCopy = unsortedArcherList;

        System.out.println("------- controleren efficiëntie van sorteer algoritmes ---------");

        System.out.println("\n------- bezig met Java collectionSort ---------");
        printAndTimeSortedList(unsortedArcherListCopy, new Schema1Comparator(), false,
                true, false);
        processingTimeSorting[5] = timeMs;

        System.out.println("------- bezig met eigen basicSort (insertion/selection) ---------");
        printAndTimeSortedList(unsortedArcherList, new Schema1Comparator(), false,
                false, false);
        processingTimeSorting[6] = timeMs;

        // show results
        System.out.printf("%-15s %15s %n", "Sorteermethode", "Verwerkingstijd");
        for (int i = 0; i < processingTimeSorting.length; i++) {
            System.out.printf("%-20s %10s %n", sortingLabels[i], processingTimeSorting[i] + " Ms");
        }

    } // main

    // methods
    private static void printAndTimeSortedList(List<Archer> archerList, Comparator<Archer> comparator, boolean descending, boolean useJavaCollections, boolean useInsertionSort) {
        startTime(); // start tracking processing time
        if (useJavaCollections) ChampionSelector.collectionSort(archerList, comparator, descending);
        else ChampionSelector.basicSort(archerList, comparator, descending, useInsertionSort);
        for (Archer archer : archerList) System.out.println(archer);
        stopTime(); // end tracking and report processing time to user
        System.out.println(); // empty line for formatting
    }

    private static void startTime() {
        timeMs = System.currentTimeMillis();
    }

    private static void stopTime() {
        timeMs = System.currentTimeMillis() - timeMs;
        System.out.printf("%nVerwerkingstijd voor deze actie was: %d Ms %n", timeMs);
    }

} // class
