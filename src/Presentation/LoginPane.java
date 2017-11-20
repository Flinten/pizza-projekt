package Presentation;

import Database.MySqlConnection;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Created by Ejer on 22-05-2017.
 */

//lavet af kadir
public class LoginPane {

    Label brugerNavn = new Label("Brugernavn");
    Label kodeord = new Label("Kodeord");

    TextField brugerNavnTF = new TextField();
    PasswordField kodeordPF = new PasswordField();

    Button login = new Button("Login");
    Button quit = new Button("Quit");

    VBox vBox = new VBox();

    HBox hBoxKnapper = new HBox();
    HBox hBoxKodeord = new HBox();
    HBox hBoxBrugerNavn = new HBox();

    public LoginPane() {
    }

    public VBox getLoginPane(){

        opbygningAfPane();

        return vBox;
    }

    private void opbygningAfPane(){
        //adder bruger navn textfield og label til hboxBrugerNavn
        hBoxBrugerNavn.setSpacing(10);
        hBoxBrugerNavn.getChildren().addAll(brugerNavn, brugerNavnTF);

        //adder bruger navn textfield og label til hBoxKodeord
        hBoxKodeord.setSpacing(24);
        hBoxKodeord.getChildren().addAll(kodeord, kodeordPF);

        //Adder Login og quit knapper til til hBoxKnapper
        hBoxKnapper.setSpacing(20);
        hBoxKnapper.setPadding(new Insets(0, 0, 0, 70));
        hBoxKnapper.getChildren().addAll(login, quit);

        //Adder hBoxBrugerNavn, hBoxKodeord, hBoxKnapper til vbox som bliver retuneret
        vBox.getChildren().addAll(hBoxBrugerNavn, hBoxKodeord, hBoxKnapper);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(200, 0, 10, 250));
    }
/*
    private void loginPaneKnapper(){

        login.setOnAction(event -> {

            brugerNavn
            checkLogin();
        });
        quit.setOnAction(event -> {
            System.exit(1);
        });

    }
*/
    private boolean checkLogin(int login, String kode){
        MySqlConnection mySqlConnection = new MySqlConnection();

        HashMap<Integer,String> hashMap = new HashMap<>();

        String query = "SELECT ansat_Login_Id, ansat_Login_Kodeord FROM table_Ansat";
        Statement st = null;

        try {
            st = mySqlConnection.connect().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                int ansatLoginId = rs.getInt(2);
                String ansatKode = rs.getString(3);

                hashMap.put(ansatLoginId,ansatKode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(hashMap.get(login).equals(kode)){
            return true;
        }
        else return false;
    }

    public Label getBrugerNavn() {
        return brugerNavn;
    }

    public void setBrugerNavn(Label brugerNavn) {
        this.brugerNavn = brugerNavn;
    }

    public TextField getBrugerNavnTF() {
        return brugerNavnTF;
    }

    public void setBrugerNavnTF(TextField brugerNavnTF) {
        this.brugerNavnTF = brugerNavnTF;
    }

    public Label getKodeord() {
        return kodeord;
    }

    public void setKodeord(Label kodeord) {
        this.kodeord = kodeord;
    }

    public PasswordField getKodeordPF() {
        return kodeordPF;
    }

    public void setKodeordPF(PasswordField kodeordPF) {
        this.kodeordPF = kodeordPF;
    }

    public Button getLogin() {
        return login;
    }

    public void setLogin(Button login) {
        this.login = login;
    }

    public Button getQuit() {
        return quit;
    }

    public void setQuit(Button quit) {
        this.quit = quit;
    }
}
