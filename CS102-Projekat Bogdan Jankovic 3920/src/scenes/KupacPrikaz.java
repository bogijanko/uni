package scenes;

import crud.CrudKlijent;
import crud.CrudLek;
import crud.CrudPorudzbina;
import crud.CrudStavkaPorudzbine;
import exceptions.BrojKnjiziceNijeValidanException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pojo.Klijent;
import pojo.Lek;
import pojo.Porudzbina;
import pojo.StavkaPorudzbine;
import util.MessageAlerts;

public class KupacPrikaz {

    public static Scene KupacView() {
        Button btn1 = new Button("Reset kupovine");
        Button btn2 = new Button("Kraj kupovine");

        HBox hb1 = new HBox(20);
        hb1.setAlignment(Pos.CENTER);
        hb1.setStyle("-fx-background-color:lightgreen;");
        hb1.getChildren().addAll(btn1, btn2);

        VBox vb1 = new VBox(10);
        Label l = new Label("Izaberite lekove: ");
        l.setUnderline(true);
        ListView lista = new ListView();
        ObservableList<Lek> lekovi = FXCollections.observableArrayList(CrudLek.readAll());
        lista.setItems(lekovi);
        lista.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Button btn3 = new Button("Izaberi");
        vb1.setAlignment(Pos.CENTER);
        vb1.getChildren().addAll(l, lista, btn3);
        vb1.setStyle("-fx-background-color:green;");

        VBox vb2 = new VBox(10);
        Label l2 = new Label("Izabrani lekovi: ");
        l2.setUnderline(true);
        TextArea ta = new TextArea();
        ta.setPrefColumnCount(20);
        ta.setPrefRowCount(10);
        ta.setEditable(false);
        vb2.setAlignment(Pos.CENTER);
        vb2.getChildren().addAll(l2, ta);
        vb2.setStyle("-fx-background-color:green;");

        VBox vb3 = new VBox(10);
        vb3.setPadding(new Insets(10, 10, 10, 10));
        Label l3 = new Label("Izabrani lek: ");
        l3.setUnderline(true);
        TextField izabrani = new TextField();
        izabrani.setEditable(false);
        Label l4 = new Label("Unesite količinu koju želite za izabrani lek: ");
        TextField kol = new TextField();
        Button btn4 = new Button("Potvrdi");
        vb3.getChildren().addAll(l3, izabrani, l4, kol, btn4);

        BorderPane root = new BorderPane();
        root.setBottom(hb1);
        root.setLeft(vb1);
        root.setRight(vb2);
        root.setCenter(vb3);

        ObservableList<StavkaPorudzbine> stavkePorudzbine = FXCollections.observableArrayList(CrudStavkaPorudzbine.readAll());
        ObservableList<Porudzbina> porudzbine = FXCollections.observableArrayList(CrudPorudzbina.readAll());
        List<Lek> izabraniLekovi = new ArrayList<>();
        List<StavkaPorudzbine> izabraneStavke = new ArrayList<>();

        btn1.setOnAction(e -> {
            izabrani.setText("");
            kol.setText("");
            ta.setText("");
            izabraniLekovi.clear();
            stavkePorudzbine.clear();
            izabraneStavke.clear();
        });

        btn2.setOnAction(e -> {
            for (Lek lek : izabraniLekovi) {
                Lek tempUpdate = new Lek(lek.getNaziv(), lek.getProizvodjac(), lek.getJacina(), lek.getCena(), lek.getKolicina());
                tempUpdate.setId(lek.getId());
                tempUpdate.setKolicina(lek.getKolicina() - Integer.parseInt(kol.getText()));
                CrudLek.update(tempUpdate);
            }
            Stage stage = new Stage();
            Button dodeli = new Button("Dodeli-štampaj račun");
            Label label1 = new Label("Klijenti");
            label1.setTextFill(Color.BLACK);
            label1.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
            HBox label1Hb2 = new HBox();
            label1Hb2.setAlignment(Pos.CENTER);
            label1Hb2.getChildren().add(label1);
            TableView tableKlijenti = new TableView<>();
            ObservableList<Klijent> klijenti = FXCollections.observableArrayList(CrudKlijent.readAll());
            tableKlijenti.setItems(klijenti);

            TableColumn idk = new TableColumn("ID");
            idk.setCellValueFactory(new PropertyValueFactory<Klijent, String>("id"));
            TableColumn ime = new TableColumn("Ime");
            ime.setCellValueFactory(new PropertyValueFactory<Klijent, String>("ime"));
            TableColumn prezime = new TableColumn("Prezime");
            prezime.setCellValueFactory(new PropertyValueFactory<Klijent, String>("prezime"));
            TableColumn brojKnjizice = new TableColumn("Broj knjižice");
            brojKnjizice.setCellValueFactory(new PropertyValueFactory<Klijent, String>("brojKnjizice"));

            tableKlijenti.getColumns().setAll(idk, ime, prezime, brojKnjizice);
            tableKlijenti.setPrefWidth(400);
            tableKlijenti.setPrefHeight(150);
            tableKlijenti.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            VBox vbTabela = new VBox(10, label1Hb2, tableKlijenti, dodeli);

            dodeli.setOnAction(event -> {
                if (tableKlijenti.getSelectionModel().getSelectedItem() == null) {
                    MessageAlerts.showErrorMessage("Izaberite klijenta!");
                } else {
                    Porudzbina p = new Porudzbina();
                    Klijent k = (Klijent) tableKlijenti.getSelectionModel().getSelectedItem();
                    p.setKlijentId(k);
                    for (StavkaPorudzbine i : izabraneStavke) {
                        p.setStavkaPorudzbineId(i);
                        i.setPorudzbinaList(porudzbine);
                    }
                    CrudPorudzbina.create(p);
                    
                    //RACUN
                    Stage racun = new Stage();
                    TextArea klijentArea = new TextArea();
                    klijentArea.setEditable(false);
                    klijentArea.appendText(p.getKlijentId().getIme() + " " + p.getKlijentId().getPrezime() + "\n");
                    klijentArea.appendText("Broj zdravstvene knjižice: " + "\n");
                    klijentArea.appendText(p.getKlijentId().getBrojKnjizice());
                    klijentArea.setPrefRowCount(4);

                    TextArea racunArea = new TextArea();
                    int ukupno = 0;
                    racunArea.setEditable(false);
                    for (StavkaPorudzbine i : izabraneStavke) {
                        racunArea.appendText(i.getIdLeka().getNaziv() + " količina " + i.getKolicina() + "\n");
                        racunArea.appendText(i.getKolicina() + " * " + i.getIdLeka().getCena() + "din" + " = " + i.getKolicina() * i.getIdLeka().getCena()+ "din" + "\n");
                        ukupno += i.getKolicina() * i.getIdLeka().getCena();
                    }
                    
                    new Thread(() -> {
                        try {
                            String link = "https://www.panadol.rs/informacije-o-bezbednom-koriscenju-leka.html";
                            Document document = Jsoup.connect(link).get();
                            String uputstvo = document.select("div.richText-content > p > span.disc-number").text();
                            Platform.runLater(() -> {;
                                MessageAlerts.showInfoMessage(uputstvo);
                            });
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }).start();

                    TextField ukupnoField = new TextField();
                    ukupnoField.setEditable(false);
                    ukupnoField.setText("UKUPNO: " + ukupno + "din");
                    Button end = new Button("Nazad");
                    
                    end.setOnAction(nazad -> {
                        racun.close();
                        stage.close();
                    });

                    VBox kraj = new VBox(10, klijentArea, racunArea, ukupnoField, end);
                    kraj.setAlignment(Pos.CENTER);
                    Scene scene = new Scene(kraj, 230, 390);
                    racun.setTitle("RAČUN");
                    racun.setScene(scene);
                    racun.show();
                }
            });
            Label txt = new Label("Dodaj Klijenta: ");
            TextField tfime = new TextField();
            tfime.setPromptText("Ime");
            TextField tfprezime = new TextField();
            tfprezime.setPromptText("Prezime");
            TextField tfbrojKnjizice = new TextField();
            tfbrojKnjizice.setPromptText("Broj zdravstvene knjižice");
            Button dugme = new Button("Dodaj");
            dugme.setPrefWidth(150);

            dugme.setOnAction((ActionEvent x) -> {
                if (tfime.getText().trim().isEmpty() || tfprezime.getText().trim().isEmpty() || tfbrojKnjizice.getText().trim().isEmpty()) {
                    MessageAlerts.showErrorMessage("Popunite sva polja");
                } else {
                    String imeKlijenta = tfime.getText().trim();
                    String prezimeKlijenta = tfprezime.getText().trim();
                    String brojKnjiziceKlijenta = tfbrojKnjizice.getText().trim();
                    Klijent temp;
                    try {
                        temp = new Klijent(imeKlijenta, prezimeKlijenta, brojKnjiziceKlijenta);
                        CrudKlijent.create(temp);
                    } catch (BrojKnjiziceNijeValidanException ex) {
                        MessageAlerts.showInfoMessage("Broj knjizice nije validan");
                    }
                    tableKlijenti.getItems().setAll(CrudKlijent.readAll());
                    tableKlijenti.refresh();
                }
            });
            GridPane add = new GridPane();
            add.setAlignment(Pos.TOP_CENTER);
            add.setVgap(10);
            add.setHgap(10);
            add.add(txt, 0, 0);
            add.add(tfime, 0, 1);
            add.add(tfprezime, 0, 2);
            add.add(tfbrojKnjizice, 0, 3);
            add.add(dugme, 0, 4);

            HBox hb = new HBox(20, vbTabela, add);
            hb.setPadding(new Insets(15, 15, 15, 15));
            Scene scene = new Scene(hb, 600, 280);
            dodeli.requestFocus();
            stage.setTitle("Porudžbina - Klijent");
            stage.setScene(scene);
            stage.show();
        });

        btn3.setOnAction(e -> {
            ObservableList<Lek> selectedItems = lista.getSelectionModel().getSelectedItems();
            izabrani.setText(selectedItems.get(0).toString());
            kol.setText("");
        });

        btn4.setOnAction(e -> {
            ObservableList<Lek> selectedItems = lista.getSelectionModel().getSelectedItems();
            try {
                if (Integer.parseInt(kol.getText()) <= 0 || izabrani.getText().equals("")) {
                }
                if (selectedItems.get(0).getKolicina() - Integer.parseInt(kol.getText()) < 0) {
                    MessageAlerts.showInfoMessage("Izabrani lek nemamo u traženoj količini");
                } else {
                    String str = "";
                    for (Lek lek : selectedItems) {
                        str += (lek + " količina: " + kol.getText() + "\n");
                        izabraniLekovi.add(lek);
                        StavkaPorudzbine sp = new StavkaPorudzbine();
                        sp.setIdLeka(lek);
                        sp.setKolicina(Integer.parseInt(kol.getText()));
                        izabraneStavke.add(sp);
                        CrudStavkaPorudzbine.create(sp);
                        ta.appendText(str);
                    }
                }
            } catch (NumberFormatException ex) {
                MessageAlerts.showErrorMessage("Unesite broj za količinu!");
            }
        });
        Scene scene = new Scene(root, 750, 300);
        return scene;
    }
}
