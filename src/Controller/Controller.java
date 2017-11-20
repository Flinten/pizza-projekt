package Controller;

import Business.*;
//import Business.Tilbehør.EkstraTilbehørMad.*;
import Business.Produkter.*;
import Database.MySqlConnection;
import Presentation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

//lavet af alle
public class Controller {

    private HovedMenuPane hovedMenuPane = new HovedMenuPane();

    private ObservableList<SeLagerStatus> listSeLagerStatus = FXCollections.observableArrayList();
    private ObservableList<Udbringning> listUdbringning = FXCollections.observableArrayList();
    private ObservableList<Tilbehør> produktList = FXCollections.observableArrayList();
    private ObservableList<Ansat> listAnsat = FXCollections.observableArrayList();
    private ObservableList<Ordre> listHistorik = FXCollections.observableArrayList();

    MySqlConnection mySqlConnection = new MySqlConnection();


    public Controller(Stage primaryStage) {


        // opretter hovedmenu pane
        HBox hBox = new HBox();
        hBox.getChildren().add(hovedMenuPane.getHovedMenuPane());

        //opretter new scene og parameter overfører hbox og sætter størrelse på scene
        Scene scene = new Scene(hBox, 1600, 1000);
        //gør at man ikke kan justere primaryStage
        primaryStage.setResizable(false);
        //tilføjer css stylesheet
        scene.getStylesheets().add("CSS/MyCSS.css");

        primaryStage.setScene(scene);
        primaryStage.show();

        // laver setOnAction på HovedMenu knapper
        hovedMenuPane.getBtnOrdre().setOnAction(event -> {

            orderPane orderPane = new orderPane();

            scene.setRoot(orderPane.getOrderPane(primaryStage, scene, produktList, hovedMenuPane));

            primaryStage.setScene(scene);
        });
        hovedMenuPane.getBtnEvent().setOnAction(event -> {

            EventPane eventPane = new EventPane();

            scene.setRoot(eventPane.getEventPane(primaryStage, scene, listUdbringning, hovedMenuPane));
            primaryStage.setScene(scene);

            eventPane.indsætDataEventTabel(listUdbringning);
        });
        hovedMenuPane.getBtnHistorik().setOnAction(event -> {

            HistorikPane historikPane = new HistorikPane();

            scene.setRoot(historikPane.getHistorikPane(primaryStage, scene, listHistorik, hovedMenuPane));
            primaryStage.setScene(scene);

            historikPane.indsætDataHistorikTabel(listHistorik);

        });
        hovedMenuPane.getBtnAnsat().setOnAction(event -> {

            AnsatPane ansatPane = new AnsatPane();

            scene.setRoot(ansatPane.getAnsatPane(primaryStage, scene, listAnsat, hovedMenuPane));
            primaryStage.setScene(scene);

            ansatPane.indsætDataiAnsatTabel(listAnsat);

        });
        hovedMenuPane.getBtnLagerStatus().setOnAction(event -> {

            LagerPane lagerPane = new LagerPane();

            scene.setRoot(lagerPane.getLagerPane(primaryStage, scene, hovedMenuPane));
            lagerPane.indsætDataLagerTabel(listSeLagerStatus);

            primaryStage.setScene(scene);
        });
        hovedMenuPane.getBtnLogud().setOnAction(event -> {
            System.exit(1);
        });
    }
}