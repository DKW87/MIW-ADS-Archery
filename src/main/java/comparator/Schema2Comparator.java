package comparator;

import nl.hva.ict.se.ads.Archer;

import java.util.Comparator;

/**
 * @author Danny KWANT
 * @project archery
 * @created 12/07/2024 - 13:20
 */
public class Schema2Comparator implements Comparator<Archer> {

    @Override
    public int compare(Archer archer, Archer anotherArcher) {
        int compareTopRounds = Integer.compare(archer.getTopRounds(), anotherArcher.getTopRounds());

        // instead of nested ifs/else or 2 ternary statements, I used one of each to keep it readable and compact
        if (compareTopRounds == 0) {
            int compareAmountOfMaxPointsScored = Integer.compare(archer.getAmountOfMaxPointsScored(),
                    anotherArcher.getAmountOfMaxPointsScored());
            return compareAmountOfMaxPointsScored == 0 ? anotherArcher.compareTo(archer) : compareAmountOfMaxPointsScored;
        }
        return compareTopRounds;
    }

} // class
