package comparator;

import nl.hva.ict.se.ads.Archer;

import java.util.Comparator;

/**
 * @author Danny KWANT
 * @project archery
 * @created 12/07/2024 - 13:20
 */
public class LastNameComparator implements Comparator<Archer> {

    /**
     * Compares last name of archers.
     * @param archer the first object to be compared.
     * @param anotherArcher the second object to be compared.
     * @return
     */
    @Override
    public int compare(Archer archer, Archer anotherArcher) {
        return archer.getLastName().compareTo(anotherArcher.getLastName());
    }

}
