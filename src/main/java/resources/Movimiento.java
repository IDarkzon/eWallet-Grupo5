package resources;

import java.sql.*;

public class Movimiento {
    private double monto;
    private String fechaMovimiento;
    private byte tipo;
    private int id;

    public Movimiento(double monto, String fechaMovimiento, byte tipo, Tarjeta card) throws SQLException, ClassNotFoundException {
        this.monto = monto;
        this.fechaMovimiento = fechaMovimiento;
        this.tipo = tipo;

        insertarMovimiento(card);
        this.id = obtenerID(card);
    }

    public void insertarMovimiento(Tarjeta card) throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "Insert into Movimiento(monto, fechamovimiento, tipo, idTarjeta) values(?,?,?,?)";
        try {
            // Creamos el Prepared Statement y ejecutamos la instrucción
            PreparedStatement ps = conn.prepareStatement(script);
            ps.setDouble(1, monto);
            ps.setString(2, fechaMovimiento);
            ps.setByte(3, tipo);
            ps.setInt(4, card.obtenerID());

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void actualizarMovimiento(double monto, String fecha, byte tipo) throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "update Movimiento set monto = ?, fechamovimiento = ?, tipo = ? where id= " + this.id;
        try {
            // Creamos el Prepared Statement y ejecutamos la instrucción
            PreparedStatement ps = conn.prepareStatement(script);
            ps.setDouble(1, monto);
            ps.setString(2, fecha);
            ps.setByte(3, tipo);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        this.monto = monto;
        this.fechaMovimiento = fecha;
        this.tipo = tipo;
    }

    public void eliminarMovimiento(String tarjeta) throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "delete from Movimiento where id = " + this.id;

        // Creamos el Statement y ejecutamos la instrucción
        Statement stmnt = conn.createStatement();
        stmnt.executeUpdate(script);
    }

    public String listarMovimiento(String tarjeta) throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "select * from Movimiento where id = " + this.id;

        // Creamos el Statement y ejecutamos la instrucción
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery(script);

        String Resultado = "";

        while (rs.next()) {
            Resultado = "Movimiento " + rs.getInt(1) + ": " +
                    rs.getDouble(2) + " " +
                    rs.getString(3) + " " +
                    rs.getByte(4) + " " +
                    rs.getInt(5);
        }

        return Resultado;
    }

    public int obtenerID(Tarjeta card) throws SQLException, ClassNotFoundException {
        // Creamos el objeto de la clase Conexion
        Conexion conexion = new Conexion();

        // Creamos el objeto Connection e insanciamos con el metodo getConexion
        Connection conn = conexion.getConexion();

        // Creamos un String cuyo valor sea la consulta
        String script = "select id from Movimiento where idTarjeta = " + card.obtenerID();

        // Creamos el Statement y ejecutamos la instrucción
        Statement stmnt = conn.createStatement();
        ResultSet rs = stmnt.executeQuery(script);

        int ID = 0;

        while (rs.next()) {
            ID = rs.getInt(1);
        }

        return ID;
    }
}
