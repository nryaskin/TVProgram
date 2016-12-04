package MyPackage;

/**
 * Created by Никита on 04.12.2016.
 */
import java.awt.*;
import java.io.Console;
import java.sql.*;
import java.util.*;
import java.util.List;

public class SetConnect {
    public static Connection GetConnect() {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "12345");
        } catch (Exception se) {
            System.out.println("Error :" + se.toString());
        }
        return conn;
    }
}
