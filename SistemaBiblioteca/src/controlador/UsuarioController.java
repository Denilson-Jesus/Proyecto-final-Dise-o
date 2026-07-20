package controlador;

import dao.UsuarioDAO;
import java.util.ArrayList;
import modelo.Usuario;

public class UsuarioController {

    private UsuarioDAO dao;

    public UsuarioController() {

        dao = new UsuarioDAO();
    }

    public boolean registrarUsuario(
            Usuario usuario) {

        return dao.registrar(usuario);
    }

    public boolean actualizarUsuario(
            Usuario usuario) {

        return dao.actualizar(usuario);
    }

    public boolean eliminarUsuario(
            int id) {

        return dao.eliminar(id);
    }
    public ArrayList<Usuario> listarUsuarios() {

    return dao.listar();
    }
    
    public Usuario buscarPorId(int id) {

    return dao.buscarPorId(id);

    }
    
}