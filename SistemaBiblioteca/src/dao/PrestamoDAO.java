package dao;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import modelo.Prestamo;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PrestamoDAO {

    public boolean registrar(Prestamo prestamo) {

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String sql = """
                INSERT INTO prestamos
                (id_usuario,id_libro,fecha_prestamo,fecha_devolucion,estado)
                VALUES (?,?,?,?,?)
                """;

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ps.setInt(1, prestamo.getIdUsuario());
            ps.setInt(2, prestamo.getIdLibro());
            ps.setString(3, prestamo.getFechaPrestamo());
            ps.setString(4, prestamo.getFechaDevolucion());
            ps.setString(5, prestamo.getEstado());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
    
    public ArrayList<Prestamo> listar() {

        ArrayList<Prestamo> lista = new ArrayList<>();

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String sql = "SELECT * FROM prestamos";

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Prestamo prestamo = new Prestamo();

                prestamo.setId(rs.getInt("id"));
                prestamo.setIdUsuario(rs.getInt("id_usuario"));
                prestamo.setIdLibro(rs.getInt("id_libro"));
                prestamo.setFechaPrestamo(rs.getString("fecha_prestamo"));
                prestamo.setFechaDevolucion(rs.getString("fecha_devolucion"));
                prestamo.setEstado(rs.getString("estado"));

                lista.add(prestamo);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
    
    public boolean devolver(int idPrestamo) {

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String sql = """
                UPDATE prestamos
                SET estado='DEVUELTO'
                WHERE id=?
                """;

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ps.setInt(1, idPrestamo);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
    
    public Prestamo buscarPorId(int id) {

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String sql =
                    "SELECT * FROM prestamos WHERE id=?";

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                Prestamo p = new Prestamo();

                p.setId(rs.getInt("id"));
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setIdLibro(rs.getInt("id_libro"));
                p.setFechaPrestamo(rs.getString("fecha_prestamo"));
                p.setFechaDevolucion(rs.getString("fecha_devolucion"));
                p.setEstado(rs.getString("estado"));

                return p;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
    
}