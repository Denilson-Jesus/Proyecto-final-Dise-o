package strategy;

import dao.LibroDAO;
import java.util.ArrayList;
import modelo.Libro;

public class BusquedaPorTitulo implements BusquedaStrategy {

    private LibroDAO dao = new LibroDAO();

    @Override
    public ArrayList<Libro> buscar(String dato) {

        return dao.buscarPorTitulo(dato);

    }
}