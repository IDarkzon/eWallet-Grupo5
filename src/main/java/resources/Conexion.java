package resources;

import java.sql.*;

public class Conexion {
    private static final String host = "localhost";
    private static final String port = "3306";
    private static final String user = "root";
    private static final String pass = "root";
    private static final String db = "BDWallet";
    private static final String classDriver = "com.mysql.cj.jdbc.Driver";

    // Creamos una función de conexión
    public Connection getConexion() throws ClassNotFoundException, SQLException {
        // Cargamos el Driver
        Class.forName(classDriver);

        // Instanciamos un String con la dirección de la Base de datos
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?user=" + user + "&password= " + pass;

        // Retornamos el establecimiento de la conexión
        return DriverManager.getConnection(url);
    }

    public void confirmarConexion() {
        Conexion conexion = new Conexion();
        Connection conn = null;
        try {

            conn = conexion.getConexion();

        } catch (SQLException | ClassNotFoundException e) {

            System.out.println("Error: " + e);

        } finally {
            if (conn != null) {
                System.out.println("Conexión Establecida a la Base de Datos: " + db);
            } else {
                System.out.println("Conexión Inexistente...");
            }
        }
    }
}
