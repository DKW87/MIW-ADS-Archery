package nl.hva.ict.se.ads;

import java.util.List;

public class Launcher {

    private static long timeMs;

    public static void main(String[] args) {
        startTime();
        List<Archer> unsortedArcherList = Archer.generateArchers(1000);
        for (Archer archer : unsortedArcherList) {
            System.out.println(archer);
        }
        stopTime();

        System.out.println();
        System.out.println("------- sorteren op Id ---------");
        // TODO

        System.out.println();
        System.out.println("------- sorteren op achternaam ---------");
        // TODO

        System.out.println();
        System.out.println("------- sorteren op hoogste score (schema 1) ---------");
        // TODO

        System.out.println();
        System.out.println("------- sorteren op top rondes (schema 2)---------");
        // TODO


        System.out.println();
        System.out.println("------- efficiëntie van sorteer algoritmes ---------");
        // TODO
    }

    // methods
    private static void startTime() {
        timeMs = System.currentTimeMillis();
    }

    private static void stopTime() {
        System.out.printf("%d Ms", System.currentTimeMillis() - timeMs);
    }

}
