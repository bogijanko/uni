package sortalgorithmanimation.core;

import java.util.Random;

/**
 * Izgradnja elemenata koje ćemo koristiti za sortiranje
 */
public class Elements {

    /**
     * Broj elemenata za generisanje
     */
    private static final int NUM_OF_ELEMENTS = 20;

    /**
     * Unique elementi koje generišemo za soritiranje
     */
    private static final int NUM_OF_UNIQUE_ELEM = 5;

    /**
     * Getter za broj elemenata koje generišemo
     */
    public int getNumOfElements() {
        return NUM_OF_ELEMENTS;
    }

    /**
     * Generisanje slučajno odabranih elemenata
     *
     * @return
     */
    public static int[] random() {
        Random rand = new Random();
        int[] elements = new int[NUM_OF_ELEMENTS];
        for (int i = 0; i < NUM_OF_ELEMENTS; i++) {
            elements[i] = rand.nextInt((NUM_OF_ELEMENTS - 1) + 1) + 1;
        }
        return elements;
    }

    /**
     * Generisanje unique elemenata
     *
     * @return
     */
    public static int[] fewUnique() {
        Random rand = new Random();
        int[] elements = new int[NUM_OF_ELEMENTS];
        int[] unique = new int[NUM_OF_UNIQUE_ELEM];

        for (int i = 0; i < NUM_OF_UNIQUE_ELEM; i++) {
            unique[i] = rand.nextInt((NUM_OF_ELEMENTS - 1) + 1) + 1;
        }

        int uniqueKey = 1;
        for (int i = 0; i < NUM_OF_ELEMENTS; i++) {
            elements[i] = unique[uniqueKey - 1];

            if (i % NUM_OF_UNIQUE_ELEM == 0) {
                uniqueKey++;
            }
        }

        return elements;
    }

    /**
     * Generisanje elemenata po obrnutom redosledu
     *
     * @return
     */
    public static int[] reversed() {
        int[] elements = new int[NUM_OF_ELEMENTS];

        for (int i = 0; i < NUM_OF_ELEMENTS; i++) {
            elements[i] = NUM_OF_ELEMENTS - i;
        }

        return elements;
    }

    /**
     * Generisanje skoro sortiranih elemenata
     *
     * @return
     */
    public static int[] almostSorted() {
        Random rand = new Random();
        int[] elements = new int[NUM_OF_ELEMENTS];

        for (int i = 0; i < NUM_OF_ELEMENTS; i++) {
            if (i % 5 == 0) {
                elements[i] = rand.nextInt((NUM_OF_ELEMENTS - 1) + 1) + 1;
            } else {
                elements[i] = i;
            }
        }
        return elements;
    }

}
