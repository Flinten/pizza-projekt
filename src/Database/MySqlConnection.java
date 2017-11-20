package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Ejer on 22-05-2017.
 */

    //MysqlConnection source: https://stackoverflow.com/questions/2839321/connect-java-to-a-mysql-database
    //Hele denne klasse er fundet på intetnettet på linkede ovenfor
public class MySqlConnection {

        // init database constants
        private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
        private static final String DATABASE_URL = "jdbc:mysql://rds-mysql-pizza.czhqd4ycpbyt.us-east-1.rds.amazonaws.com:3306/pizza";
        private static final String USERNAME = "pizza";
        private static final String PASSWORD = "rasmus12";
        private static final String MAX_POOL = "250";
/*
        private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
        private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/Pizza";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "rasmus12";
        private static final String MAX_POOL = "250";
*/
        // init connection object
        private Connection connection;
        // init properties object
        private Properties properties;

        // create properties
        private Properties getProperties() {
            if (properties == null) {
                properties = new Properties();
                properties.setProperty("user", USERNAME);
                properties.setProperty("password", PASSWORD);
                properties.setProperty("MaxPooledStatements", MAX_POOL);
            }
            return properties;
        }

        // connect database
        public Connection connect() {
            if (connection == null) {
                try {
                    Class.forName(DATABASE_DRIVER);
                    connection = DriverManager.getConnection(DATABASE_URL, getProperties());
                    //System.out.println("connection okay");
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
            return connection;
        }

        // disconnect database
        public void disconnect() {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

}
