package dao;

import conexion.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Usuario;

public class UsuarioDAO {

    public boolean validarLogin(String usuario,
                                String password) {

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String sql = """
                SELECT *
                FROM usuarios
                WHERE usuario=?
                AND password=?
                """;

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
    
    public boolean registrar(Usuario usuario) {

    try {

        Connection cn =
                ConexionBD.getInstancia().getConexion();

        String sql = """
            INSERT INTO usuarios
            (nombre,dni,correo,usuario,password,rol)
            VALUES (?,?,?,?,?,?)
            """;

        PreparedStatement ps =
                cn.prepareStatement(sql);

        ps.setString(1, usuario.getNombre());
        ps.setString(2, usuario.getDni());
        ps.setString(3, usuario.getCorreo());
        ps.setString(4, usuario.getUsuario());
        ps.setString(5, usuario.getPassword());
        ps.setString(6, usuario.getRol());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
    }
    
    public ArrayList<Usuario> listar() {

    ArrayList<Usuario> lista =
            new ArrayList<>();

    try {

        Connection cn =
                ConexionBD.getInstancia().getConexion();

        String sql =
                "SELECT * FROM usuarios";

        PreparedStatement ps =
                cn.prepareStatement(sql);

        ResultSet rs =
                ps.executeQuery();

        while (rs.next()) {

            Usuario usuario;

                if (rs.getString("rol").equals("DOCENTE")) {

                    usuario = new modelo.Docente();

                } else {

                    usuario = new modelo.Alumno();
                }

            usuario.setId(
                    rs.getInt("id"));

            usuario.setNombre(
                    rs.getString("nombre"));

            usuario.setDni(
                    rs.getString("dni"));

            usuario.setCorreo(
                    rs.getString("correo"));

            usuario.setUsuario(
                    rs.getString("usuario"));

            usuario.setPassword(
                    rs.getString("password"));

            usuario.setRol(
                    rs.getString("rol"));

            lista.add(usuario);
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return lista;
    }
    
    public boolean actualizar(
        Usuario usuario) {

    try {

        Connection cn =
                ConexionBD.getInstancia().getConexion();

        String sql = """
            UPDATE usuarios
            SET nombre=?,
                dni=?,
                correo=?,
                usuario=?,
                password=?,
                rol=?
            WHERE id=?
            """;

        PreparedStatement ps =
                cn.prepareStatement(sql);

        ps.setString(1,
                usuario.getNombre());

        ps.setString(2,
                usuario.getDni());

        ps.setString(3,
                usuario.getCorreo());

        ps.setString(4,
                usuario.getUsuario());

        ps.setString(5,
                usuario.getPassword());

        ps.setString(6,
                usuario.getRol());

        ps.setInt(7,
                usuario.getId());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
    }
    
    public boolean eliminar(int id) {

    try {

        Connection cn =
                ConexionBD.getInstancia().getConexion();

        String sql =
                "DELETE FROM usuarios WHERE id=?";

        PreparedStatement ps =
                cn.prepareStatement(sql);

        ps.setInt(1, id);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
    }
    
    public Usuario buscarPorId(int id) {

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String sql =
                    "SELECT * FROM usuarios WHERE id=?";

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Usuario usuario;

                if (rs.getString("rol").equals("DOCENTE")) {

                    usuario = new modelo.Docente();

                } else {

                    usuario = new modelo.Alumno();
                }

                usuario.setId(rs.getInt("id"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setDni(rs.getString("dni"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));

                return usuario;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
    
    
    
}