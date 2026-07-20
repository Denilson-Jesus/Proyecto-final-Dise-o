package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instancia;
    private Connection conexion;

    private ConexionBD() {

    try {

        Class.forName("org.sqlite.JDBC");

        conexion = DriverManager.getConnection(
                "jdbc:sqlite:biblioteca.db"
        );

        System.out.println("Conexión exitosa a SQLite");

    } catch (ClassNotFoundException e) {

        System.out.println("No se encontró el driver SQLite");
        e.printStackTrace();

    } catch (SQLException e) {

        System.out.println("Error al conectar");
        e.printStackTrace();
    }
}

    public static ConexionBD getInstancia() {

        if (instancia == null) {
            instancia = new ConexionBD();
        }

        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }
}