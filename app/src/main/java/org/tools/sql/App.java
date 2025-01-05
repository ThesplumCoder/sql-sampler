package org.tools.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.tools.sql.connections.ServerConnection;

public class App {
    private static Connection con; 

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        con = new ServerConnection("localhost:3307", "root", System.getenv("DB_SQL_PASSWORD"), "diamante").getConnection();
        retrieveData("TB_Areas", "NomArea");
    }

    public static List<String> retrieveData(String tableName, String columnName) {
        ArrayList<String> data;
        String query;

        data = new ArrayList<>();
        query = "SELECT " + columnName + " FROM " + tableName;
        try (
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
        ) {
            if (rs.first()) {
                do {
                    System.out.println(rs.getString(columnName));
                } while (rs.next());
            }
        } catch(SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return data;
    }
}
