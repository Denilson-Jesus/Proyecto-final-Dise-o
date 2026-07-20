package controlador;

import dao.PrestamoDAO;
import java.util.ArrayList;
import modelo.Prestamo;

public class PrestamoController {

    private PrestamoDAO dao;

    public PrestamoController() {

        dao = new PrestamoDAO();
    }

    public boolean registrarPrestamo(
            Prestamo prestamo) {

        return dao.registrar(prestamo);
    }
    
    public ArrayList<Prestamo> listarPrestamos() {

        return dao.listar();

    }
    
    public boolean devolverPrestamo(int idPrestamo) {

        return dao.devolver(idPrestamo);

    }
    
    public Prestamo buscarPorId(int id) {

        return dao.buscarPorId(id);

    }
    
}