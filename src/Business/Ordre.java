package Business;

import Database.MySqlConnection;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Ejer on 23-05-2017.
 */
public class Ordre {
    private int orderId;
    private String dato;
    private double pris;

    public Ordre(String dato, double pris) {
        this.dato = dato;
        this.pris = pris;
        indsætOrderTilDatabase(dato,pris);
    }

    public Ordre(int orderId, String dato, double pris) {
        this.orderId = orderId;
        this.dato = dato;
        this.pris = pris;
    }

    private void indsætOrderTilDatabase(String dato, double pris){
        //metode lavet af casper
        MySqlConnection mySqlConnection = new MySqlConnection();
        try {
            Statement st = mySqlConnection.connect().createStatement();
            st.executeUpdate("INSERT INTO table_Order_Historik(order_Date,order_price)"+
                    "Values('"+dato+"','"+pris+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }
}
