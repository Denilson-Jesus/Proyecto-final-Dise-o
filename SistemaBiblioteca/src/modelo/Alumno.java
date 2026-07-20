package modelo;

public class Alumno extends Usuario {

    public Alumno() {
    }

    public Alumno(int id, String nombre, String dni, String correo,
                  String usuario, String password, String rol) {

        super(id, nombre, dni, correo, usuario, password, rol);
    }
}