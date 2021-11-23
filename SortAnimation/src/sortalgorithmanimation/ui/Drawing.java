package sortalgorithmanimation.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Klasa za iscrtavanje algoritma
 */
public class Drawing
{
    /**
     * Vreme izmedju iscrtavanja elementa
     */
    public static final int SLEEP_TIME = 100;

    /**
     * Razmak izmedju linija kojih se iscrtavaju
     */
    private static final int SPACE = 30;

    /**
     * Veličina linije
     */
    private static final int LINE_WIDTH = 15;

    /**
     * @param LINE_HEIGHT_MULTIPLIER - promenljiva za visinu koja se povecava
     */
    private static final int LINE_HEIGHT_MULTIPLIER = 15;

    /**
     * Tabla za crtanje
     */
    private Canvas bord;

    /**
     * GraphicsContext 
     */
    private GraphicsContext graph;

    /**
     * Instanca za crtanje
     * @param bord
     */
    public Drawing(Canvas bord)
    {
        this.bord = bord;
        this.graph = this.bord.getGraphicsContext2D();
    }

    /**
     * Iscrtavanje elemenata, moramo da napravimo pauzu u aplikaciji da bi videli promene
     * @param elements
     * @param index
     */
    public void draw(int[] elements, int index, int comparator){
        this.graph.clearRect(0, 0, this.bord.getWidth(), this.bord.getHeight());
        this.graph.setLineWidth(LINE_WIDTH);

        int x = SPACE;

        for (int i = 0; i < elements.length; i++) {
            if (index == i) {
                this.graph.setStroke(Color.BLUE);
            } else if (comparator == i) {
                this.graph.setStroke(Color.BLACK);
            } else {
                this.graph.setStroke(Color.CORAL);
            }
            
            this.graph.strokeLine(x, this.bord.getHeight(), x, this.bord.getHeight() - (elements[i] * LINE_HEIGHT_MULTIPLIER));
            x += SPACE;
        }
    }
}
