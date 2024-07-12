package nl.hva.ict.se.ads;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Given a list of Archer's this class can be used to sort the list using one of two sorting algorithms.
 */
public class ChampionSelector {
    /**
     * This method uses either selection sort or insertion sort for sorting the archers.
     */
    public static List<Archer> basicSort(List<Archer> archers, Comparator<Archer> scoringScheme, boolean descending) {
        // TODO implement a basic sort method of your choice
        return archers;
    }


    /**
     * This method uses the Java collections sort algorithm for sorting the archers.
     * @param descending reverses the comparators allowing for list to be returned sorted in descending order
     */
    public static List<Archer> collectionSort(List<Archer> archers, Comparator<Archer> scoringScheme, boolean descending) {
        if (descending) archers.sort(scoringScheme.reversed());
        else archers.sort(scoringScheme);
        return archers;
    }

}
