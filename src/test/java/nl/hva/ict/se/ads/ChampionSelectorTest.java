package nl.hva.ict.se.ads;

import comparator.IdComparator;
import comparator.LastNameComparator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChampionSelectorTest {
    protected Comparator<Archer> comparator;

    @BeforeEach
    public void createComparator() {
        comparator = new IdComparator();
    }

    @Test
    public void basicSortAndCollectionSortResultInSameOrder() {
        List<Archer> unsortedArchersForBasicSort = Archer.generateArchers(23);
        List<Archer> unsortedArchersForCollectionSort = new ArrayList<>(unsortedArchersForBasicSort);

        List<Archer> sortedArchersBasicSort = ChampionSelector.basicSort(unsortedArchersForBasicSort, comparator, false);
        List<Archer> sortedArchersCollectionSort = ChampionSelector.collectionSort(unsortedArchersForCollectionSort, comparator, false);

        assertEquals(sortedArchersCollectionSort, sortedArchersBasicSort);
    }

}