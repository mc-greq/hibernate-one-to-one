package example.nio;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

    public static void main(String[] args) {
        // write your code here
        String jdbcUrl = "jdbc:mysql://localhost:3306/hb-01-one-to-one?useSSL=false&serverTimezone=UTC";
        String user = "hbstudent";
        String pass = "hbstudent";

        try{
            System.out.println("Connecting to database: " + jdbcUrl);

            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);

            System.out.println("Connection successful!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}