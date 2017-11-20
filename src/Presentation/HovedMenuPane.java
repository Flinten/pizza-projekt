package Presentation;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Created by Ejer on 22-05-2017.
 */

//lavet af alle

public class HovedMenuPane {

    Label overskrift = new Label("");

    //Opretter knapper
    Button btnOrdre = new Button("Opret ordre");
    Button btnEvent = new Button("Opret event");
    Button btnHistorik = new Button("Ordrehistorik");
    Button btnAnsat = new Button("Ansatte");
    Button btnLagerStatus = new Button("Se lager status");
    Button btnLogud = new Button("Log ud");

    public BorderPane getHovedMenuPane(){
        indstilStørrelser();
        //Opretter VBox og sætter den til venstre
        VBox left = new VBox();
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(left);
        borderPane.setPadding(new Insets(20));
        left.setSpacing(70);

        //Sætter label & knapper ind i VBox "left"
        left.getChildren().addAll(overskrift, btnOrdre, btnEvent, btnHistorik, btnAnsat,btnLagerStatus,btnLogud);

        borderPane.setPadding(new Insets(0,0,50,150));
        return borderPane;

    }// sætter størrelse på kanpperne
    private void indstilStørrelser(){
        overskrift.setPrefSize(100,50);
        //Størrelse på knapper
        btnOrdre.setPrefSize(200,100);
        btnEvent.setPrefSize(200,100);
        btnHistorik.setPrefSize(200,100);
        btnAnsat.setPrefSize(200,100);
        btnLagerStatus.setPrefSize(200,100);
        btnLogud.setPrefSize(200,100);

        btnLogud.setId("sletknap");
    }

    public Label getOverskrift() {
        return overskrift;
    }

    public void setOverskrift(Label overskrift) {
        this.overskrift = overskrift;
    }

    public Button getBtnOrdre() {
        return btnOrdre;
    }

    public void setBtnOrdre(Button btnOrdre) {
        this.btnOrdre = btnOrdre;
    }

    public Button getBtnEvent() {
        return btnEvent;
    }

    public void setBtnEvent(Button btnEvent) {
        this.btnEvent = btnEvent;
    }

    public Button getBtnHistorik() {
        return btnHistorik;
    }

    public void setBtnHistorik(Button btnHistorik) {
        this.btnHistorik = btnHistorik;
    }

    public Button getBtnAnsat() {
        return btnAnsat;
    }

    public void setBtnAnsat(Button btnAnsat) {
        this.btnAnsat = btnAnsat;
    }

    public Button getBtnLogud() {
        return btnLogud;
    }

    public void setBtnLogud(Button btnLogud) {
        this.btnLogud = btnLogud;
    }

    public Button getBtnLagerStatus() {
        return btnLagerStatus;
    }

    public void setBtnLagerStatus(Button btnLagerStatus) {
        this.btnLagerStatus = btnLagerStatus;
    }
}
