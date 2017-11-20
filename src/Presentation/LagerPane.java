package Presentation;


import Business.SeLagerStatus;
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
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ejer on 23-05-2017.
 */

//lavet af alle

public class LagerPane {
    //opretter tableview
    TableView tableView = new TableView();
    //opretter Label


    Button buttonTilbage = new Button("Tilbage til hovedmenu");
    Button buttonAfslut = new Button("Log ud");
    //opretter kolonner
    TableColumn<SeLagerStatus,String> navnCol = new TableColumn("Sodavand navn");
    TableColumn<SeLagerStatus,String> nuværendeBeholdningCol = new TableColumn("Sodavand forventet beholdning");
    TableColumn<SeLagerStatus,String> optimalBeholdningCol = new TableColumn("Sodavand nuværende beholdning");

    //hbox til tilbage og afslut knap
    HBox hBoxKnapper = new HBox();

    BorderPane borderPane = new BorderPane();

    public LagerPane() {
    }


    public BorderPane getLagerPane(Stage primaryStage, Scene scene,  HovedMenuPane hovedMenuPane){
        //kalder metode setTableView()
        setTableView();
        //kalder metode opbygningAfPane();
        opbygningAfPane();

        lagerPaneKnapper(scene,primaryStage,hovedMenuPane);

        return borderPane;
    }
    // eventhandler til buttontilbage
    private void lagerPaneKnapper(Scene scene, Stage primaryStage, HovedMenuPane hovedMenuPane){
        getButtonTilbage().setOnAction(event -> {
            scene.setRoot(hovedMenuPane.getHovedMenuPane());
            primaryStage.setScene(scene);
        });
        getButtonAfslut().setOnAction(event -> {
            System.exit(1);
        });
    }

    public void indsætDataLagerTabel(ObservableList listSeLagerStatus){
        //fjerner alt fra listen og genindsætter
        listSeLagerStatus.remove(0, listSeLagerStatus.size());

        indsætDataLagerList(listSeLagerStatus);
        //indsætter listSeLagerStatus i table
        getTableView().setItems(listSeLagerStatus);
    }

    //metode lavet af casper indsætDataLagerList
    private void indsætDataLagerList(ObservableList listSeLagerStatus){
        // query til databasen
        String query = "SELECT"+" table_Produkt.produkt_Navn, table_Lager.lager_Nuværende_Beholdning, table_Lager.lager_Optimal_beholdning\n" +
                "FROM table_Produkt\n" +
                "INNER JOIN table_Lager ON table_Produkt.produkt_Id =table_Lager.lager_id";

        MySqlConnection mySqlConnection = new MySqlConnection();

        try {
            Statement st = mySqlConnection.connect().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                String navn = rs.getString(1);
                int nuværendeBeholdning = rs.getInt(2);
                int optilmalBeholdning = rs.getInt(3);
                listSeLagerStatus.add(new SeLagerStatus(navn,nuværendeBeholdning,optilmalBeholdning));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void opbygningAfPane(){

        // tilhøjer tilbage og afslut knap til hboxKnapper
        buttonAfslut.setId("sletknap");
        buttonTilbage.setId("tilbage");
        hBoxKnapper.setSpacing(20);
        hBoxKnapper.getChildren().addAll(buttonTilbage,buttonAfslut);
        hBoxKnapper.setPadding(new Insets(10));
        //tilføjer til borderPane som bliver returnet i metoden getLagerPane()
        borderPane.setPadding(new Insets(100));

        borderPane.setCenter(tableView);
        borderPane.setBottom(hBoxKnapper);
    }

    private void setTableView(){

        //størrelse på tableview
        tableView.setPrefSize(740,300);

        //Fortæller hvad hver kolonne skal have af værdier
        navnCol.setCellValueFactory(new PropertyValueFactory<SeLagerStatus, String>("navn"));
        nuværendeBeholdningCol.setCellValueFactory(new PropertyValueFactory<SeLagerStatus, String>("nuværendeBeholdning"));
        optimalBeholdningCol.setCellValueFactory(new PropertyValueFactory<SeLagerStatus, String>("optimalBeholdning"));

        //indstiller længden på kolonnerne i tableview
        navnCol.setPrefWidth(220);
        nuværendeBeholdningCol.setPrefWidth(260);
        optimalBeholdningCol.setPrefWidth(260);

        //adder kolonnerne til tableview
        tableView.getColumns().addAll(navnCol,nuværendeBeholdningCol,optimalBeholdningCol);

    }

    public TableView getTableView() {
        return tableView;
    }

    public void setTableView(TableView tableView) {
        this.tableView = tableView;
    }


    public Button getButtonTilbage() {
        return buttonTilbage;
    }

    public void setButtonTilbage(Button buttonTilbage) {
        this.buttonTilbage = buttonTilbage;
    }

    public Button getButtonAfslut() {
        return buttonAfslut;
    }

    public void setButtonAfslut(Button buttonAfslut) {
        this.buttonAfslut = buttonAfslut;
    }

    public TableColumn getNavnCol() {
        return navnCol;
    }

    public void setNavnCol(TableColumn navnCol) {
        this.navnCol = navnCol;
    }

    public TableColumn getNuværendeBeholdningCol() {
        return nuværendeBeholdningCol;
    }

    public void setNuværendeBeholdningCol(TableColumn nuværendeBeholdningCol) {
        this.nuværendeBeholdningCol = nuværendeBeholdningCol;
    }

    public TableColumn getForventetBeholdningCol() {
        return optimalBeholdningCol;
    }

    public void setForventetBeholdningCol(TableColumn forventetBeholdningCol) {
        this.optimalBeholdningCol = forventetBeholdningCol;
    }
}
