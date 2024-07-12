package nl.hva.ict.se.ads;

import comparator.*;


import java.util.Comparator;
import java.util.List;

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
        startTime();
        if (useJavaCollections) ChampionSelector.collectionSort(archerList, comparator, descending);
        else ChampionSelector.basicSort(archerList, comparator, descending, useInsertionSort);
        for (Archer archer : archerList) System.out.println(archer);
        stopTime();
        pressEnterToContinue();
        System.out.println(); // empty line for formatting
    }

    private static void startTime() {
        timeMs = System.currentTimeMillis();
    }

    private static void stopTime() {
        System.out.printf("%nVerwerkingstijd voor deze actie was: %d Ms %n", System.currentTimeMillis() - timeMs);
    }

    private static void pressEnterToContinue() {
        System.out.println("\nDruk op enter om verder te gaan...\n");
        try {
            System.in.read();
        } catch (Exception error) {
            error.printStackTrace();
        }
    }

} // class
