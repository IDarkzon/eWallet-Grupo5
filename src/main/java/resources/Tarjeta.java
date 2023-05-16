package resources;

import java.sql.*;

public class Tarjeta {
    private String numero;
    private int saldo;
    private String fechaVencimiento;
    private byte tipo;
    private byte proveedor;
    private int id;

    public Tarjeta(String numero, int saldo, String fechaVencimiento, byte tipo, byte proveedor, Usuario user) throws SQLException, ClassNotFoundException {
        this.numero = numero;
        this.saldo = saldo;
        this.fechaVencimiento = fechaVencimiento;
        this.tipo = tipo;
        this.proveedor = proveedor;

        insertarTarjeta(user);
        this.id = obtenerID();
    }

    public void insertarTarjeta(Usuario user) throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "Insert into Tarjeta(numero, saldo, fechavencimiento, tipo, proveedor, idUsuario) values(?,?,?,?,?,?)";
        try {
            // Creamos el Prepared Statement y ejecutamos la instrucción
            PreparedStatement ps = conn.prepareStatement(script);
            ps.setString(1, numero);
            ps.setInt(2, saldo);
            ps.setString(3, fechaVencimiento);
            ps.setByte(4, tipo);
            ps.setByte(5, proveedor);
            ps.setInt(6, user.obtenerID());

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void actualizarTarjeta(int saldo, String fechaVencimiento, byte tipo, byte proveedor) throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "update Tarjeta set saldo = ?, fechavencimiento = ?, tipo = ?, proveedor = ? where numero =" + this.numero;
        try {
            // Creamos el Prepared Statement y ejecutamos la instrucción
            PreparedStatement ps = conn.prepareStatement(script);
            ps.setInt(1, saldo);
            ps.setString(2, fechaVencimiento);
            ps.setByte(3, tipo);
            ps.setByte(4, proveedor);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        this.saldo = saldo;
        this.fechaVencimiento = fechaVencimiento;
        this.tipo = tipo;
        this.proveedor = proveedor;
    }

    public void eliminarTarjeta() throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "delete from Tarjeta where numero = " + this.numero;

        // Creamos el Statement y ejecutamos la instrucción
        Statement stmnt = conn.createStatement();
        stmnt.executeUpdate(script);
    }

    public String listarTarjeta() throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "select * from Tarjeta where numero = " + this.numero;

        // Creamos el Statement y ejecutamos la instrucción
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery(script);

        String Resultado = "";

        while (rs.next()) {
            Resultado = "Tarjeta " + rs.getInt(1) + ": " +
                    rs.getString(2) + " " +
                    rs.getInt(3) + " " +
                    rs.getString(4) + " " +
                    rs.getByte(5) + " " +
                    rs.getByte(6) + " " +
                    rs.getInt(7);
        }

        return Resultado;
    }

    public int obtenerID() throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "select id from Tarjeta where numero = " + this.numero;

        // Creamos el Statement y ejecutamos la instrucción
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery(script);

        int ID = 0;

        while (rs.next()) {
            ID = rs.getInt(1);
        }

        return ID;
    }

    public String getNumero() {
        return numero;
    }
}
