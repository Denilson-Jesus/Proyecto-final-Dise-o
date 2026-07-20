package controlador;

import dao.LibroDAO;
import modelo.Libro;

public class LibroController {

    private LibroDAO dao;

    public LibroController() {

        dao = new LibroDAO();
    }

    public boolean registrarLibro(
            String titulo,
            String autor,
            String categoria,
            int stock) {

        Libro libro = new Libro();

        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setCategoria(categoria);
        libro.setStock(stock);

        return dao.registrar(libro);
    }
    
    public boolean registrarLibro(Libro libro) {

    return dao.registrar(libro);

    }

    public boolean actualizarLibro(
            int id,
            String titulo,
            String autor,
            String categoria,
            int stock) {

        Libro libro = new Libro();

        libro.setId(id);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setCategoria(categoria);
        libro.setStock(stock);

        return dao.actualizar(libro);
    }

    public boolean eliminarLibro(int id) {

        return dao.eliminar(id);
    }
    
    public java.util.ArrayList<Libro> listarLibros() {

    return dao.listar();

    }
    
    public Libro buscarPorId(int id) {

    return dao.buscarPorId(id);

    }
    
    public boolean actualizarStock(
        int idLibro,
        int stock) {

    return dao.actualizarStock(
            idLibro,
            stock);

    }
    
}