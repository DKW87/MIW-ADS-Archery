package comparator;

import nl.hva.ict.se.ads.Archer;

import java.util.Comparator;

/**
 * @author Danny KWANT
 * @project archery
 * @created 12/07/2024 - 13:20
 */
public class Schema1Comparator implements Comparator<Archer> {

    /**
     * Used to compare totalscores between archers.
     * If both archers have the same totalscore, compares weightedscores.
     * If both weighted scores are the same, archer id is compared.
     * @param archer the first object to be compared.
     * @param anotherArcher the second object to be compared.
     * @return
     */
    @Override
    public int compare(Archer archer, Archer anotherArcher) {
        int compareTotalScore = Integer.compare(archer.getTotalScore(), anotherArcher.getTotalScore());

        // instead of nested ifs/else or 2 ternary statements, I used one of each to keep it readable and compact
        if (compareTotalScore == 0 ) {
            int compareWeightedScore = Integer.compare(archer.getWeightedScore(), anotherArcher.getWeightedScore());
            return compareWeightedScore == 0 ? anotherArcher.compareTo(archer) : compareWeightedScore;
        }
        return compareTotalScore;
    }

} // class
