package sortalgorithmanimation.sort;

import sortalgorithmanimation.ui.Drawing;

/**
 * Quick sort algorithm
 */
public class QuickSort
{

    /**
     * Alat za iscrtavanje
     */
    private Drawing drawing;

    /**
     * Setup za Quick sort algoritam
     * @param drawing
     */
    public QuickSort(Drawing drawing)
    {
        this.drawing = drawing;
    }

    /**
     * Sortiranje
     */
    public void doSort(int[] elements, int left, int right) throws InterruptedException
    {
        int i = left;
        int j = right;
        int index = left + (right - left) / 2;
        int comparator = 0;
        int pivot = elements[index];

        this.drawing.draw(elements, index, comparator);
        Thread.sleep(drawing.SLEEP_TIME);

        while (i <= j) {
            while (elements[i] < pivot) {
                i++;
                index = i;
                this.drawing.draw(elements, index, comparator);
                Thread.sleep(drawing.SLEEP_TIME);
            }
            while (elements[j] > pivot) {
                j--;
                comparator = j;
                this.drawing.draw(elements, index, comparator);
                Thread.sleep(drawing.SLEEP_TIME);
            }

            if (i <= j) {
                int temp = elements[i];
                elements[i] = elements[j];
                elements[j] = temp;

                if(i == index) {
                    index = j;
                } else if (j == index) {
                    index = i;
                }

                this.drawing.draw(elements, index, comparator);
                Thread.sleep(drawing.SLEEP_TIME);
                i++;
                j--;
            }
        }

        if (left < j) {
            doSort(elements, left, j);
        }

        if (i < right) {
            doSort(elements, i, right);
        }

        this.drawing.draw(elements, index, comparator);
        Thread.sleep(drawing.SLEEP_TIME);
    }
}
