package util;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.Statement;

public class CrearTablas {

    public static void crear() {

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            Statement st = cn.createStatement();

            st.execute("""
                CREATE TABLE IF NOT EXISTS usuarios(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                dni TEXT NOT NULL,
                correo TEXT NOT NULL,
                usuario TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL,
                rol TEXT NOT NULL
                 )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS libros(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    titulo TEXT NOT NULL,
                    autor TEXT NOT NULL,
                    categoria TEXT NOT NULL,
                    stock INTEGER NOT NULL
                )
            """);

            st.execute("""
                CREATE TABLE IF NOT EXISTS prestamos(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    id_usuario INTEGER NOT NULL,
                    id_libro INTEGER NOT NULL,
                    fecha_prestamo TEXT NOT NULL,
                    fecha_devolucion TEXT,
                    estado TEXT NOT NULL,

                    FOREIGN KEY(id_usuario)
                        REFERENCES usuarios(id),

                    FOREIGN KEY(id_libro)
                        REFERENCES libros(id)
                )
            """);

            System.out.println("Tablas creadas correctamente");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}