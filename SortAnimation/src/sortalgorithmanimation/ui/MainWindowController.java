package sortalgorithmanimation.ui;

import sortalgorithmanimation.core.Elements;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import sortalgorithmanimation.sort.InsertionSort;
import sortalgorithmanimation.sort.QuickSort;

public class MainWindowController {

    @FXML
    Canvas bord;

    @FXML
    ComboBox<String> choiceElements, choiceAlgorithm;

    /**
     * GraphicsContext holder
     */
    private GraphicsContext graph;

    /**
     * Drzi selektovanu scenu
     */
    private Scene scene;

    /**
     * Znak da nam je animacija aktivna
     */
    private boolean animationActive;

    /**
     * Drzac elemenata
     */
    private Elements elementsGenerator;

    /**
     * Drzac za iscrtavanje
     */
    private Drawing drawing;

    /**
     * Setovanje scene u odnosu na to kom kontroleru pripada
     *
     * @param scene Scena koju koristimo u kontroleru
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Setup za layout. Setovacemo elemente za sortiranje kao i algoritam Posle
     * toga stampamo linije
     */
    public void setupLayout(Elements elements) {
        elementsGenerator = elements;

        /* Opcije za sortiranje */
        ObservableList<String> elementsOptions = FXCollections.observableArrayList(
                        "Random",
                        "Few unique",
                        "Reversed",
                        "Almost sorted"
                );
        choiceElements.setItems(elementsOptions);

        /* Algoritmi za sortiranje */
        ObservableList<String> algorithmOptions = FXCollections.observableArrayList(
                        "Quick sort",
                        "Insertion sort"
                );
        choiceAlgorithm.setItems(algorithmOptions);

        /* Setovanje iscrtavanja */
        drawing = new Drawing(bord);
    }

    /**
     * Startovanje animacije
     *
     * @param evt
     */
    public void startAnimation(ActionEvent evt) {
        try {

            if (animationActive) {
                throw new Exception("Animacija je u toku, morate sacekati da se završi");
            }

            /* Uzimanje elementa za sortiranje */
            int[] elements = getSelectedElements();

            /* Uzimanje algoritma za sortiranje */
            String algorithm = getSelectedAlgorithm();

            /* Pokretanje thread-a za animaciju sortiranja */
            Thread loop = new Thread(()
                    -> {
                try {
                    /* Setovanje animacije ako je aktivna */
                    animationActive = true;

                    switch (algorithm) {
                        case "Quick sort":
                            QuickSort quicksort = new QuickSort(drawing);
                            quicksort.doSort(elements, 0, elements.length - 1);
                            break;
                        case "Insertion sort":
                            InsertionSort insertionsort = new InsertionSort(drawing);
                            insertionsort.doSort(elements);
                            break;
                    }

                    /* Setovanje animacije kada se zavrsi sortiranje*/
                    animationActive = false;
                } catch (InterruptedException ie) {
                    animationActive = false;
                }
            });
            loop.start();

        } catch (Exception e) {
            new DisplayMessage(Alert.AlertType.ERROR, "Doslo je do greške", e.getMessage());
        }
    }

    /**
     * Preuzimanje selektovanih elemenata
     *
     * @return
     * @throws Exception
     */
    private int[] getSelectedElements() throws Exception {
        if (choiceElements.getSelectionModel().getSelectedItem() == null) {
            throw new Exception("Niste izabrali elemente za sortiranje");
        }

        String selected = choiceElements.getSelectionModel().getSelectedItem();
        int[] elements = new int[elementsGenerator.getNumOfElements()];

        switch (selected) {
            case "Random":
                elements = elementsGenerator.random();
                break;
            case "Few unique":
                elements = elementsGenerator.fewUnique();
                break;
            case "Reversed":
                elements = elementsGenerator.reversed();
                break;
            case "Almost sorted":
                elements = elementsGenerator.almostSorted();
                break;
        }

        return elements;
    }

    /**
     * Preuzimanje elektovanog algoritma
     *
     * @return
     * @throws Exception
     */
    private String getSelectedAlgorithm() throws Exception {
        if (choiceAlgorithm.getSelectionModel().getSelectedItem() == null) {
            throw new Exception("Niste izabrali algoritam za sortiranje");
        }
        String selected = choiceAlgorithm.getSelectionModel().getSelectedItem();
        return selected;
    }
}
