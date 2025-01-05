package org.tools.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import org.tools.sql.connections.ServerConnection;
import org.tools.sql.dml.Select;
import org.tools.sql.dml.From;
import org.tools.sql.dml.Where;

public class App {
    private static Connection con; 

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        con = new ServerConnection("localhost:3306", "root", System.getenv("DB_SQL_PASSWORD"), System.getenv("DB_SQL_DATABASE")).getConnection();

        query4sender();
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
                    data.add(rs.getString(columnName));
                } while (rs.next());
            }
        } catch(SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return data;
    }

    public static String query4() {
        ArrayList<String> parts;
        LinkedHashMap<String,String> filters;

        filters = new LinkedHashMap<>();
        filters.put("IdUsr", "U7123");
        filters.put("IdTaller", "T20");
        filters.put("IdGuionClase", "GU69");

        parts = new ArrayList<>();
        parts.add(Select.build("Nota", "ComentProfe"));
        parts.add(From.build("TR_NotaTallerExamen"));
        parts.add(Where.build(filters));

        return String.join(" ", parts);
    }

    public static void query4sender() {
        String query;

        query = query4();
        try (
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
        ) {
            if (rs.first()) {
                System.out.println("Nota\tComentProfe");
                do {
                    System.out.print(rs.getString("Nota") + "\t");
                    System.out.print(rs.getString("ComentProfe"));
                } while (rs.next());
            }
        } catch(SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
}
