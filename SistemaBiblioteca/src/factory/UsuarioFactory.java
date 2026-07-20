package factory;

import modelo.Alumno;
import modelo.Docente;
import modelo.Usuario;

public class UsuarioFactory {

    public static Usuario crearUsuario(String tipo) {

        if (tipo.equalsIgnoreCase("ALUMNO")) {

            return new Alumno();

        } else if (tipo.equalsIgnoreCase("DOCENTE")) {

            return new Docente();
        }

        return null;
    }
}