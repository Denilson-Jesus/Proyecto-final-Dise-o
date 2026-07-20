package strategy;

import java.util.ArrayList;
import modelo.Libro;

public class BusquedaContext {

    private BusquedaStrategy strategy;

    public void setStrategy(BusquedaStrategy strategy) {

        this.strategy = strategy;

    }

    public ArrayList<Libro> buscar(String dato) {

        return strategy.buscar(dato);

    }
}