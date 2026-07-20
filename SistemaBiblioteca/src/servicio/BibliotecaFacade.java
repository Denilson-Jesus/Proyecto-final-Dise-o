package servicio;

import controlador.LibroController;
import controlador.PrestamoController;
import controlador.UsuarioController;
import strategy.BusquedaContext;
import strategy.BusquedaStrategy;

public class BibliotecaFacade {

    private observer.BibliotecaSubject subject = new observer.BibliotecaSubject();
    private LibroController libroController;
    private UsuarioController usuarioController;
    private PrestamoController prestamoController;
    private static BibliotecaFacade instancia;
    private BusquedaContext contextoBusqueda = new BusquedaContext();
    private BibliotecaFacade() {

        libroController = new LibroController();
        usuarioController = new UsuarioController();
        prestamoController = new PrestamoController();
        
    }
    public static BibliotecaFacade getInstancia() {

        if (instancia == null) {

            instancia = new BibliotecaFacade();

        }

        return instancia;

    }
    
    public boolean registrarUsuario(modelo.Usuario usuario) {

    return usuarioController.registrarUsuario(usuario);

    }

    public boolean registrarLibro(modelo.Libro libro) {

    return libroController.registrarLibro(libro);


    }
    
    public java.util.ArrayList<modelo.Usuario> listarUsuarios() {

    return usuarioController.listarUsuarios();

    }
    
    public java.util.ArrayList<modelo.Libro> listarLibros() {

    return libroController.listarLibros();

    }
    
    public boolean registrarPrestamo(
            int idUsuario,
            int idLibro,
            String fechaPrestamo,
            String fechaDevolucion) {

        modelo.Usuario usuario =
                usuarioController.buscarPorId(idUsuario);

        if (usuario == null) {

            return false;
        }

        modelo.Libro libro =
                libroController.buscarPorId(idLibro);

        if (libro == null) {

            return false;
        }

        if (libro.getStock() <= 0) {

            return false;
        }

        modelo.Prestamo prestamo =
                new modelo.Prestamo();

        prestamo.setIdUsuario(idUsuario);
        prestamo.setIdLibro(idLibro);
        prestamo.setFechaPrestamo(fechaPrestamo);
        prestamo.setFechaDevolucion(fechaDevolucion);
        prestamo.setEstado("PRESTADO");

        if (prestamoController.registrarPrestamo(prestamo)) {

            libroController.actualizarStock(
                    idLibro,
                    libro.getStock() - 1);
            subject.notificar();

            return true;
        }

        return false;
    }
    
    public java.util.ArrayList<modelo.Prestamo> listarPrestamos() {

    return prestamoController.listarPrestamos();

    }
    
    public boolean devolverPrestamo(int idPrestamo) {

        modelo.Prestamo prestamo =
                prestamoController.buscarPorId(idPrestamo);

        if (prestamo == null) {

            return false;
        }

        modelo.Libro libro =
                libroController.buscarPorId(
                        prestamo.getIdLibro());

        if (libro == null) {

            return false;
        }

        if (prestamoController.devolverPrestamo(idPrestamo)) {

            libroController.actualizarStock(
                    libro.getId(),
                    libro.getStock() + 1);
            subject.notificar();
            return true;
        }

        return false;
    }
    
    public void agregarObserver(observer.Observer observer) {
        subject.agregarObserver(observer);
    }
    
    public java.util.ArrayList<modelo.Libro> buscarLibros(
            BusquedaStrategy strategy,
            String dato) {

        contextoBusqueda.setStrategy(strategy);

        return contextoBusqueda.buscar(dato);

    }
    
    
    
}