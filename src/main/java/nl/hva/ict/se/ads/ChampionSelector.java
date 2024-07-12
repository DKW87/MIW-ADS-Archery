package nl.hva.ict.se.ads;

import java.util.Comparator;
import java.util.List;

/**
 * Given a list of Archer's this class can be used to sort the list using one of two sorting algorithms.
 */
public class ChampionSelector {
    /**
     * This method uses either selection sort or insertion sort for sorting the archers.
     * @param descending reverses the comparators allowing for list to be returned sorted in descending order
     * @param useInsertionSort sets insertionSort on true or use selectionSort when false
     */
    public static List<Archer> basicSort(List<Archer> archers, Comparator<Archer> scoringScheme, boolean descending, boolean useInsertionSort) {
        if (useInsertionSort) insertionSort(archers, scoringScheme, descending);
        else selectionSort(archers, scoringScheme, descending);
        return archers;
    }

    public static List<Archer> insertionSort(List<Archer> archers, Comparator<Archer> scoringScheme, boolean descending) {
        if (descending) scoringScheme = scoringScheme.reversed();

        for (int i = 1; i < archers.size(); i++) {
            Archer currentElement = archers.get(i);
            int j = i - 1;

            while (j >= 0 && scoringScheme.compare(archers.get(j), currentElement) > 0) {
                archers.set(j + 1, archers.get(j));
                j--;
            }
            archers.set(j + 1, currentElement);
        }
        return archers;
    }

    public static List<Archer> selectionSort(List<Archer> archers, Comparator<Archer> scoringScheme, boolean descending) {
        if (descending) scoringScheme = scoringScheme.reversed();

        for (int i = 0; i < archers.size() - 1; i++) {
            int selectedIndex = i;

            for (int j = i + 1; j < archers.size(); j++) {
                if (scoringScheme.compare(archers.get(j), archers.get(selectedIndex)) < 0) {
                    selectedIndex = j;
                }
            }
            Archer temp = archers.get(selectedIndex);
            archers.set(selectedIndex, archers.get(i));
            archers.set(i, temp);
        }
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
