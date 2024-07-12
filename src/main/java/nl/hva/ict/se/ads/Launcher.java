package nl.hva.ict.se.ads;

import comparator.IdComparator;
import comparator.LastNameComparator;
import comparator.Schema1Comparator;

import java.util.List;

public class Launcher {

    private static long timeMs;

    public static void main(String[] args) {
        startTime();
        List<Archer> unsortedArcherList = Archer.generateArchers(20);
        for (Archer archer : unsortedArcherList) {
            System.out.println(archer);
        }
        stopTime();

        // switched id and last name around since id is already sorted correctly after generating

        System.out.println();
        System.out.println("------- sorteren op achternaam ---------");
        // TODO change to own sorting method :) Currently java collection depended
        ChampionSelector.collectionSort(unsortedArcherList, new LastNameComparator(), false);
        for (Archer archer : unsortedArcherList) {
            System.out.println(archer);
        }

        System.out.println();
        System.out.println("------- sorteren op Id ---------");
        // TODO change to own sorting method :) Currently java collection depended
        ChampionSelector.collectionSort(unsortedArcherList, new IdComparator(), false);
        for (Archer archer : unsortedArcherList) {
            System.out.println(archer);
        }

        System.out.println();
        System.out.println("------- sorteren op hoogste score (schema 1) ---------");
        // TODO change to own sorting method :) Currently java collection depended
        ChampionSelector.collectionSort(unsortedArcherList, new Schema1Comparator(), true);
        for (Archer archer : unsortedArcherList) {
            System.out.println(archer);
        }
        System.out.println();
        System.out.println("------- sorteren op top rondes (schema 2)---------");
        // TODO


        System.out.println();
        System.out.println("------- efficiëntie van sorteer algoritmes ---------");
        // TODO

    } // main

    // methods
    private static void startTime() {
        timeMs = System.currentTimeMillis();
    }

    private static void stopTime() {
        System.out.printf("%d ms", System.currentTimeMillis() - timeMs);
    }

} // class
