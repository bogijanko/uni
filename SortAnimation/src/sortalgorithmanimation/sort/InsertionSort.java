package sortalgorithmanimation.sort;

import sortalgorithmanimation.ui.Drawing;

/**
 * Insertion sort
 */
public class InsertionSort
{

    /**
     * Alat za iscrtavanje
     * Drawing alat
     */
    private Drawing drawing;

    /**
     * Setup za Insertion sort algoritam
     * @param drawing
     */
    public InsertionSort(Drawing drawing)
    {
        this.drawing = drawing;
    }

    /**
     * Sortiranje
     */
    public void doSort(int[] elements) throws InterruptedException
    {
        int j;

        this.drawing.draw(elements, 1, 0);
        Thread.sleep(this.drawing.SLEEP_TIME);

        for (int p = 1; p < elements.length; p++) {
            int tmp = elements[p];
            for (j = p; j > 0 && tmp < elements[j - 1]; j--) {
                elements[j] = elements[j - 1];
                this.drawing.draw(elements, j, j - 1);
                Thread.sleep(this.drawing.SLEEP_TIME);
            }
            elements[j] = tmp;
            this.drawing.draw(elements, j, 0);
            Thread.sleep(this.drawing.SLEEP_TIME);
        }
    }

}
