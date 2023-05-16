package resources;

import java.sql.*;

public class Usuario {
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private int id;

    public Usuario(String nombre, String apellido, String correo, String password) throws SQLException, ClassNotFoundException {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;

        insertarUsuario();
        this. id = obtenerID();
    }

    public void insertarUsuario() throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "Insert into Usuario(nombre, apellido, correo, contrasena) values(?,?,?,?)";
        try {
            // Creamos el Prepared Statement y ejecutamos la instrucción
            PreparedStatement ps = conn.prepareStatement(script);
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setString(4, password);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void actualizarUsuario(String nombre, String apellido, String correo, String password) throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "update Usuario set nombre = ?, apellido = ?, correo = ?, password = ? where correo = " + this.correo;

        try {
            // Creamos el Prepared Statement y ejecutamos la instrucción
            PreparedStatement ps = conn.prepareStatement(script);

            // Enviamos los datos
            ps.setString(1, nombre);
            ps.setString(2, apellido);
            ps.setString(3, correo);
            ps.setString(4, password);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
    }

    public void eliminarUsuario() throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "delete from Usuario where correo = " + this.correo;

        // Creamos el Statement y ejecutamos la instrucción
        Statement stmnt = conn.createStatement();
        stmnt.executeUpdate(script);
    }

    public String listarUsuario() throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "select * from Usuario where correo = " + this.correo;

        // Creamos el Statement y ejecutamos la instrucción
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery(script);

        String Resultado = "";

        while (rs.next()) {
            Resultado = "Usuario " + rs.getInt(1) + ": " +
                                     rs.getString(2) + " " +
                                     rs.getString(3) + " " +
                                     rs.getString(4) + " " +
                                     rs.getString(5);
        }

        return Resultado;
    }

    public int obtenerID() throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "select id from Usuario where correo = " + this.correo;

        // Creamos el Statement y ejecutamos la instrucción
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery(script);

        int ID = 0;

        while (rs.next()) {
            ID = rs.getInt(1);
        }

        return ID;
    }

    public String getCorreo() {
        return correo;
    }
}
