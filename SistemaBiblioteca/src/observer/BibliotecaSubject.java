package observer;

import java.util.ArrayList;

public class BibliotecaSubject {

    private ArrayList<Observer> observers =
            new ArrayList<>();

    public void agregarObserver(Observer o) {

        observers.add(o);

    }

    public void notificar() {

        for (Observer o : observers) {

            o.actualizar();

        }

    }

}