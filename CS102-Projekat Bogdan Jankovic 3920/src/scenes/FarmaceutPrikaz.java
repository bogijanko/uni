package scenes;

import crud.CrudFarmaceut;
import crud.CrudKlijent;
import crud.CrudLek;
import exceptions.BrojKnjiziceNijeValidanException;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pojo.Farmaceut;
import pojo.Klijent;
import pojo.Lek;
import util.MessageAlerts;

public class FarmaceutPrikaz {

    public static Scene getAdminView() {
        Button dodajLek = new Button("Dodaj lek");
        Button izmeniLek = new Button("Izmeni lek");
        Button izbrisiLek = new Button("Obrisi lek");
        Button dodajKlijenta = new Button("Dodaj klijenta");
        Button izmeniKlijenta = new Button("Izmeni klijenta");
        Button obrisiKlijenta = new Button("Obrisi klijenta");
        Button dodajFarmaceuta = new Button("Dodaj farmaceuta");
        Button izmeniFarmaceuta = new Button("Izmeni farmaceuta");
        Button obrisiFarmaceuta = new Button("Obrisi farmaceuta");
        Button logout = new Button("Izloguj se");
        
        Label label = new Label("Baza lekova: ");
        label.setTextFill(Color.GREEN);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        HBox labelHb = new HBox();
        labelHb.setAlignment(Pos.CENTER);
        labelHb.getChildren().add(label);
        labelHb.setSpacing(300);
        labelHb.getChildren().add(logout);
     
        logout.setStyle("-fx-background-color:red;");
        
        logout.setOnAction((ActionEvent e) -> {
            System.exit(0);
        });
        
        TableView<Lek> table = new TableView<>();
        ObservableList<Lek> lekovi = FXCollections.observableArrayList(CrudLek.readAll());

        TableColumn<Lek,String> idl = new TableColumn<>("ID");
        idl.setCellValueFactory(new PropertyValueFactory<Lek, String>("id"));
        TableColumn naziv = new TableColumn("Naziv");
        naziv.setCellValueFactory(new PropertyValueFactory<Lek, String>("naziv"));
        TableColumn proizvodjac = new TableColumn("Proizvodjac");
        proizvodjac.setCellValueFactory(new PropertyValueFactory<Lek, String>("proizvodjac"));
        TableColumn jacina = new TableColumn("Jacina");
        jacina.setCellValueFactory(new PropertyValueFactory<Lek, String>("jacina"));
        TableColumn cena = new TableColumn("Cena");
        cena.setCellValueFactory(new PropertyValueFactory<Lek, String>("cena"));
        TableColumn kolicina = new TableColumn("Kolicina");
        kolicina.setCellValueFactory(new PropertyValueFactory<Lek, String>("kolicina"));

        table.getColumns().addAll(idl,naziv,proizvodjac,jacina,cena,kolicina);
        table.getItems().addAll(lekovi);
        table.setPrefWidth(400);
        table.setPrefHeight(150);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        HBox hb = new HBox(20);
        hb.setAlignment(Pos.CENTER);
        hb.setStyle("-fx-background-color:lightgreen;");
        hb.getChildren().addAll(dodajLek, izmeniLek, izbrisiLek);
        
        //ADD LEK
        dodajLek.setOnAction(e -> {
            Stage stage = new Stage();
            Label txt = new Label("Dodaj Lek: ");
            TextField tfnaziv = new TextField();
            tfnaziv.setPromptText("Naziv leka");
            TextField tfproizvodjac = new TextField();
            tfproizvodjac.setPromptText("Proizvodjač");
            TextField tfjacina = new TextField();
            tfjacina.setPromptText("Jačina");
            TextField tfcena = new TextField();
            tfcena.setPromptText("Cena");
            TextField tfkolicina = new TextField();
            tfkolicina.setPromptText("Količina");
            Button dugme = new Button("Dodaj");
            dugme.setPrefWidth(150);

            dugme.setOnAction((ActionEvent x) -> {
            if (tfnaziv.getText().trim().isEmpty() || tfproizvodjac.getText().trim().isEmpty() || tfjacina.getText().trim().isEmpty() || tfcena.getText().trim().isEmpty() || tfkolicina.getText().trim().isEmpty()) {
                MessageAlerts.showErrorMessage("Popunite sva polja");
            } else {
                String nazivLeka = tfnaziv.getText().trim();
                String proizvodjacLeka = tfproizvodjac.getText().trim();
                Integer jacinaLeka = Integer.parseInt(tfjacina.getText().trim());
                Integer cenaLeka = Integer.parseInt(tfcena.getText().trim());
                Integer kolicinaLeka = Integer.parseInt(tfkolicina.getText().trim());
                
                Lek temp = new Lek(nazivLeka, proizvodjacLeka, jacinaLeka, cenaLeka, kolicinaLeka);
                
                CrudLek.create(temp);
                table.getItems().setAll(CrudLek.readAll());
                table.refresh();
                stage.close();
            }
            });
            GridPane root = new GridPane();
            root.setAlignment(Pos.TOP_CENTER);
            root.setVgap(10);
            root.setHgap(10);
            root.add(txt, 0, 0);
            root.add(tfnaziv, 0, 1);
            root.add(tfproizvodjac, 0, 2);
            root.add(tfjacina, 0, 3);
            root.add(tfcena, 0, 4);
            root.add(tfkolicina, 0, 5);
            root.add(dugme, 0, 6);

            Scene scene = new Scene(root, 200, 300);
            dugme.requestFocus();
            stage.setTitle("Dodaj Lek");
            stage.setScene(scene);
            stage.show();
        });
        //UPDATE LEK
        izmeniLek.setOnAction(e -> {
            if (table.getSelectionModel().getSelectedItem() == null) {
                MessageAlerts.showErrorMessage("Izaberite lek!");
            } else {
            Stage stage = new Stage();
            Label txt = new Label("Izmeni Lek: ");
            TextField tfnaziv = new TextField();
            tfnaziv.setPromptText("Novi naziv leka");
            TextField tfproizvodjac = new TextField();
            tfproizvodjac.setPromptText("Novi proizvodjač");
            TextField tfjacina = new TextField();
            tfjacina.setPromptText("Nova jačina");
            TextField tfcena = new TextField();
            tfcena.setPromptText("Nova cena");
            TextField tfkolicina = new TextField();
            tfkolicina.setPromptText("Nova količina");
            Button dugme = new Button("Izmeni");
            dugme.setPrefWidth(150);

            dugme.setOnAction((ActionEvent x) -> {
            if (tfnaziv.getText().trim().isEmpty() || tfproizvodjac.getText().trim().isEmpty() || tfjacina.getText().trim().isEmpty() || tfcena.getText().trim().isEmpty() || tfkolicina.getText().trim().isEmpty()) {
                MessageAlerts.showErrorMessage("Popunite sva polja");
            } else {
                String nazivLeka = tfnaziv.getText().trim();
                String proizvodjacLeka = tfproizvodjac.getText().trim();
                Integer jacinaLeka = Integer.parseInt(tfjacina.getText().trim());
                Integer cenaLeka = Integer.parseInt(tfcena.getText().trim());
                Integer kolicinaLeka = Integer.parseInt(tfkolicina.getText().trim());
                
                Lek temp = (Lek) table.getSelectionModel().getSelectedItem();
                Lek tempUpdate = new Lek(temp.getId(),nazivLeka, proizvodjacLeka, jacinaLeka, cenaLeka, kolicinaLeka);
                CrudLek.update(tempUpdate);
                table.getItems().setAll(CrudLek.readAll());
                table.refresh();
                stage.close();
            }
            });
            GridPane root = new GridPane();
            root.setAlignment(Pos.TOP_CENTER);
            root.setVgap(10);
            root.setHgap(10);
            root.add(txt, 0, 0);
            root.add(tfnaziv, 0, 1);
            root.add(tfproizvodjac, 0, 2);
            root.add(tfjacina, 0, 3);
            root.add(tfcena, 0, 4);
            root.add(tfkolicina, 0, 5);
            root.add(dugme, 0, 6);

            Scene scene = new Scene(root, 200, 300);
            dugme.requestFocus();
            stage.setTitle("Izmeni Lek");
            stage.setScene(scene);
            stage.show();
            }
        });
        //DELETE LEK
        izbrisiLek.setOnAction((ActionEvent e) -> {
            if (table.getSelectionModel().getSelectedItem() == null) {
                MessageAlerts.showErrorMessage("Izaberite lek!");
            } else {
                Lek temp = (Lek) table.getSelectionModel().getSelectedItem();
                CrudLek.delete(temp);
                table.getItems().setAll(CrudLek.readAll());
                table.refresh();
            }
        });

        Label label1 = new Label("Klijenti");
        label1.setTextFill(Color.BLACK);
        label1.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        HBox label1Hb2 = new HBox();
        label1Hb2.setAlignment(Pos.CENTER);
        label1Hb2.getChildren().add(label1);
        TableView tableKlijenti = new TableView<>();
        ObservableList<Klijent> klijenti= FXCollections.observableArrayList(CrudKlijent.readAll());
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

        HBox hb2 = new HBox(20);
        hb2.setAlignment(Pos.CENTER);
        hb2.setStyle("-fx-background-color:lightgreen;");
        hb2.getChildren().addAll(dodajKlijenta, izmeniKlijenta, obrisiKlijenta);
        
        //ADD KLIJENTA
        dodajKlijenta.setOnAction((ActionEvent e) -> {
            Stage stage = new Stage();
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
                stage.close();
            }
            });
            GridPane root = new GridPane();
            root.setAlignment(Pos.TOP_CENTER);
            root.setVgap(10);
            root.setHgap(10);
            root.add(txt, 0, 0);
            root.add(tfime, 0, 1);
            root.add(tfprezime, 0, 2);
            root.add(tfbrojKnjizice, 0, 3);
            root.add(dugme, 0, 4);

            Scene scene = new Scene(root, 200, 300);
            dugme.requestFocus();
            stage.setTitle("Dodaj Klijenta");
            stage.setScene(scene);
            stage.show();
        });
        //UPDATE KLIJENTA
        izmeniKlijenta.setOnAction(e -> {
            if (tableKlijenti.getSelectionModel().getSelectedItem() == null) {
                MessageAlerts.showErrorMessage("Izaberite klijenta!");
            } else {
            Stage stage = new Stage();
            Label txt = new Label("Izmeni Klijenta: ");
            TextField tfime = new TextField();
            tfime.setPromptText("Ime");
            TextField tfprezime = new TextField();
            tfprezime.setPromptText("Prezime");
            TextField tfbrojKnjizice = new TextField();
            tfbrojKnjizice.setPromptText("Broj zdravstvene knjižice");
            Button dugme = new Button("Izmeni");
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
                } catch (BrojKnjiziceNijeValidanException ex) {
                    MessageAlerts.showInfoMessage("Broj knjižice nije validan");
                }
                Klijent tempKlijent = (Klijent) tableKlijenti.getSelectionModel().getSelectedItem();
                Klijent tempUpdate = new Klijent(tempKlijent.getId(),imeKlijenta, prezimeKlijenta,brojKnjiziceKlijenta);
                CrudKlijent.update(tempUpdate);
                tableKlijenti.getItems().setAll(CrudKlijent.readAll());
                tableKlijenti.refresh();
                stage.close();
            }
            });
            GridPane root = new GridPane();
            root.setAlignment(Pos.TOP_CENTER);
            root.setVgap(10);
            root.setHgap(10);
            root.add(txt, 0, 0);
            root.add(tfime, 0, 1);
            root.add(tfprezime, 0, 2);
            root.add(tfbrojKnjizice, 0, 3);
            root.add(dugme, 0, 4);

            Scene scene = new Scene(root, 200, 300);
            dugme.requestFocus();
            stage.setTitle("Izmeni Klijenta");
            stage.setScene(scene);
            stage.show();
            }
        });
        //DELETE KLIJENTA
        obrisiKlijenta.setOnAction((ActionEvent e) -> {
            if (tableKlijenti.getSelectionModel().getSelectedItem() == null) {
                MessageAlerts.showErrorMessage("Izaberite klijenta!");
            } else {
                Klijent temp = (Klijent) tableKlijenti.getSelectionModel().getSelectedItem();
                CrudKlijent.delete(temp);
                tableKlijenti.getItems().setAll(CrudKlijent.readAll());
                tableKlijenti.refresh();
            }
        });

        Label label2 = new Label("Farmaceuti");
        label2.setTextFill(Color.DARKGREEN);
        label2.setFont(Font.font("Calibri", FontWeight.BOLD, 22));
        HBox label2Hb2 = new HBox();
        label2Hb2.setAlignment(Pos.CENTER);
        label2Hb2.getChildren().add(label2);

        TableView tableFarmaceuti = new TableView<>();
        ObservableList<Farmaceut> farmaceuti= FXCollections.observableArrayList(CrudFarmaceut.readAll());
        tableFarmaceuti.setItems(farmaceuti);
        
        TableColumn idf = new TableColumn("ID");
        idf.setCellValueFactory(new PropertyValueFactory<Farmaceut, String>("id"));
        TableColumn username = new TableColumn("Username");
        username.setCellValueFactory(new PropertyValueFactory<Farmaceut, String>("username"));
        TableColumn pass = new TableColumn("Password");
        pass.setCellValueFactory(new PropertyValueFactory<Farmaceut, String>("password"));
        
        tableFarmaceuti.getColumns().setAll(idf, username, pass);
        tableFarmaceuti.setPrefWidth(400);
        tableFarmaceuti.setPrefHeight(100);
        tableFarmaceuti.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        HBox hb3 = new HBox(20);
        hb3.setAlignment(Pos.CENTER);
        hb3.setStyle("-fx-background-color:lightgreen;");
        hb3.getChildren().addAll(dodajFarmaceuta, izmeniFarmaceuta, obrisiFarmaceuta);
        
        //ADD FARMACEUTA
        dodajFarmaceuta.setOnAction((ActionEvent e) -> {
            Stage stage = new Stage();
            Label txt = new Label("Dodaj Farmaceuta: ");
            TextField tfusername = new TextField();
            tfusername.setPromptText("Username");
            PasswordField tfpass = new PasswordField();
            tfpass.setPromptText("Password");
            Button dugme = new Button("Dodaj");
            dugme.setPrefWidth(150);

            dugme.setOnAction((ActionEvent x) -> {
            if (tfusername.getText().trim().isEmpty() || tfpass.getText().trim().isEmpty()) {
                MessageAlerts.showErrorMessage("Popunite sva polja");
            } else {
                String usernameFarmaceuta = tfusername.getText().trim();
                String passFarmaceuta = tfpass.getText().trim();
                
                Farmaceut temp = new Farmaceut(usernameFarmaceuta,passFarmaceuta);
                CrudFarmaceut.create(temp);
                tableFarmaceuti.getItems().setAll(CrudFarmaceut.readAll());
                tableFarmaceuti.refresh();
                stage.close();
            }
            });
            GridPane root = new GridPane();
            root.setAlignment(Pos.TOP_CENTER);
            root.setVgap(10);
            root.setHgap(10);
            root.add(txt, 0, 0);
            root.add(tfusername, 0, 1);
            root.add(tfpass, 0, 2);
            root.add(dugme, 0, 3);

            Scene scene = new Scene(root, 200, 200);
            dugme.requestFocus();
            stage.setTitle("Dodaj Farmaceuta");
            stage.setScene(scene);
            stage.show();
        });
        //UPDATE FARMACEUTA
        izmeniFarmaceuta.setOnAction(e -> {
            if (tableFarmaceuti.getSelectionModel().getSelectedItem() == null) {
                MessageAlerts.showErrorMessage("Izaberite farmaceuta!");
            } else {
            Stage stage = new Stage();
            Label txt = new Label("Izmeni Farmaceuta: ");
            TextField tfusername = new TextField();
            tfusername.setPromptText("Username");
            PasswordField tfpass = new PasswordField();
            tfpass.setPromptText("Password");
            Button dugme = new Button("Izmeni");
            dugme.setPrefWidth(150);

            dugme.setOnAction((ActionEvent x) -> {
            if (tfusername.getText().trim().isEmpty() || tfpass.getText().trim().isEmpty()) {
                MessageAlerts.showErrorMessage("Popunite sva polja");
            } else {
                String usernameFarmaceuta = tfusername.getText().trim();
                String passFarmaceuta = tfpass.getText().trim();
                
                Farmaceut tempFarmaceut = (Farmaceut) tableFarmaceuti.getSelectionModel().getSelectedItem();
                Farmaceut tempUpdate = new Farmaceut(tempFarmaceut.getId(), usernameFarmaceuta, passFarmaceuta);
                CrudFarmaceut.update(tempUpdate);
                tableFarmaceuti.getItems().setAll(CrudFarmaceut.readAll());
                tableFarmaceuti.refresh();
                stage.close();
            }
            });
            GridPane root = new GridPane();
            root.setAlignment(Pos.TOP_CENTER);
            root.setVgap(10);
            root.setHgap(10);
            root.add(txt, 0, 0);
            root.add(tfusername, 0, 1);
            root.add(tfpass, 0, 2);
            root.add(dugme, 0, 3);

            Scene scene = new Scene(root, 200, 200);
            dugme.requestFocus();
            stage.setTitle("Izmeni Farmaceuta");
            stage.setScene(scene);
            stage.show();
            }
        });
        //DELETE KLIJENTA
        obrisiFarmaceuta.setOnAction((ActionEvent e) -> {
            if (tableFarmaceuti.getSelectionModel().getSelectedItem() == null) {
                MessageAlerts.showErrorMessage("Izaberite Farmaceuta!");
            } else {
                Farmaceut temp = (Farmaceut) tableFarmaceuti.getSelectionModel().getSelectedItem();
                CrudFarmaceut.delete(temp);
                tableFarmaceuti.getItems().setAll(CrudFarmaceut.readAll());
                tableFarmaceuti.refresh();
            }
        });

        VBox vbox = new VBox(7);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(labelHb, table, hb, label1Hb2, tableKlijenti, hb2, label2Hb2, tableFarmaceuti, hb3);
        Scene scene = new Scene(vbox, 570, 750);
        return scene;
    }
}
