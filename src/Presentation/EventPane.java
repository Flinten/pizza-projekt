package Presentation;

import Business.Kunde;
import Business.Udbringning;
import Database.MySqlConnection;
import com.sun.javafx.css.Size;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//lavet af alle

/**
 * Created by Ejer on 22-05-2017.
 */
public class EventPane {

    TableView table = new TableView();

    //Overskrift
    Label label = new Label("Her kan du oprette et event");

    //Opretter textfields
    TextField fornavn = new TextField("Fornavn");
    TextField efternavn = new TextField("Efternavn");
    TextField adresse = new TextField("Adresse");
    TextField by = new TextField("By");
    TextField postnummer = new TextField("Postnummer");
    TextField tlf = new TextField("Telefon nummer");
    TextField kommentar = new TextField("Kommentar");

    DatePicker datePicker = new DatePicker();

    //Knap til at gemme event
    Button tilfoj = new Button("Gem event");
    Button slet = new Button("Slet event");
    //Knapper til at gå tilbage og logge ud
    Button tilbage = new Button("Tilbage til hovedmenu");
    Button logud = new Button("Log ud");

    //Opretter kolonner i table
    TableColumn idCol = new TableColumn("Id");
    TableColumn fornavnCol = new TableColumn("Fornavn");
    TableColumn efternavnCol = new TableColumn("Efternavn");
    TableColumn adresseCol = new TableColumn("Adresse");
    TableColumn byCol = new TableColumn("By");
    TableColumn postnummerCol = new TableColumn("Postnummer");
    TableColumn tlfCol = new TableColumn("Tlf. nr.");
    TableColumn datoCol = new TableColumn("Dato");
    TableColumn kommentarCol = new TableColumn("Kommentar");


    VBox tableBox = new VBox();

    //vbox til tilbage og log ud knapperne og til textfields
    VBox nederstVbox = new VBox();

    //hbox til de 2 knapper, log ud og tilbage til hovedmenu
    HBox hBox = new HBox();

    //Vbox til textfields & datepicker under tabellen
    VBox vbox = new VBox();

    //Hbox til tilføjSletKnapper
    HBox hBoxtilføjSletKnap = new HBox();

    BorderPane borderPane = new BorderPane();



    public EventPane() {
    }

    public BorderPane getEventPane(Stage primaryStage, Scene scene, ObservableList observableList, HovedMenuPane hovedMenuPane){


        //Kalder textfiledInstalition metode
        textfieldInstalition();
        //Kalder tableViewInstalition metode
        tableViewInstalition();
        //Kalder datepickerInstalition metode
        datepickerInstalition();
        //kalder opbygningAfPane metode
        opbygningAfPane();

        eventPaneKnapper(scene, primaryStage, observableList, hovedMenuPane);

        return borderPane;
    }

    //metode til at lave setOnAction på knapper i EventPane
    private void eventPaneKnapper(Scene scene, Stage primaryStage, ObservableList listEvent, HovedMenuPane hovedMenuPane){
        getTilbage().setOnAction(event -> {
            scene.setRoot(hovedMenuPane.getHovedMenuPane());
            primaryStage.setScene(scene);
        });
        //eventhandler til log ud knap
        getLogud().setOnAction(event -> {
            System.exit(1);
        });
        //eventhandler til tilføj knap
        getTilfoj().setOnAction(event -> {
            opretEvent(listEvent);
        });
        //evnethandler til slet knap
        getSlet().setOnAction(event -> {
            Udbringning udbringning1;
            udbringning1 = (Udbringning) getTable().getSelectionModel().getSelectedItem();
            udbringning1.sletEvent();
            int listSletIndex = getTable().getSelectionModel().getSelectedIndex();
            listEvent.remove(listSletIndex);
        });
    }
    // metode til at oprette event
    private void opretEvent(ObservableList listEvent){
        String fornavn = getFornavn().getText();
        String efternavn = getEfternavn().getText();
        String adresse = getAdresse().getText();
        String by = getBy().getText();
        int postnummer = Integer.parseInt(getPostnummer().getText());
        int telefonnummer = Integer.parseInt(getTlf().getText());
        Date dato = Date.valueOf(getDatePicker().getValue().toString());
        String kommentar = getKommentar().getText();
        //opretter nyt event objekt
        new Udbringning(fornavn,efternavn,adresse,by,postnummer,telefonnummer,dato,kommentar);
        //indsætter data til tabel
        indsætDataEventTabel(listEvent);
        new Kunde(fornavn,efternavn,adresse,by,postnummer,telefonnummer);
    }

    public void indsætDataEventTabel(ObservableList listEvent){
        listEvent.remove(0,listEvent.size());
        indsætDataEventList(listEvent);
        getTable().setItems(listEvent);

    }
    private void indsætDataEventList(ObservableList listEvent){
        // query
        String query = "SELECT * FROM table_Event";

        MySqlConnection mySqlConnection = new MySqlConnection();

        try {
            Statement st = mySqlConnection.connect().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                int id = rs.getInt(1);
                String fornavn = rs.getString(2);
                String efternavn = rs.getString(3);
                String adresse = rs.getString(4);
                String by = rs.getString(5);
                int postnummer = rs.getInt(6);
                int telefonnummer = rs.getInt(7);
                Date dato = rs.getDate(8);
                String kommentar = rs.getString(9);

                listEvent.add(new Udbringning(id,fornavn,efternavn,adresse,by,postnummer,telefonnummer,dato,kommentar));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void datepickerInstalition(){

        //Datepicker source: http://o7planning.org/en/11085/javafx-datepicker-tutorial
        //Opretter datepicker
        datePicker.setShowWeekNumbers(true);

        // Converter
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        datePicker.setConverter(converter);
        datePicker.setPromptText("dd-MM-yyyy");

    }

        //indstiller tableview
    private void tableViewInstalition(){

            //sætter længden på hver kolonne
        idCol.setPrefWidth(80);
        fornavnCol.setPrefWidth(160);
        efternavnCol.setPrefWidth(160);
        adresseCol.setPrefWidth(200);
        byCol.setPrefWidth(140);
        postnummerCol.setPrefWidth(80);
        tlfCol.setPrefWidth(80);
        datoCol.setPrefWidth(80);
        kommentarCol.setPrefWidth(350);

            //Fortæller hvad hver kolonne skal have af værdier

        fornavnCol.setCellValueFactory(new PropertyValueFactory<Udbringning, String>("fornavn"));
        efternavnCol.setCellValueFactory(new PropertyValueFactory<Udbringning,String>("efternavn"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<Udbringning,String>("adresse"));
        byCol.setCellValueFactory(new PropertyValueFactory<Udbringning,String>("by"));
        postnummerCol.setCellValueFactory(new PropertyValueFactory<Udbringning, Integer>("postnummer"));
        tlfCol.setCellValueFactory(new PropertyValueFactory<Udbringning, Integer>("telefonnummer"));
        datoCol.setCellValueFactory(new PropertyValueFactory<Udbringning, String>("dato"));
        kommentarCol.setCellValueFactory(new PropertyValueFactory<Udbringning, String>("kommentar"));

        //adder kolonner til tableview
        table.getColumns().addAll(fornavnCol, efternavnCol, adresseCol, byCol, postnummerCol, tlfCol, datoCol,kommentarCol);

        //Opretter vbox til tabel

        tableBox.setSpacing(40);
        tableBox.setPadding(new Insets(20));
        tableBox.getChildren().addAll(table);
    }

        //Metode som gør når textfields bliver klikket på fjerner den alt tekst
    private void textfieldInstalition(){

        fornavn.setMaxWidth(300);
        efternavn.setMaxWidth(300);
        adresse.setMaxWidth(300);
        by.setMaxWidth(300);
        postnummer.setMaxWidth(300);
        tlf.setMaxWidth(300);
        kommentar.setMaxWidth(300);


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
        kommentar.setOnMouseClicked(event -> {
            kommentar.setText("");
        });

        logud.setId("sletknap");
        slet.setId("sletknap");
        tilbage.setId("tilbage");
    }

    private void opbygningAfPane(){
        //Hbox til tilføjSletKnapper

        hBoxtilføjSletKnap.setSpacing(10);
        hBoxtilføjSletKnap.getChildren().addAll(tilfoj,slet);

        //Vbox til textfields & datepicker under tabellen
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(60));

        vbox.getChildren().addAll(label, fornavn, efternavn, adresse, by, postnummer, tlf, datePicker,kommentar,hBoxtilføjSletKnap);


        //hbox til de 2 knapper, log ud og tilbage til hovedmenu
        hBox.getChildren().addAll(tilbage,logud);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(60));

        //vbox til tilbage og log ud knapperne og til textfields
        nederstVbox.setSpacing(5);
        //nederstVbox.setId("border2");
        nederstVbox.getChildren().addAll(vbox,hBox);



        //Borderpane til vbox fra tabel og til hbox fra textfields
        borderPane.setLeft(tableBox);
        borderPane.setBottom(nederstVbox);
    }

    public Button getSlet() {
        return slet;
    }

    public void setSlet(Button slet) {
        this.slet = slet;
    }

    public TableView getTable() {
        return table;
    }

    public void setTable(TableView table) {
        this.table = table;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
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

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
    }

    public Button getTilfoj() {
        return tilfoj;
    }

    public void setTilfoj(Button tilfoj) {
        this.tilfoj = tilfoj;
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

    public TextField getKommentar() {
        return kommentar;
    }

    public void setKommentar(TextField kommentar) {
        this.kommentar = kommentar;
    }
}
