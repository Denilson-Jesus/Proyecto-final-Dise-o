package main;

import factory.UsuarioFactory;
import modelo.Usuario;

public class PruebaFactory {

    public static void main(String[] args) {

        Usuario u1 =
                UsuarioFactory.crearUsuario("ALUMNO");

        Usuario u2 =
                UsuarioFactory.crearUsuario("DOCENTE");

        System.out.println(
                u1.getClass().getSimpleName());

        System.out.println(
                u2.getClass().getSimpleName());
    }
}