package adapter;

import modelo.Usuario;

public class UsuarioAdapter implements UsuarioAdaptador {

    private Usuario usuario;

    public UsuarioAdapter(Usuario usuario) {

        this.usuario = usuario;

    }

    @Override
    public String mostrarInformacion() {

        return "Nombre: " + usuario.getNombre()
                + " | Usuario: " + usuario.getUsuario()
                + " | Rol: " + usuario.getRol();

    }

}