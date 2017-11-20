package Presentation;

import Business.Ansat;
import Database.MySqlConnection;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//lavet af alle
/**
 * Created by Ejer on 22-05-2017.
 */
public class AnsatPane {

    // opretter textfields
    TextField id = new TextField("ID nr.");
    TextField fornavn = new TextField("Fornavn");
    TextField efternavn = new TextField("Efternavn");
    TextField adresse = new TextField("Adresse");
    TextField by = new TextField("By");
    TextField postnummer = new TextField("Postnummer");
    TextField tlf = new TextField("Tlf.nr.");
    TextField kodeord = new TextField("Kodeord");

    //opretter knapper
    Button tilfoj = new Button("Tilføj");
    Button slet = new Button("Slet");
    Button tilbage = new Button("Tilbage");
    Button logud = new Button("Log ud");


    Label label = new Label("Her kan du oprette en ny ansat eller slette en");

    TableView table = new TableView();

    //hbox til tilbage og log ud knapperne
    HBox nederstHbox = new HBox();

    //Borderpane til vbox fra tabel og til hbox ffra textfields
    BorderPane borderPane = new BorderPane();

    //vbox tilknapper og textfields
    VBox vBoxKnapText = new VBox();

    //hbox til knapper
    HBox hBoxknapper = new HBox();

    //vbox der har textfields
    VBox vboxTextFields1 = new VBox();

    //vbox der indeholder textfields
    VBox vBoxTextFields2 = new VBox();

    //hbox til vboxs som indeholder textfields
    HBox hBox = new HBox();

    //vbox til label og tableview
    VBox vbox = new VBox();


    public AnsatPane() {
    }

    public BorderPane getAnsatPane(Stage primaryStage, Scene scene, ObservableList listHistorik, HovedMenuPane hovedMenuPane){
        //Opretter table
        indstillertableview();
        opbygningAfPane();
        indsætTekstIfields();
        ansatPaneKnapper(scene,primaryStage,listHistorik,hovedMenuPane);
      return borderPane;
    }
    //metode til at lave setOnAction på knapper i AnsatPane
    private void ansatPaneKnapper(Scene scene, Stage primaryStage, ObservableList<Ansat> list, HovedMenuPane hovedMenuPane) {
        getTilbage().setOnAction(event -> {
            scene.setRoot(hovedMenuPane.getHovedMenuPane());
            primaryStage.setScene(scene);
        });
        getTilfoj().setOnAction(event -> {
            opretAnsat();
            indsætDataiAnsatTabel(list);
            indsætTekstIfields();
        });

        getSlet().setOnAction(event -> {
            Ansat ansat;
            ansat = (Ansat) getTable().getSelectionModel().getSelectedItem();
            ansat.sletAnsat(ansat.getId());
            //int i = ansat.getId();
            int listSletIndex = getTable().getSelectionModel().getSelectedIndex();
            list.remove(listSletIndex);
        });

        getLogud().setOnAction(event -> {
            System.exit(1);
        });
    }

    //opdatere ObservableList<Ansat> list ved at kalde metoden indsætTilAnsatList(list),
    //og derefter adder ObservableList<Ansat> list til tabellen
    public void indsætDataiAnsatTabel(ObservableList<Ansat> list){
        //fjerner alt fra listen så der ikke bliver sat doublikanter ind i listen
        list.remove(0,list.size());
        indsætTilAnsatList(list);

        getTable().setItems(list);
    }

    //indsætter ansat til ObservableList<Ansat> list så det kan vises på tableview
    //lavet af casper indsætTilAnsatList
    private void indsætTilAnsatList(ObservableList<Ansat> list) {

        MySqlConnection connection = new MySqlConnection();

        String query = "SELECT * FROM table_Ansat";
        try {
            Statement st = connection.connect().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                int ansatId = rs.getInt(1);
                int ansatLoginId = rs.getInt(2);
                String ansatKode = rs.getString(3);
                String ansatFornavn = rs.getString(4);
                String ansatEfternavn = rs.getString(5);
                String ansatAdresse = rs.getString(6);
                String ansatBy = rs.getString(7);
                int ansatPostnummer = rs.getInt(8);
                int ansatTelefonnummer = rs.getInt(9);

                Ansat ansat = new Ansat(ansatId,ansatFornavn, ansatEfternavn, ansatAdresse, ansatBy,
                        ansatPostnummer, ansatTelefonnummer, ansatLoginId, ansatKode);
                list.add(ansat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //metode til at oprette ansat
    //lavet af casper
    private void opretAnsat(){
        String fornavn = getFornavn().getText();
        String efternavn = getEfternavn().getText();
        String adresse = getAdresse().getText();
        String by = getBy().getText();
        int postnummer = Integer.parseInt(getPostnummer().getText());
        int telefonnummer = Integer.parseInt(getTlf().getText());
        int loginId = Integer.parseInt(getId().getText());
        String kodeord = getKodeord().getText();
        new Ansat(fornavn,efternavn,adresse,by,postnummer,telefonnummer,loginId,kodeord);
    }


    private void opbygningAfPane(){

        //adder tableview og label til vbox
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(1));
        vbox.getChildren().addAll(label, table);

        //sætter størrelse på tilføj og slet knap
        tilfoj.setPrefSize(100,30);
        slet.setPrefSize(100,30);
        slet.setId("sletknap");
        tilbage.setId("tilbage");
        //tilføjer textfields id,kodeord, fornavn, efternavn samt sætter padding og spacing
        vboxTextFields1.setSpacing(20);
        vboxTextFields1.setPadding(new Insets(1));
        vboxTextFields1.getChildren().addAll(id,kodeord, fornavn, efternavn);
        vboxTextFields1.setPrefSize(400, 30);

        //tilføjer textfields adresse,by,postnummer,tlf samt sætter padding og spacing
        vBoxTextFields2.setSpacing(20);
        vBoxTextFields2.getChildren().addAll(adresse,by,postnummer,tlf);
        vBoxTextFields2.setPrefSize(400,30);

        //adder de 2 vbox med textfields til hbox
        hBox.setSpacing(20);
        hBox.getChildren().addAll(vboxTextFields1,vBoxTextFields2);

        //tilføjer tilføj og slet kanpper til hboxknapper
        hBoxknapper.setSpacing(80);
        hBoxknapper.getChildren().addAll(tilfoj,slet);

        //tilføjer hbox og hboxKnapper til vboxKnapText
        vBoxKnapText.getChildren().addAll(hBox,hBoxknapper);
        vBoxKnapText.setSpacing(10);

        tilbage.setPrefSize(100,30);
        logud.setPrefSize(100,30);

        // tilføjer tilbage og log ud knap til nederstHbox
        nederstHbox.setSpacing(80);
       // nederstHbox.setPadding(new Insets(10));
        nederstHbox.getChildren().addAll(tilbage, logud);


        // tilføjer vbox og vbox og vboxKnapText til borderpane og giver dem placering top og bottom
        borderPane.setPadding(new Insets(50));

        borderPane.setTop(vbox);
        borderPane.setLeft(vBoxKnapText);
        borderPane.setBottom(nederstHbox);
    }

    private void indstillertableview(){

        table.setPrefSize(1400, 600);
        table.setEditable(true);

        //Opretter kolonner i table
        TableColumn idCol = new TableColumn("ID nr.");
        TableColumn fornavnCol = new TableColumn("Fornavn");
        TableColumn efternavnCol = new TableColumn("Efternavn");
        TableColumn adresseCol = new TableColumn("Adresse");
        TableColumn byCol = new TableColumn("By");
        TableColumn postnummerCol = new TableColumn("Postnummer");
        TableColumn tlfCol = new TableColumn("Tlf. nr.");

        idCol.setPrefWidth(230);
        fornavnCol.setPrefWidth(230);
        efternavnCol.setPrefWidth(230);
        adresseCol.setPrefWidth(230);
        byCol.setPrefWidth(230);
        postnummerCol.setPrefWidth(230);
        tlfCol.setPrefWidth(230);

        idCol.setCellValueFactory(new PropertyValueFactory<Ansat,Integer>("loginId"));
        fornavnCol.setCellValueFactory(new PropertyValueFactory<Ansat, String>("fornavn"));
        efternavnCol.setCellValueFactory(new PropertyValueFactory<Ansat, String>("efternavn"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<Ansat, String>("adresse"));
        byCol.setCellValueFactory(new PropertyValueFactory<Ansat,String>("by"));
        postnummerCol.setCellValueFactory(new PropertyValueFactory<Ansat,Integer>("postnummer"));
        tlfCol.setCellValueFactory(new PropertyValueFactory<Ansat,Integer>("telefonnummer"));

        table.getColumns().addAll(idCol, fornavnCol, efternavnCol, adresseCol, byCol, postnummerCol, tlfCol);

        fornavn.setOnMouseClicked(event -> {
            fornavn.setText("");
        });
        efternavn.setOnMouseClicked(event -> {
            efternavn.setText("");
        });
        adresse.setOnMouseClicked(event -> {
            adresse.setText("");
        });
        by.setOnMouseClicked(event -> {
            by.setText("");
        });
        postnummer.setOnMouseClicked(event -> {
            postnummer.setText("");
        });
        tlf.setOnMouseClicked(event -> {
            tlf.setText("");
        });
        id.setOnMouseClicked(event -> {
            id.setText("");
        });
        kodeord.setOnMouseClicked(event -> {
            kodeord.setText("");
        });

        logud.setId("logud");

    }

    public void indsætTekstIfields(){
        id.setText("ID nr.");
        fornavn.setText("Fornavn");
        efternavn.setText("Efternavn");
        adresse.setText("Adresse");
        by.setText("By");
        postnummer.setText("Postnummer");
        tlf.setText("Tlf.nr.");
        kodeord.setText("Kodeord");
    }

    public TextField getId() {
        return id;
    }

    public void setId(TextField id) {
        this.id = id;
    }

    public TextField getFornavn() {
        return fornavn;
    }

    public void setFornavn(TextField fornavn) {
        this.fornavn = fornavn;
    }

    public TextField getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(TextField efternavn) {
        this.efternavn = efternavn;
    }

    public TextField getAdresse() {
        return adresse;
    }

    public void setAdresse(TextField adresse) {
        this.adresse = adresse;
    }

    public TextField getBy() {
        return by;
    }

    public void setBy(TextField by) {
        this.by = by;
    }

    public TextField getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(TextField postnummer) {
        this.postnummer = postnummer;
    }

    public TextField getTlf() {
        return tlf;
    }

    public void setTlf(TextField tlf) {
        this.tlf = tlf;
    }

    public Button getTilfoj() {
        return tilfoj;
    }

    public void setTilfoj(Button tilfoj) {
        this.tilfoj = tilfoj;
    }

    public Button getSlet() {
        return slet;
    }

    public void setSlet(Button slet) {
        this.slet = slet;
    }

    public Button getTilbage() {
        return tilbage;
    }

    public void setTilbage(Button tilbage) {
        this.tilbage = tilbage;
    }

    public Button getLogud() {
        return logud;
    }

    public void setLogud(Button logud) {
        this.logud = logud;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public TextField getKodeord() {
        return kodeord;
    }

    public TableView getTable() {
        return table;
    }

    public void setTable(TableView table) {
        this.table = table;
    }

    public void setKodeord(TextField kodeord) {
        this.kodeord = kodeord;
    }
}
