package comparator;

import nl.hva.ict.se.ads.Archer;

import java.util.Comparator;

/**
 * @author Danny KWANT
 * @project archery
 * @created 12/07/2024 - 13:20
 */
public class IdComparator implements Comparator<Archer> {

    @Override
    public int compare(Archer archer, Archer anotherArcher) {
        return Integer.compare(archer.getId(), anotherArcher.getId());
    }

} // class
