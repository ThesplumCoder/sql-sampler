package org.tools.sql.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerConnection {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DEFAULT_PROTOCOL = "jdbc:mysql:";
    private static final String CLASS_NAME = ServerConnection.class.getName();
    private static boolean DB_DRIVER_LOADED = false;
    private static String hostname = "localhost";
    private static String port = "3306";
    private Connection con;

    public ServerConnection(String url, String username, String password) {
        if(!DB_DRIVER_LOADED) {
            loadDriver();
        }

        try {
            if (DB_DRIVER_LOADED) {
                con = DriverManager.getConnection(url, username, password);
                con.setTransactionIsolation(8);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    /**
     * Crea una conexión a un servidor de MySQL, usando los parámetros
     * suministrados.
     *
     * @param host Dominio o dirección IPv4 donde se encuentra el servidor.
     * @param user Nombre de usuario para iniciar sesión en el servidor de
     * BBDD.
     * @param password Contraseña del usuario para iniciar sesión en el servidor
     * de BBDD.
     * @param database Nombre de la BD específica sobre la que se realizarán
     * las operaciones.
     */
    public ServerConnection(String host, String user, String password, String database) {
        String dbUrl;

        if(!DB_DRIVER_LOADED) {
            loadDriver();
        }

        dbUrl = ServerConnection.DEFAULT_PROTOCOL + "//" + host + "/" + database;
        try {
            if (DB_DRIVER_LOADED) {
                con = DriverManager.getConnection(dbUrl, user, password);
                con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void loadDriver() {
        if (!DB_DRIVER_LOADED) {
            try {
                Class.forName(DB_DRIVER);
                ServerConnection.DB_DRIVER_LOADED = true;
            } catch (ClassNotFoundException cnfEx) {
                cnfEx.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return (this.con != null)? this.con : null;
    }

}
