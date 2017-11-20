package Presentation;

import Business.Ordre;
import Database.MySqlConnection;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Ejer on 22-05-2017.
 */
public class HistorikPane {

    Label label = new Label("Her kan du se ordrehistorik");
    Button tilbage = new Button("Tilbage til hovedmenu");
    Button logud = new Button("Log ud");

    TableColumn ordrenrCol = new TableColumn("Ordre nr.");
    TableColumn prisCol = new TableColumn("Pris");
    TableColumn datoCol = new TableColumn("Dato");

    TableView table = new TableView();

    //Vbox til overskrift og tabellen
    VBox vbox = new VBox();

    //Opretter vbox til tabel
    VBox tableBox = new VBox();

    //Borderpane til at få tabellen og textfields over logud og tilabge knapper
    BorderPane ydersteBorderPane = new BorderPane();

    //Borderpane til vbox fra tabel og til hbox fra textfields
    BorderPane borderPane = new BorderPane();

    //hbox til tilbage og log ud knapperne
    HBox nederstHbox = new HBox();

    public HistorikPane() {
    }
    public BorderPane getHistorikPane(Stage primaryStage, Scene scene, ObservableList listEvent, HovedMenuPane hovedMenuPane){

        opbygningAfPane();
        tableViewInstalition();
        historkPaneKnapper(scene,primaryStage,hovedMenuPane);

        return ydersteBorderPane;
    }

    private void historkPaneKnapper(Scene scene, Stage primaryStage, HovedMenuPane hovedMenuPane){
        // eventhandler til tilbage knappen
        getTilbage().setOnAction(event -> {
            scene.setRoot(hovedMenuPane.getHovedMenuPane());
            primaryStage.setScene(scene);
        });
        //evenethandler til log ud knappen
        getLogud().setOnAction(event -> {
            System.exit(1);
        });
    }

    public void indsætDataHistorikTabel(ObservableList<Ordre> list){
        list.remove(0,list.size());
        indsætTilHistorikList(list);
        getTable().setItems(list);
    }

    //indsætTilhistorikList metode lavet af Casper
    private void indsætTilHistorikList(ObservableList<Ordre> list){
        // query til databasen
        String query = "SELECT * FROM table_Order_Historik";

        MySqlConnection mySqlConnection = new MySqlConnection();

        try {
            Statement st = mySqlConnection.connect().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                int orderId = rs.getInt(1);
                String orderDate = rs.getString(2);
                double orderPrice = rs.getDouble(3);
                Ordre ordre = new Ordre(orderId,orderDate,orderPrice);

                list.add(ordre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void opbygningAfPane(){

        //Opretter vbox til tabel
        tableBox.setSpacing(10);
        tableBox.setPadding(new Insets(20));
        tableBox.getChildren().addAll(table);

        //Vbox til overskrift og tabellen
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(1));
        vbox.getChildren().addAll(label, table);

        //hbox til tilbage og log ud knapperne
        nederstHbox.setSpacing(5);
        nederstHbox.setPadding(new Insets(10));
        nederstHbox.getChildren().addAll(tilbage, logud);

        //Borderpane til vbox fra tabel og til hbox fra textfields
        borderPane.setTop(vbox);

        //Borderpane til at få tabellen og textfields over logud og tilabge knapper
        ydersteBorderPane.setTop(borderPane);
        ydersteBorderPane.setBottom(nederstHbox);
        ydersteBorderPane.setPadding(new Insets(50));
    }
    //indstiller tableview
    private void tableViewInstalition(){

        //Fortæller hvad hver kolonne skal have af værdier
        ordrenrCol.setCellValueFactory(new PropertyValueFactory<Ordre,Integer>("orderId"));
        datoCol.setCellValueFactory(new PropertyValueFactory<Ordre, String>("dato"));
        prisCol.setCellValueFactory(new PropertyValueFactory<Ordre,Double>("pris"));

        ordrenrCol.setPrefWidth(500);
        datoCol.setPrefWidth(500);
        prisCol.setPrefWidth(500);

        table.setPrefSize(700,800);
        //adder kolonner til tableview
        table.getColumns().addAll(ordrenrCol, prisCol, datoCol);

        tilbage.setId("tilbage");
        logud.setId("sletknap");
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
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

    public TableView getTable() {
        return table;
    }

    public void setTable(TableView table) {
        this.table = table;
    }
}
