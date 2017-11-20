package Presentation;

//orderPane lavet af Casper Flintrup

//import Business.Tilbehør.EkstraTilbehørMad.*;
import Business.Udbringning;
import Business.Ordre;
import Business.Produkter.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Created by Ejer on 17-05-2017.
 */
// orderPane lavet af Casper Flintrup
public class orderPane {
    //Labels til orderPane
    Label labelOverskrift = new Label("Pizza Palace");
    Label labelMenuNr = new Label("Vælg hvilken slags mad");
    Label labelVælgTilbehør = new Label("Vælg Tilbehør.");
    Label labelSodaVand = new Label("Vælg drikkevare");
    Label labelKurv = new Label("Kurv");
    Label labelTotal = new Label("Total:");
    Label labelDåse = new Label("Dåsesodavand");
    Label labelSodavandLille = new Label("Sodavand 0,5");
    Label labelSodavandStor = new Label("Sodavand 1,5");

    Label label = new Label("Kryds af hvis der skal være levering");
    //Opretter checkboxes til at vælge tilbehør 12 stk.
    CheckBox checkBoxAnanas = new CheckBox("Ananas: 8 kr");
    CheckBox checkBoxBacon = new CheckBox("Bacon: 11 kr");
    CheckBox checkBoxBanan = new CheckBox("Banan: 9 kr");
    CheckBox checkBoxChampignon = new CheckBox("Champignon: 8 kr");
    CheckBox checkBoxChili = new CheckBox("Chili: 5 kr");
    CheckBox checkBoxCocktailPølse = new CheckBox("Cocktailpølser: 8 kr");
    CheckBox checkBoxCremeFraiche = new CheckBox("Creme Fraiche: 7 kr");
    CheckBox checkBoxDej = new CheckBox("Dej: 6 kr");
    CheckBox checkBoxFetaost = new CheckBox("Fetaost: 10 kr");
    CheckBox checkBoxHvidløg = new CheckBox("Hvidløg: 4 kr");
    CheckBox checkBoxKebab = new CheckBox("Kebab: 12 kr");
    CheckBox checkBoxKyling = new CheckBox("Kylling: 12 kr");
    //Textarea for se pris summen af orderen

    DatePicker datePicker = new DatePicker();

    TextField fornavn = new TextField("Fornavn");
    TextField efternavn = new TextField("Efternavn");
    TextField adresse = new TextField("Adresse");
    TextField by = new TextField("By");
    TextField postnummer = new TextField("Postnummer");
    TextField tlf = new TextField("Telefon nummer");
    TextField kommentar = new TextField("Kommentar");

    //Knap til at gemme event
    Button tilfoj = new Button("Gem event");
    Button slet = new Button("Slet event");
    //Knapper til at gå tilbage og logge ud
    Button tilbage = new Button("Tilbage");
    Button logud = new Button("Log ud");

    RadioButton radioButton = new RadioButton();

    VBox vbox = new VBox();

    //Textfield til at finde hvilken pizza der skal bestilles
    TextField textFieldMenuNr = new TextField();

    //Textfield for se pris summen af orderen
    TextField textFieldTotalSum = new TextField();

    VBox tableBox = new VBox();

    //vbox til tilbage og log ud knapperne og til textfields
    VBox nederstVbox = new VBox();

    //hbox til de 2 knapper, log ud og tilbage til hovedmenu
    HBox hBox = new HBox();

    HBox hBoxtilføjSletKnap = new HBox();

    //Knapper til orderPane
    Button buttonTilføjTilKruv = new Button("Tilføj til kurv");
    Button buttonBetal = new Button("Betal");
    Button buttonSlet = new Button("Fjern fra kurv");
    Button buttonSodavandLille = new Button("Tilføj 0,33\n   12 Kr");
    Button buttonSodavandMellem= new Button("Tilføj 0,5\n  20 kr");
    Button buttonSodavandStor = new Button("Tilføj 1,5\n   32 kr");
    Button buttonTilføjMad = new Button("Tilføj mad");

    //Comboboxes til valg af hvilken sodavand
    ComboBox comboBoxSodavandLille = new ComboBox();
    ComboBox comboBoxSodavandMellem = new ComboBox();
    ComboBox comboBoxSodavandStor = new ComboBox();

    //opretter tableview og kolonner
    TableView tableView = new TableView();
    TableColumn tableColumnMenuNr = new TableColumn("Menu Nr");
    TableColumn tableColumnMenuNrNavn = new TableColumn("Navn");
    TableColumn tableColumnMenuNrPris = new TableColumn("Pris");

    //Opretter observableList til sodavand
    ObservableList<Tilbehør> observableListLille = FXCollections.observableArrayList();
    ObservableList<Tilbehør> observableListMellem = FXCollections.observableArrayList();
    ObservableList<Tilbehør> observableListStor = FXCollections.observableArrayList();

    ObservableList<Tilbehør> observableListMad = FXCollections.observableArrayList();

    //hbox til at sætte label og textfield til at bestille mad
    HBox hBoxMenuNr = new HBox();

    // opretter 3 Vbox til checkboxes (tilbehør) og sætter 50 pixel mellemrum
    VBox vBoxTilbehør1 = new VBox();
    VBox vBoxTilbehør2 = new VBox();
    VBox vBoxTilbehør3 = new VBox();

    //opretter hbox til alle vboxes med tilbehør checkboxes
    HBox hBoxAlleTilbehør = new HBox();

    //opretter vbokse til drikke vare
    VBox vBoxVælgSodavand = new VBox();

    //opretter vboks til sodavands knapper
    VBox vBoxSodavandKnapper = new VBox();

    //Opretter Hbox til sodavands comboboxs og knapper
    HBox hBoxSodavand = new HBox();

    //opretter vbox til venstre side af orderPane
    VBox vBoxVenstre = new VBox();

    //Opretter hbox til afslut og slet knapperne
    HBox hBoxAfslutSlet = new HBox();

    //Opretter vboks der skal være i højre side af orderPane
    VBox vBoxHøjre = new VBox();

    //Opretter hbox som bliver returneret i getOrderPane()
    HBox hBoxFullPane = new HBox();

    //hbox til tilbage og log ud knapperne
    HBox nederstHbox = new HBox();

    ComboBox comboBox = new ComboBox();



    public orderPane() {
    }
    public HBox getOrderPane(Stage primaryStage, Scene scene, ObservableList observableList, HovedMenuPane hovedMenuPane){

        //sætter knapperne i den rigtig størrelse metode
        setSize();

        //instiller Comboxes metode
        instilCombox();

        //Indstiller tableview se metode længere nede
        createTableView();

        //opbygger hele pane metode
        opbygningAfPane();

        textfieldInstalition();

        datepickerInstalition();

        orderPaneKnapper(scene, primaryStage, observableList, hovedMenuPane);

        return hBoxFullPane;
    }
    //metode til at lave setOnAction på knapper i orderPane
    public void orderPaneKnapper(Scene scene, Stage primaryStage,  ObservableList produktList, HovedMenuPane hovedMenuPane){

        // Når tilføj
        // knappen bliver trykket på tilføjer den først en pizza og der efter det valgte tilbehør
        getButtonTilføjMad().setOnAction(event -> {

            produktList.add(comboBox.getSelectionModel().getSelectedItem());
            checkCheckBoxes(produktList);
            getTextFieldTotalSum().setText(String.valueOf(indsætPrisItextField(produktList)));
            getTableView().setItems(produktList);

        });
        getButtonSodavandLille().setOnAction(event -> {
            Tilbehør tilbehør = (Tilbehør) getComboBoxSodavandLille().getSelectionModel().getSelectedItem();
            produktList.add(kloneObjekt(tilbehør));
            getTextFieldTotalSum().setText(String.valueOf(indsætPrisItextField(produktList)));
            getTableView().setItems(produktList);
        });
        getButtonSodavandMellem().setOnAction(event -> {
            Tilbehør tilbehør = (Tilbehør) getComboBoxSodavandMellem().getSelectionModel().getSelectedItem();
            produktList.add(kloneObjekt(tilbehør));
            getTextFieldTotalSum().setText(String.valueOf(indsætPrisItextField(produktList)));
            getTableView().setItems(produktList);
        });
        getButtonSodavandStor().setOnAction(event -> {
            Tilbehør tilbehør = (Tilbehør) getComboBoxSodavandStor().getSelectionModel().getSelectedItem();
            produktList.add(kloneObjekt(tilbehør));
            getTextFieldTotalSum().setText(String.valueOf(indsætPrisItextField(produktList)));
            getTableView().setItems(produktList);

        });
        getButtonSlet().setOnAction(event -> {
            getTableView().getSelectionModel().getSelectedItem();
            int listsletIndex = getTableView().getSelectionModel().getFocusedIndex();
            produktList.remove(listsletIndex);
            getTextFieldTotalSum().setText(String.valueOf(indsætPrisItextField(produktList)));
        });

        getButtonBetal().setOnAction(event -> {
            DatePicker datePicker = new DatePicker(LocalDate.now());
            String date = String.valueOf(datePicker.getValue());
            double pris = Double.parseDouble(getTextFieldTotalSum().getText());
            udskrivKvitering(produktList);
            Ordre ordre = new Ordre(date,pris);
            if(radioButton.isSelected()) {
                opretEvent();
            }
            textfieldInstalition();
            getTextFieldTotalSum().clear();
            produktList.remove(0,produktList.size());
        });
        getLogud().setOnAction(event -> {
            System.exit(1);
        });
        getTilbage().setOnAction(event -> {
            scene.setRoot(hovedMenuPane.getHovedMenuPane());
            primaryStage.setScene(scene);
        });

    }

        // checker alle checkboxes og tilføjer tilbefør hvis comboboxen krydset af
    private void checkCheckBoxes(ObservableList observableList){

        if(getCheckBoxAnanas().isSelected()){
            Ananas ananas = new Ananas();
            observableList.add(ananas);
            getCheckBoxAnanas().setSelected(false);
        }
        if(getCheckBoxBacon().isSelected()){
            Bacon bacon = new Bacon();
            observableList.add(bacon);
            getCheckBoxBacon().setSelected(false);
        }
        if (getCheckBoxBanan().isSelected()){
            Banan banan = new Banan();
            observableList.add(banan);
            getCheckBoxBanan().setSelected(false);
        }
        if (getCheckBoxChampignon().isSelected()){
            Champignon champignon = new Champignon();
            observableList.add(champignon);
            getCheckBoxChampignon().setSelected(false);
        }
        if(getCheckBoxChili().isSelected()){
            Chili chili = new Chili();
            observableList.add(chili);
            getCheckBoxChili().setSelected(false);
        }
        if (getCheckBoxCocktailPølse().isSelected()){
            Cocktailpølser cocktailpølser = new Cocktailpølser();
            observableList.add(cocktailpølser);
            getCheckBoxCocktailPølse().setSelected(false);
        }
        if (getCheckBoxCremeFraiche().isSelected()){
            CremeFraiche cremeFraiche = new CremeFraiche();
            observableList.add(cremeFraiche);
            getCheckBoxCremeFraiche().setSelected(false);
        }
        if (getCheckBoxDej().isSelected()){
            Dej dej = new Dej();
            observableList.add(dej);
            getCheckBoxDej().setSelected(false);
        }
        if (getCheckBoxFetaost().isSelected()){
            Fetaost fetaost = new Fetaost();
            observableList.add(fetaost);
            getCheckBoxFetaost().setSelected(false);
        }
        if (getCheckBoxHvidløg().isSelected()){
            Hvidløg hvidløg = new Hvidløg();
            observableList.add(hvidløg);
            getCheckBoxHvidløg().setSelected(false);
        }
        if (getCheckBoxKebab().isSelected()){
            Kebab kebab = new Kebab();
            observableList.add(kebab);
            getCheckBoxKebab().setSelected(false);
        }
        if (getCheckBoxKyling().isSelected()){
            Kylling kylling = new Kylling();
            observableList.add(kylling);
            getCheckBoxKyling().setSelected(false);
        }
    }
    private void fjernEventInfo(){
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
    }

    // metode kloner objekt som er parameter overført
    // source code: https://stackoverflow.com/questions/869033/how-do-i-copy-an-object-in-java{
    private Tilbehør kloneObjekt(Tilbehør tilbehør){
        try{
            Object clone = tilbehør.getClass().newInstance();
            for (Field field : tilbehør.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                field.set(clone, field.get(tilbehør));
            }
            return tilbehør;
        }catch(Exception e){
            return null;
        }
    }
    //} slut metode taget fra https://stackoverflow.com/questions/869033/how-do-i-copy-an-object-in-java

    //metode der finder den samlet værdi af alle produkter valgt
    //lavet af casper
    private double indsætPrisItextField(ObservableList<Tilbehør> observableList){
        double sum = 0;
        for(int i = 0; i < observableList.size(); i++){
            sum += observableList.get(i).getPris();
        }
        return sum;
    }

    private void opbygningAfPane(){

        //Vbox til textfields & datepicker

       // vbox.setSpacing(15);
        vbox.setPadding(new Insets(20));
        vbox.setSpacing(30);
        vbox.getChildren().add(radioButton);
        vbox.setSpacing(0);
        vbox.getChildren().addAll(label, fornavn, efternavn, adresse, by, postnummer, tlf, datePicker,kommentar);
        vbox.setId("border");
        //hbox til de 2 knapper, log ud og tilbage til hovedmenu
        hBox.getChildren().addAll(tilbage,logud);
        hBox.setSpacing(10);

        //vbox til tilbage og log ud knapperne og til textfields
        nederstVbox.setSpacing(5);
        nederstVbox.setPadding(new Insets(20));

        nederstVbox.getChildren().addAll(vbox,hBox);

        comboBox.getItems().addAll(new Pitabrød(),new Pizza(),new Burger(),new Tyrkiskbrød(), new Durum());


        //hbox til at sætte label og textfield til at bestille mad
        hBoxMenuNr.setSpacing(250);
        hBoxMenuNr.setPadding(new Insets(10));
        hBoxMenuNr.getChildren().addAll(labelMenuNr, comboBox);
        hBoxMenuNr.setId("border");

        //sætter spacing imellem
        vBoxTilbehør1.setSpacing(20);
        vBoxTilbehør2.setSpacing(20);
        vBoxTilbehør3.setSpacing(20);

        //Tilføjer checkboxes som indeholder tilbehør til pizzaen til de 3 hboxes
        vBoxTilbehør1.getChildren().addAll(checkBoxAnanas,checkBoxBacon,checkBoxBanan,checkBoxChampignon);
        vBoxTilbehør2.getChildren().addAll(checkBoxChili,checkBoxCocktailPølse,checkBoxCremeFraiche,checkBoxDej);
        vBoxTilbehør3.getChildren().addAll(checkBoxFetaost,checkBoxHvidløg,checkBoxKebab,checkBoxKyling);



        //adder vbox tilbehør til hboxAlleTilbehør
        hBoxAlleTilbehør.setSpacing(50);
        hBoxAlleTilbehør.getChildren().addAll(vBoxTilbehør1,vBoxTilbehør2,vBoxTilbehør3);
        hBoxAlleTilbehør.setPadding(new Insets(10));
        hBoxAlleTilbehør.setId("border");

        //adder label og comboBox til vBoxVælgSodavand
        vBoxVælgSodavand.setSpacing(10);
        vBoxVælgSodavand.getChildren().addAll(labelDåse,comboBoxSodavandLille);
        vBoxVælgSodavand.setSpacing(30);
        vBoxVælgSodavand.getChildren().addAll(labelSodavandLille,comboBoxSodavandMellem);
        vBoxVælgSodavand.setSpacing(10);
        vBoxVælgSodavand.getChildren().addAll(labelSodavandStor,comboBoxSodavandStor);

        //indsætter tilføj sodavandkanpperne til vboxSodavandKnapper
        vBoxSodavandKnapper.setSpacing(10);
        vBoxSodavandKnapper.getChildren().add(buttonSodavandLille);
        vBoxSodavandKnapper.getChildren().add(buttonSodavandMellem);
        vBoxSodavandKnapper.getChildren().add(buttonSodavandStor);

        // tilføjer vBoxVælgSodavand,vBoxSodavandKnapper til hboxSodavand
        hBoxSodavand.setSpacing(250);
        hBoxSodavand.getChildren().addAll(vBoxVælgSodavand,vBoxSodavandKnapper);
        hBoxSodavand.setPadding(new Insets(10));
        hBoxSodavand.setId("border");

        //Sætter vboks padding til hvor mange pixel den skal sænkes fra top og hvor meget den skal til højre
        vBoxVenstre.setSpacing(5);
        vBoxVenstre.setPadding(new Insets(40,0,20,40));
        vBoxVenstre.setSpacing(10);
        vBoxVenstre.getChildren().addAll(hBoxMenuNr);
        vBoxVenstre.setSpacing(20);
        vBoxVenstre.getChildren().add(labelVælgTilbehør);
        vBoxVenstre.getChildren().addAll(hBoxAlleTilbehør,buttonTilføjMad);


        //Indsætter en større Spacing imellem til drikkevare
        vBoxVenstre.setSpacing(10);
        vBoxVenstre.getChildren().add(labelSodaVand);
        vBoxVenstre.setSpacing(10);
        vBoxVenstre.getChildren().addAll(hBoxSodavand,vbox);

        // adder slet og afslut knapper til hboxAfslutSlet
        hBoxAfslutSlet.setSpacing(80);
        hBoxAfslutSlet.getChildren().addAll(buttonSlet);
        // tilføjer tilbage og log ud knap til nederstHbox
        nederstHbox.setSpacing(80);
        // nederstHbox.setPadding(new Insets(10));
        nederstHbox.getChildren().addAll(tilbage, logud);
        //adder tableview,textfieldTotalSum og hboxAfslut og buttonBetal til vBoxHøjre
        vBoxHøjre.getChildren().add(tableView);
        vBoxHøjre.setSpacing(50);
        vBoxHøjre.getChildren().add(textFieldTotalSum);
        vBoxHøjre.getChildren().add(hBoxAfslutSlet);
        vBoxHøjre.getChildren().add(buttonBetal);
        vBoxHøjre.getChildren().add(nederstHbox);
        vBoxHøjre.setPadding(new Insets(130,50,100,0));




        // indsætter vbocVenstre og vboxHøjre til hBoxFullPane som bliver retuneret
        hBoxFullPane.setSpacing(600);
        hBoxFullPane.getChildren().addAll(vBoxVenstre, vBoxHøjre);
    }

    private void setSize(){
        checkBoxChampignon.setPrefWidth(200);
        checkBoxFetaost.setPrefWidth(200);

        buttonTilføjMad.setPrefSize(200,64);
        buttonSodavandLille.setPrefSize(100,64);
        buttonSodavandMellem.setPrefSize(100,64);
        buttonSodavandStor.setPrefSize(100,64);
        buttonSlet.setPrefSize(480,100);

        buttonBetal.setPrefSize(480,100);
        comboBoxSodavandLille.setMinWidth(160);
        comboBoxSodavandMellem.setMinWidth(160);
        comboBoxSodavandStor.setMinWidth(160);
        buttonBetal.setId("hallo");
        logud.setPrefSize(160,64);
        tilbage.setPrefSize(160,64);
        textFieldTotalSum.setPrefSize(200,70);
        buttonBetal.setId("betalknap");
        buttonSlet.setId("sletknaporder");
        buttonSlet.setId("sletknaporder");

        buttonSodavandLille.setId("betalknap");
        buttonSodavandMellem.setId("betalknap");
        buttonSodavandStor.setId("betalknap");
        buttonTilføjMad.setId("betalknap");
        logud.setId("sletknap");
        tilbage.setId("tilbage");

    }

    private void instilObservableListSodavand(){

        ColaLille colaLille = new ColaLille();
        ColaMellem colaMellem = new ColaMellem();
        ColaStor colaStor = new ColaStor();

        ColaZeroLille colaZeroLille = new ColaZeroLille();
        ColaZeroMellem colaZeroMellem = new ColaZeroMellem();
        ColaZeroStor colaZeroStor = new ColaZeroStor();

        FantaLille fantaLille = new FantaLille();
        FantaMellem fantaMellem = new FantaMellem();
        FantaStor fantaStor = new FantaStor();

        FaxeLille faxeLille = new FaxeLille();
        FaxeMellem faxeMellem = new FaxeMellem();
        FaxeStor faxeStor = new FaxeStor();

        Pitabrød pitabrød = new Pitabrød();
        Pizza pizza = new Pizza();
        Durum durum = new Durum();
        Burger burger = new Burger();


        observableListMad.addAll(pitabrød,pizza,durum,burger);
        observableListLille.addAll(colaLille,colaZeroLille,fantaLille,faxeLille);
        observableListMellem.addAll(colaMellem,colaZeroMellem,fantaMellem,faxeMellem);
        observableListStor.addAll(colaStor,colaZeroStor,fantaStor,faxeStor);

    }

    //lavet af casper
    private void udskrivKvitering(ObservableList<Tilbehør> produktList){
        try {

            PrintStream printStream = new PrintStream("C:\\Users\\Ejer\\IdeaProjects\\pizza projekt\\src\\Business\\Kvitering");
            printStream.print("\tPizza and burger house\n\n");
            printStream.print("Produkt navn\t\t\tPris\n\n");
            for(int i = 0; i < produktList.size(); i++){

                printStream.print(produktList.get(i).getKviteringOpsætning()+"\n");
            }
            printStream.print("\nTotal:\t\t\t\t\t"+indsætPrisItextField(produktList));
            printStream.print(
                    "\n\n\tPizza burger house\n" +
                    "\tTelefon 12345678\n" +
                    "\tAdresse Sydhanvsvej 7\n" +
                    "\tBy: Sydhavn\n" +
                    "\tpost nummer 2450");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void instilCombox(){
        instilObservableListSodavand();
        comboBoxSodavandLille.getItems().addAll(observableListLille);
        comboBoxSodavandMellem.getItems().addAll(observableListMellem);
        comboBoxSodavandStor.getItems().addAll(observableListStor);
    }

    private void createTableView(){

        tableColumnMenuNr.setCellValueFactory(
                new PropertyValueFactory<Tilbehør,String>("navn"));
        tableColumnMenuNr.setPrefWidth(120);
        tableColumnMenuNrNavn.setCellValueFactory(
                new PropertyValueFactory<Tilbehør,String>("produktType"));
        tableColumnMenuNrNavn.setPrefWidth(150);
        tableColumnMenuNrPris.setCellValueFactory(
                new PropertyValueFactory<Tilbehør,Double>("pris"));
        tableColumnMenuNrPris.setPrefWidth(100);

        tableView.getColumns().addAll(tableColumnMenuNr,tableColumnMenuNrNavn,tableColumnMenuNrPris);
        tableView.setPrefSize(400,500);

    }

    public Label getLabelOverskrift() {
        return labelOverskrift;
    }

    public void setLabelOverskrift(Label labelOverskrift) {
        this.labelOverskrift = labelOverskrift;
    }

    public Label getLabelMenuNr() {
        return labelMenuNr;
    }

    public void setLabelMenuNr(Label labelMenuNr) {
        this.labelMenuNr = labelMenuNr;
    }

    public Label getLabelVælgTilbehør() {
        return labelVælgTilbehør;
    }

    public void setLabelVælgTilbehør(Label labelVælgTilbehør) {
        this.labelVælgTilbehør = labelVælgTilbehør;
    }

    public Label getLabelSodaVand() {
        return labelSodaVand;
    }

    public void setLabelSodaVand(Label labelSodaVand) {
        this.labelSodaVand = labelSodaVand;
    }

    public Label getLabelKurv() {
        return labelKurv;
    }

    public void setLabelKurv(Label labelKurv) {
        this.labelKurv = labelKurv;
    }

    public Label getLabelTotal() {
        return labelTotal;
    }

    public void setLabelTotal(Label labelTotal) {
        this.labelTotal = labelTotal;
    }

    public Label getLabelDåse() {
        return labelDåse;
    }

    public void setLabelDåse(Label labelDåse) {
        this.labelDåse = labelDåse;
    }

    public Label getLabelSodavandLille() {
        return labelSodavandLille;
    }

    public void setLabelSodavandLille(Label labelSodavandLille) {
        this.labelSodavandLille = labelSodavandLille;
    }

    public Label getLabelSodavandStor() {
        return labelSodavandStor;
    }

    public void setLabelSodavandStor(Label labelSodavandStor) {
        this.labelSodavandStor = labelSodavandStor;
    }

    public CheckBox getCheckBoxAnanas() {
        return checkBoxAnanas;
    }

    public void setCheckBoxAnanas(CheckBox checkBoxAnanas) {
        this.checkBoxAnanas = checkBoxAnanas;
    }

    public CheckBox getCheckBoxBacon() {
        return checkBoxBacon;
    }

    public void setCheckBoxBacon(CheckBox checkBoxBacon) {
        this.checkBoxBacon = checkBoxBacon;
    }

    public CheckBox getCheckBoxBanan() {
        return checkBoxBanan;
    }

    public void setCheckBoxBanan(CheckBox checkBoxBanan) {
        this.checkBoxBanan = checkBoxBanan;
    }

    public CheckBox getCheckBoxChampignon() {
        return checkBoxChampignon;
    }

    public void setCheckBoxChampignon(CheckBox checkBoxChampignon) {
        this.checkBoxChampignon = checkBoxChampignon;
    }

    public CheckBox getCheckBoxChili() {
        return checkBoxChili;
    }

    public void setCheckBoxChili(CheckBox checkBoxChili) {
        this.checkBoxChili = checkBoxChili;
    }

    public CheckBox getCheckBoxCocktailPølse() {
        return checkBoxCocktailPølse;
    }

    public void setCheckBoxCocktailPølse(CheckBox checkBoxCocktailPølse) {
        this.checkBoxCocktailPølse = checkBoxCocktailPølse;
    }

    public CheckBox getCheckBoxCremeFraiche() {
        return checkBoxCremeFraiche;
    }

    public void setCheckBoxCremeFraiche(CheckBox checkBoxCremeFraiche) {
        this.checkBoxCremeFraiche = checkBoxCremeFraiche;
    }

    public CheckBox getCheckBoxDej() {
        return checkBoxDej;
    }

    public void setCheckBoxDej(CheckBox checkBoxDej) {
        this.checkBoxDej = checkBoxDej;
    }

    public CheckBox getCheckBoxFetaost() {
        return checkBoxFetaost;
    }

    public void setCheckBoxFetaost(CheckBox checkBoxFetaost) {
        this.checkBoxFetaost = checkBoxFetaost;
    }

    public CheckBox getCheckBoxHvidløg() {
        return checkBoxHvidløg;
    }

    public void setCheckBoxHvidløg(CheckBox checkBoxHvidløg) {
        this.checkBoxHvidløg = checkBoxHvidløg;
    }

    public CheckBox getCheckBoxKebab() {
        return checkBoxKebab;
    }

    public void setCheckBoxKebab(CheckBox checkBoxKebab) {
        this.checkBoxKebab = checkBoxKebab;
    }

    public CheckBox getCheckBoxKyling() {
        return checkBoxKyling;
    }

    public void setCheckBoxKyling(CheckBox checkBoxKyling) {
        this.checkBoxKyling = checkBoxKyling;
    }

    public TextField getTextFieldMenuNr() {
        return textFieldMenuNr;
    }

    public void setTextFieldMenuNr(TextField textFieldMenuNr) {
        this.textFieldMenuNr = textFieldMenuNr;
    }

    public Button getButtonTilføjTilKruv() {
        return buttonTilføjTilKruv;
    }

    public void setButtonTilføjTilKruv(Button buttonTilføjTilKruv) {
        this.buttonTilføjTilKruv = buttonTilføjTilKruv;
    }

    public Button getButtonBetal() {
        return buttonBetal;
    }

    public void setButtonBetal(Button buttonBetal) {
        this.buttonBetal = buttonBetal;
    }

    public Button getButtonSlet() {
        return buttonSlet;
    }

    public void setButtonSlet(Button buttonSlet) {
        this.buttonSlet = buttonSlet;
    }

    public Button getButtonMellem() {
        return buttonSodavandMellem;
    }

    public void setButtonDåse(Button buttonSodavandMellem) {
        this.buttonSodavandMellem = buttonSodavandMellem;
    }

    public Button getButtonSodavandLille() {
        return buttonSodavandLille;
    }

    public void setButtonSodavandLille(Button buttonSodavandLille) {
        this.buttonSodavandLille = buttonSodavandLille;
    }

    public Button getButtonSodavandStor() {
        return buttonSodavandStor;
    }

    public void setButtonSodavandStor(Button buttonSodavandStor) {
        this.buttonSodavandStor = buttonSodavandStor;
    }

    public ComboBox getComboBoxDåsesodavand() {
        return comboBoxSodavandLille;
    }

    public void setComboBoxDåsesodavand(ComboBox comboBoxDåsesodavand) {
        this.comboBoxSodavandLille = comboBoxDåsesodavand;
    }

    public ComboBox getComboBoxSodavandLille() {
        return comboBoxSodavandLille;
    }

    public void setComboBoxSodavandLille(ComboBox comboBoxSodavandLille) {
        this.comboBoxSodavandLille = comboBoxSodavandLille;
    }

    public ComboBox getComboBoxSodavandStor() {
        return comboBoxSodavandStor;
    }

    public void setComboBoxSodavandStor(ComboBox comboBoxSodavandStor) {
        this.comboBoxSodavandStor = comboBoxSodavandStor;
    }

    public TableView getTableView() {
        return tableView;
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }

    public TableColumn getTableColumnMenuNr() {
        return tableColumnMenuNr;
    }

    public void setTableColumnMenuNr(TableColumn tableColumnMenuNr) {
        this.tableColumnMenuNr = tableColumnMenuNr;
    }

    public TableColumn getTableColumnMenuNrNavn() {
        return tableColumnMenuNrNavn;
    }

    public void setTableColumnMenuNrNavn(TableColumn tableColumnMenuNrNavn) {
        this.tableColumnMenuNrNavn = tableColumnMenuNrNavn;
    }

    public TableColumn getTableColumnMenuNrPris() {
        return tableColumnMenuNrPris;
    }

    public void setTableColumnMenuNrPris(TableColumn tableColumnMenuNrPris) {
        this.tableColumnMenuNrPris = tableColumnMenuNrPris;
    }

    public TextField getTextFieldTotalSum() {
        return textFieldTotalSum;
    }

    public void setTextFieldTotalSum(TextField textFieldTotalSum) {
        this.textFieldTotalSum = textFieldTotalSum;
    }

    public Button getButtonTilføjMad() {
        return buttonTilføjMad;
    }

    public void setButtonTilføjMad(Button buttonTilføjMad) {
        this.buttonTilføjMad = buttonTilføjMad;
    }

    public Button getButtonSodavandMellem() {
        return buttonSodavandMellem;
    }

    public void setButtonSodavandMellem(Button buttonSodavandMellem) {
        this.buttonSodavandMellem = buttonSodavandMellem;
    }

    public ComboBox getComboBoxSodavandMellem() {
        return comboBoxSodavandMellem;
    }

    public void setComboBoxSodavandMellem(ComboBox comboBoxSodavandMellem) {
        this.comboBoxSodavandMellem = comboBoxSodavandMellem;
    }

    public ComboBox<Tilbehør> getComboBox() {
        return comboBox;
    }
    //Metode som gør når textfields bliver klikket på fjerner den alt tekst
    private void textfieldInstalition(){

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
    }

    // metode til at oprette event
    //lavet af alle
    private void opretEvent(){
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

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public void setDatePicker(DatePicker datePicker) {
        this.datePicker = datePicker;
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

    public TextField getKommentar() {
        return kommentar;
    }

    public void setKommentar(TextField kommentar) {
        this.kommentar = kommentar;
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

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    public VBox getTableBox() {
        return tableBox;
    }

    public void setTableBox(VBox tableBox) {
        this.tableBox = tableBox;
    }

    public VBox getNederstVbox() {
        return nederstVbox;
    }

    public void setNederstVbox(VBox nederstVbox) {
        this.nederstVbox = nederstVbox;
    }

    public HBox gethBox() {
        return hBox;
    }

    public void sethBox(HBox hBox) {
        this.hBox = hBox;
    }

    public HBox gethBoxtilføjSletKnap() {
        return hBoxtilføjSletKnap;
    }

    public void sethBoxtilføjSletKnap(HBox hBoxtilføjSletKnap) {
        this.hBoxtilføjSletKnap = hBoxtilføjSletKnap;
    }

    public ObservableList<Tilbehør> getObservableListLille() {
        return observableListLille;
    }

    public void setObservableListLille(ObservableList<Tilbehør> observableListLille) {
        this.observableListLille = observableListLille;
    }

    public ObservableList<Tilbehør> getObservableListMellem() {
        return observableListMellem;
    }

    public void setObservableListMellem(ObservableList<Tilbehør> observableListMellem) {
        this.observableListMellem = observableListMellem;
    }

    public ObservableList<Tilbehør> getObservableListStor() {
        return observableListStor;
    }

    public void setObservableListStor(ObservableList<Tilbehør> observableListStor) {
        this.observableListStor = observableListStor;
    }

    public ObservableList<Tilbehør> getObservableListMad() {
        return observableListMad;
    }

    public void setObservableListMad(ObservableList<Tilbehør> observableListMad) {
        this.observableListMad = observableListMad;
    }

    public HBox gethBoxMenuNr() {
        return hBoxMenuNr;
    }

    public void sethBoxMenuNr(HBox hBoxMenuNr) {
        this.hBoxMenuNr = hBoxMenuNr;
    }

    public VBox getvBoxTilbehør1() {
        return vBoxTilbehør1;
    }

    public void setvBoxTilbehør1(VBox vBoxTilbehør1) {
        this.vBoxTilbehør1 = vBoxTilbehør1;
    }

    public VBox getvBoxTilbehør2() {
        return vBoxTilbehør2;
    }

    public void setvBoxTilbehør2(VBox vBoxTilbehør2) {
        this.vBoxTilbehør2 = vBoxTilbehør2;
    }

    public VBox getvBoxTilbehør3() {
        return vBoxTilbehør3;
    }

    public void setvBoxTilbehør3(VBox vBoxTilbehør3) {
        this.vBoxTilbehør3 = vBoxTilbehør3;
    }

    public HBox gethBoxAlleTilbehør() {
        return hBoxAlleTilbehør;
    }

    public void sethBoxAlleTilbehør(HBox hBoxAlleTilbehør) {
        this.hBoxAlleTilbehør = hBoxAlleTilbehør;
    }

    public VBox getvBoxVælgSodavand() {
        return vBoxVælgSodavand;
    }

    public void setvBoxVælgSodavand(VBox vBoxVælgSodavand) {
        this.vBoxVælgSodavand = vBoxVælgSodavand;
    }

    public VBox getvBoxSodavandKnapper() {
        return vBoxSodavandKnapper;
    }

    public void setvBoxSodavandKnapper(VBox vBoxSodavandKnapper) {
        this.vBoxSodavandKnapper = vBoxSodavandKnapper;
    }

    public HBox gethBoxSodavand() {
        return hBoxSodavand;
    }

    public void sethBoxSodavand(HBox hBoxSodavand) {
        this.hBoxSodavand = hBoxSodavand;
    }

    public VBox getvBoxVenstre() {
        return vBoxVenstre;
    }

    public void setvBoxVenstre(VBox vBoxVenstre) {
        this.vBoxVenstre = vBoxVenstre;
    }

    public HBox gethBoxAfslutSlet() {
        return hBoxAfslutSlet;
    }

    public void sethBoxAfslutSlet(HBox hBoxAfslutSlet) {
        this.hBoxAfslutSlet = hBoxAfslutSlet;
    }

    public VBox getvBoxHøjre() {
        return vBoxHøjre;
    }

    public void setvBoxHøjre(VBox vBoxHøjre) {
        this.vBoxHøjre = vBoxHøjre;
    }

    public HBox gethBoxFullPane() {
        return hBoxFullPane;
    }

    public void sethBoxFullPane(HBox hBoxFullPane) {
        this.hBoxFullPane = hBoxFullPane;
    }

    public void setComboBox(ComboBox comboBox) {
        this.comboBox = comboBox;
    }
}
