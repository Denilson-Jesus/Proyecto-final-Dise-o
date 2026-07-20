package dao;

import conexion.ConexionBD;
import modelo.Libro;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LibroDAO {

    public boolean registrar(Libro libro) {

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String sql = """
                INSERT INTO libros
                (titulo,autor,categoria,stock)
                VALUES (?,?,?,?)
                """;

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getAutor());
            ps.setString(3, libro.getCategoria());
            ps.setInt(4, libro.getStock());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
    public ArrayList<Libro> listar() {

    ArrayList<Libro> lista = new ArrayList<>();

    try {

        Connection cn =
                ConexionBD.getInstancia().getConexion();

        String sql = "SELECT * FROM libros";

        PreparedStatement ps =
                cn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Libro libro = new Libro();

            libro.setId(rs.getInt("id"));
            libro.setTitulo(rs.getString("titulo"));
            libro.setAutor(rs.getString("autor"));
            libro.setCategoria(rs.getString("categoria"));
            libro.setStock(rs.getInt("stock"));

            lista.add(libro);
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return lista;
    }
    
    public boolean actualizar(
        Libro libro) {

    try {

        Connection cn =
                ConexionBD.getInstancia()
                        .getConexion();

        String sql = """
            UPDATE libros
            SET titulo=?,
                autor=?,
                categoria=?,
                stock=?
            WHERE id=?
            """;

        PreparedStatement ps =
                cn.prepareStatement(sql);

        ps.setString(
                1,
                libro.getTitulo());

        ps.setString(
                2,
                libro.getAutor());

        ps.setString(
                3,
                libro.getCategoria());

        ps.setInt(
                4,
                libro.getStock());

        ps.setInt(
                5,
                libro.getId());

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
    }
    
    public boolean eliminar(int id) {

    try {

        Connection cn =
                ConexionBD.getInstancia()
                        .getConexion();

        String sql =
                "DELETE FROM libros WHERE id=?";

        PreparedStatement ps =
                cn.prepareStatement(sql);

        ps.setInt(1, id);

        return ps.executeUpdate() > 0;

    } catch (Exception e) {

        e.printStackTrace();
    }

    return false;
    }
    
    public ArrayList<Libro> obtenerLibrosDisponibles() {

    ArrayList<Libro> lista = new ArrayList<>();

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String sql = """
                SELECT *
                FROM libros
                WHERE stock > 0
                ORDER BY titulo
                """;

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Libro libro = new Libro();

                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setCategoria(rs.getString("categoria"));
                libro.setStock(rs.getInt("stock"));

                lista.add(libro);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
    
    public Libro buscarPorId(int id) {

            try {

                Connection cn =
                        ConexionBD.getInstancia().getConexion();

                String sql =
                        "SELECT * FROM libros WHERE id=?";

                PreparedStatement ps =
                        cn.prepareStatement(sql);

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    Libro libro = new Libro();

                    libro.setId(rs.getInt("id"));
                    libro.setTitulo(rs.getString("titulo"));
                    libro.setAutor(rs.getString("autor"));
                    libro.setCategoria(rs.getString("categoria"));
                    libro.setStock(rs.getInt("stock"));

                    return libro;
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;
    }
    
    public boolean actualizarStock(int idLibro, int nuevoStock) {

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String sql =
                    "UPDATE libros SET stock=? WHERE id=?";

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ps.setInt(1, nuevoStock);
            ps.setInt(2, idLibro);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
    
    public ArrayList<Libro> buscarPorTitulo(String titulo) {

        ArrayList<Libro> lista = new ArrayList<>();

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String sql =
                    "SELECT * FROM libros WHERE titulo LIKE ?";

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ps.setString(1, "%" + titulo + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Libro libro = new Libro();

                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setCategoria(rs.getString("categoria"));
                libro.setStock(rs.getInt("stock"));

                lista.add(libro);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
    
    public ArrayList<Libro> buscarPorAutor(String autor) {

        ArrayList<Libro> lista = new ArrayList<>();

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String sql =
                    "SELECT * FROM libros WHERE autor LIKE ?";

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ps.setString(1, "%" + autor + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Libro libro = new Libro();

                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setCategoria(rs.getString("categoria"));
                libro.setStock(rs.getInt("stock"));

                lista.add(libro);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
    
    public ArrayList<Libro> buscarPorCategoria(String categoria) {

        ArrayList<Libro> lista = new ArrayList<>();

        try {

            Connection cn =
                    ConexionBD.getInstancia().getConexion();

            String sql =
                    "SELECT * FROM libros WHERE categoria LIKE ?";

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ps.setString(1, "%" + categoria + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Libro libro = new Libro();

                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setCategoria(rs.getString("categoria"));
                libro.setStock(rs.getInt("stock"));

                lista.add(libro);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

}