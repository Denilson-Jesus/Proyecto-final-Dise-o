package modelo;

public class Docente extends Usuario {

    public Docente() {
    }

    public Docente(int id, String nombre, String dni, String correo,
                   String usuario, String password, String rol) {

        super(id, nombre, dni, correo, usuario, password, rol);
    }
}