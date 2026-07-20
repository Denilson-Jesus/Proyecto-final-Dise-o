package strategy;

import java.util.ArrayList;
import modelo.Libro;

public interface BusquedaStrategy {

    ArrayList<Libro> buscar(String dato);

}