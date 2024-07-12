package nl.hva.ict.se.ads;

import comparator.*;


import java.util.Comparator;
import java.util.List;

public class Launcher {

    private static long timeMs;

    public static void main(String[] args) {
        startTime();
        List<Archer> unsortedArcherList = Archer.generateArchers(10);
        for (Archer archer : unsortedArcherList) {
            System.out.println(archer);
        }
        stopTime();
        System.out.println();

        // switched id and last name around since id is already sorted correctly after generating
        System.out.println("------- sorteren op achternaam ---------");
        // TODO change to own sorting method :) Currently java collection depended
        printAndTimeSortedList(unsortedArcherList, new LastNameComparator(), false, true);

        System.out.println("------- sorteren op Id ---------");
        // TODO change to own sorting method :) Currently java collection depended
        printAndTimeSortedList(unsortedArcherList, new IdComparator(), false, true);

        System.out.println("------- sorteren op hoogste score (schema 1) ---------");
        // TODO change to own sorting method :) Currently java collection depended
        printAndTimeSortedList(unsortedArcherList, new Schema1Comparator(), true, true);

        System.out.println("------- sorteren op top rondes (schema 2)---------");
        // TODO change to own sorting method :) Currently java collection depended
        printAndTimeSortedList(unsortedArcherList, new Schema2Comparator(), true, true);

        System.out.println("------- efficiëntie van sorteer algoritmes ---------");
        // TODO

    } // main

    // methods
    private static void printAndTimeSortedList(List<Archer> archerList, Comparator<Archer> comparator, boolean descending, boolean useJavaCollections) {
        startTime();
        if (useJavaCollections) ChampionSelector.collectionSort(archerList, comparator, descending);
        else ChampionSelector.basicSort(archerList, comparator, descending);
        for (Archer archer : archerList) System.out.println(archer);
        stopTime();
        System.out.println(); // empty line for formatting
    }

    private static void startTime() {
        timeMs = System.currentTimeMillis();
    }

    private static void stopTime() {
        System.out.printf("%d Ms", System.currentTimeMillis() - timeMs);
    }

} // class
