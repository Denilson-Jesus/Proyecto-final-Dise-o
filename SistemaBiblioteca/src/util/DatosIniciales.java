package util;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatosIniciales {

    public static void insertarAdmin() {

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String verificar =
                    "SELECT * FROM usuarios WHERE usuario=?";

            PreparedStatement ps =
                    cn.prepareStatement(verificar);

            ps.setString(1, "admin");

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {

            String sql = """
                INSERT INTO usuarios
                (nombre,dni,correo,usuario,password,rol)
                VALUES (?,?,?,?,?,?)
                """;
                PreparedStatement psInsert =
                        cn.prepareStatement(sql);

                psInsert.setString(1, "Administrador");
                psInsert.setString(2, "00000000");
                psInsert.setString(3, "admin@biblioteca.com");
                psInsert.setString(4, "admin");
                psInsert.setString(5, "1234");
                psInsert.setString(6, "ADMINISTRADOR");

                psInsert.executeUpdate();

                System.out.println(
                        "Administrador creado");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}