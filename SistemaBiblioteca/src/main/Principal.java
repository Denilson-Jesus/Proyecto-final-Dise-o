package main;

import util.CrearTablas;
import util.DatosIniciales;
import vista.FrmLogin;
import vista.FrmUsuarios;

public class Principal {

    public static void main(String[] args) {

        CrearTablas.crear();
        DatosIniciales.insertarAdmin();
        new FrmUsuarios();
        new FrmLogin();
    }
}

