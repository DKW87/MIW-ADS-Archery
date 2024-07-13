package nl.hva.ict.se.ads;

import java.util.*;

/**
 * Holds the name, archer-id and the points scored for 30 arrows.
 *
 * Archers MUST be created by using one of the generator methods. That is way the constructor is private and should stay
 * private. You are also not allowed to add any constructor with an access modifier other then private unless it is for
 * testing purposes in which case the reason why you need that constructor must be contained in a very clear manner
 * in your report.
 */
public class Archer implements Comparable<Archer> {

    public static int MAX_ARROWS = 3;
    public static int MAX_ROUNDS = 10;
    private static final int[] WEIGHTED_POINTS = {-10, 1, 2, 3, 4, 6, 8, 10, 13, 16, 20};
    // above weighted points are from low to high so that points earned (0-10) directly match with index of this array
    private static final int MAX_POINTS_VALUE = 10; // maximum value of points that can be scored with 1 arrow
    private static Random randomizer = new Random();
    private final int id; // Once assigned a value is not allowed to change.
    private String firstName;
    private String lastName;
    private static int lastId = 100000;
    private int[][] totalScoreArray = new int[MAX_ROUNDS][MAX_ARROWS];
    private int[][] weightedScoreArray = new int[MAX_ROUNDS][MAX_ARROWS];
    private int amountOfMaxPointsScored = 0; // holds how often a MAX_POINT_VALUE was hit by an arrow
    private boolean[] topRoundArray = new boolean[MAX_ROUNDS]; // a top round is if MAX_POINT_VALUE (or MPV - 1, MPV - 2) was hit

    /**
     * Constructs a new instance of bowman and assigns a unique ID to the instance. The ID is not allowed to ever
     * change during the lifetime of the instance! For this you need to use the correct Java keyword.Each new instance
     * is a assigned a number that is 1 higher than the last one assigned. The first instance created should have
     * ID 100000;
     *
     * @param firstName the archers first name.
     * @param lastName the archers surname.
     */
    private Archer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = lastId++;
    }

    /**
     * Constructs a 'fake' archer to be used in unit tests.
     */
    public Archer() {
        this.id = 100;
        firstName = "John";
        lastName = "Smith";
    }

    /**
     * Registers the point for each of the three arrows that have been shot during a round. The <code>points</code>
     * parameter should hold the three points of the round, one per arrow.
     *
     * @param round the round for which to register the points.
     * @param points the points shot during the round.
     */
    public void registerScoreForRound(int round, int[] points) {
        for (int i = 0; i < MAX_ARROWS; i++) {
            this.totalScoreArray[round][i] = points[i];
            this.weightedScoreArray[round][i] = WEIGHTED_POINTS[points[i]];
            setTopRound(round, points[i]);
            setAmountOfMaxPointsScored(points[i]);
        }
    }

    /**
     * @return The total score of an Archer, so the sum of all arrows of all rounds
     */
    public int getTotalScore() {
        return calculateTotalScore(totalScoreArray);
    }

    /**
     * @return The weighted score, see documentation.
     */
    public int getWeightedScore() {
        return calculateTotalScore(weightedScoreArray);
    }

    /**
     * Goes through the provided scoreArray to sum up all the scored points
     * @param scoreArray both weighted and regular scoreArrays can be processed
     * @return returns the sum of points scored
     */
    public int calculateTotalScore(int[][] scoreArray) {
        int score = 0;

        for (int i = 0; i < scoreArray.length; i++) {
            for (int j = 0; j < scoreArray[i].length; j++) {
                score += scoreArray[i][j];
            }
        }
        return score;
    }

    // this was asked for in the documentation, but is not being used? Left it in because it was asked for.
    public int calculateRoundScore(int round, int[][] scoreArray) {
        int score = 0;
        for (int i = 0; i < MAX_ARROWS; i++) score += scoreArray[round][i];
        return score;
    }

    /**
     * Checks if the points scored by an arrow are of maximum value or up to 2 points below.
     * Will be set to true if this is the case for the round the arrow was shot in
     * @param round the round the arrow was shot in
     * @param points the points scored by the archer
     */
    public void setTopRound(int round, int points) {
        if (points == MAX_POINTS_VALUE || points == MAX_POINTS_VALUE - 1 || points == MAX_POINTS_VALUE - 2) {
            topRoundArray[round] = true;
        }
    }

    /**
     * Returns amount of top rounds an archer has.
     * A top round is when an 8, 9 or 10 pointer was scored in a round.
     * @return
     */
    public int getTopRounds() {
        int sumTopRounds = 0;
        for (int i = 0; i < MAX_ROUNDS; i++) {
            if (topRoundArray[i]) sumTopRounds++;
        }
        return sumTopRounds;
    }

    /**
     * Checks if an arrow hit the maximum value target and increases the variable by one if true
     * @param points
     */
    public void setAmountOfMaxPointsScored(int points) {
        if (points == MAX_POINTS_VALUE) amountOfMaxPointsScored++;
    }

    /**
     * This methods creates a List of archers.
     *
     * @param nrOfArchers the number of archers in the list.
     * @return
     */
    public static List<Archer> generateArchers(int nrOfArchers) {
        List<Archer> archers = new ArrayList<>(nrOfArchers);
        for (int i = 0; i < nrOfArchers; i++) {
            Archer archer = new Archer(Names.nextFirstName(), Names.nextSurname());
            letArcherShoot(archer, i % 100 == 0);
            archers.add(archer);
        }
        return archers;

    }

    private static void letArcherShoot(Archer archer, boolean isBeginner) {
        for (int round = 0; round < MAX_ROUNDS; round++) {
            archer.registerScoreForRound(round, shootArrows(isBeginner ? 0 : 1));
        }
    }

    private static int[] shootArrows(int min) {
        int[] points = new int[MAX_ARROWS];
        for (int arrow = 0; arrow < MAX_ARROWS; arrow++) {
            points[arrow] = shoot(min);
        }
        return points;
    }

    private static int shoot(int min) {
        return Math.max(min, randomizer.nextInt(11));
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAmountOfMaxPointsScored() {
        return amountOfMaxPointsScored;
    }

    @Override
    public String toString() {
        return String.format("%d (%d / %d) %s %s", id, getTotalScore(), getWeightedScore(), firstName, lastName);
    }

    @Override
    public int compareTo(Archer anotherArcher) {
        return this.id - anotherArcher.id;
    }

} // class
