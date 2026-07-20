package vista;
import dao.LibroDAO;
import modelo.Libro;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import controlador.LibroController;
import servicio.BibliotecaFacade;

public class FrmLibros extends JFrame implements observer.Observer{

    private JLabel lblTitulo;
    private JTable tablaLibros;
    private JScrollPane scroll;
    private JLabel lblNombre;
    private JLabel lblAutor;
    private JLabel lblCategoria;
    private JLabel lblStock;
    private int idLibro = 0;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtCategoria;
    private JTextField txtStock;
    private JButton btnActualizar;
    private JButton btnRegistrar;
    private JButton btnEliminar;
    private LibroController controller;
    private BibliotecaFacade facade;
    private JTextField txtBuscar;
    private JComboBox<String> cboBuscar;
    private JButton btnBuscar;

    public FrmLibros() {

        setTitle("Gestión de Libros");
        setSize(800,500);
        setLocationRelativeTo(null);
        setLayout(null);

        // Título
        lblTitulo = new JLabel("GESTIÓN DE LIBROS");
        lblTitulo.setBounds(320,20,200,30);

        // Etiquetas
        lblNombre = new JLabel("Título:");
        lblNombre.setBounds(50,80,100,25);

        lblAutor = new JLabel("Autor:");
        lblAutor.setBounds(50,120,100,25);

        lblCategoria = new JLabel("Categoría:");
        lblCategoria.setBounds(50,160,100,25);

        lblStock = new JLabel("Stock:");
        lblStock.setBounds(50,200,100,25);

        // Cajas de texto
        txtTitulo = new JTextField();
        txtTitulo.setBounds(150,80,250,25);

        txtAutor = new JTextField();
        txtAutor.setBounds(150,120,250,25);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(150,160,250,25);

        txtStock = new JTextField();
        txtStock.setBounds(150,200,250,25);

        // Botón
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(150,260,120,35);

        // Agregar componentes
        add(lblTitulo);

        add(lblNombre);
        add(txtTitulo);

        add(lblAutor);
        add(txtAutor);

        add(lblCategoria);
        add(txtCategoria);

        add(lblStock);
        add(txtStock);

        add(btnRegistrar);

    btnRegistrar.addActionListener(
        e -> registrarLibro());
    tablaLibros = new JTable();
    btnActualizar =
        new JButton("Actualizar");

    btnActualizar.setBounds(
        280,260,120,35);

    add(btnActualizar);
    btnActualizar.addActionListener(
        e -> actualizarLibro());
    scroll = new JScrollPane(tablaLibros);
    scroll.setBounds(430, 80, 330, 250);
    
    btnEliminar = new JButton("Eliminar");
    btnEliminar.setBounds(410,260,120,35);

    add(btnEliminar);
    btnEliminar.addActionListener(
        e -> eliminarLibro());
    
    add(scroll);
    tablaLibros.addMouseListener(
    new java.awt.event.MouseAdapter() {

        @Override
        public void mouseClicked(
                java.awt.event.MouseEvent evt) {

            seleccionarLibro();
        }
    });
    txtBuscar = new JTextField();
    txtBuscar.setBounds(40, 20, 180, 25);

    cboBuscar = new JComboBox<>();
    cboBuscar.addItem("Título");
    cboBuscar.addItem("Autor");
    cboBuscar.addItem("Categoría");
    cboBuscar.setBounds(230, 20, 120, 25);

    btnBuscar = new JButton("Buscar");
    btnBuscar.setBounds(360, 20, 100, 25);
    btnBuscar.addActionListener(e -> buscarLibro());
    add(txtBuscar);
    add(cboBuscar);
    add(btnBuscar);
    listarLibros();
    controller = new LibroController();
    facade = BibliotecaFacade.getInstancia();
    facade.agregarObserver(this);
    setVisible(true);
    }
    
    //registrar libro 
    private void registrarLibro() {

    try {

        Libro libro = new Libro();

        libro.setTitulo(
                txtTitulo.getText());

        libro.setAutor(
                txtAutor.getText());

        libro.setCategoria(
                txtCategoria.getText());

        libro.setStock(
                Integer.parseInt(
                        txtStock.getText()));

        if (controller.registrarLibro(
        libro.getTitulo(),
        libro.getAutor(),
        libro.getCategoria(),
        libro.getStock())) {

            JOptionPane.showMessageDialog(
                    this,
                    "Libro registrado correctamente");

            limpiar();
            listarLibros();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al registrar libro");
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Ingrese datos válidos");
    }
    }
    
    
    private void limpiar() {

    txtTitulo.setText("");
    txtAutor.setText("");
    txtCategoria.setText("");
    txtStock.setText("");

    idLibro = 0;

    txtTitulo.requestFocus();
    }
    
    
    private void listarLibros() {

    DefaultTableModel modelo =
            new DefaultTableModel();

    modelo.addColumn("ID");
    modelo.addColumn("Título");
    modelo.addColumn("Autor");
    modelo.addColumn("Categoría");
    modelo.addColumn("Stock");

    LibroDAO dao = new LibroDAO();

    ArrayList<Libro> lista =
            dao.listar();

    for (Libro libro : lista) {

        modelo.addRow(new Object[]{
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getCategoria(),
                libro.getStock()
        });
    }

    tablaLibros.setModel(modelo);
    }
    
    private void seleccionarLibro() {

    int fila =
            tablaLibros.getSelectedRow();

    if (fila >= 0) {

        idLibro = Integer.parseInt(
                tablaLibros.getValueAt(
                        fila, 0).toString());

        txtTitulo.setText(
                tablaLibros.getValueAt(
                        fila, 1).toString());

        txtAutor.setText(
                tablaLibros.getValueAt(
                        fila, 2).toString());

        txtCategoria.setText(
                tablaLibros.getValueAt(
                        fila, 3).toString());

        txtStock.setText(
                tablaLibros.getValueAt(
                        fila, 4).toString());
    }
    }
    
    //actualizar libro
    private void actualizarLibro() {

    try {

        Libro libro = new Libro();

        libro.setId(idLibro);

        libro.setTitulo(
                txtTitulo.getText());

        libro.setAutor(
                txtAutor.getText());

        libro.setCategoria(
                txtCategoria.getText());

        libro.setStock(
                Integer.parseInt(
                        txtStock.getText()));

        if (controller.actualizarLibro(
        idLibro,
        txtTitulo.getText(),
        txtAutor.getText(),
        txtCategoria.getText(),
        Integer.parseInt(
                txtStock.getText()))){

            JOptionPane.showMessageDialog(
                    this,
                    "Libro actualizado");

            limpiar();
            listarLibros();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al actualizar");
        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(
                this,
                "Datos incorrectos");
    }
    }
    
    //Eliminar libro
    private void eliminarLibro() {

    if (idLibro == 0) {

        JOptionPane.showMessageDialog(
                this,
                "Seleccione un libro");

        return;
    }

    int opcion =
            JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea eliminar este libro?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION);

    if (opcion == JOptionPane.YES_OPTION) {

        if (controller.eliminarLibro(idLibro)){

            JOptionPane.showMessageDialog(
                    this,
                    "Libro eliminado");

            idLibro = 0;

            limpiar();
            listarLibros();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al eliminar");
        }
    }
    }

    @Override
    public void actualizar() {

        listarLibros();

    }
    private void buscarLibro() {

        String dato = txtBuscar.getText();

        if (dato.isEmpty()) {

            listarLibros();
            return;
        }

        strategy.BusquedaStrategy estrategia;

        switch (cboBuscar.getSelectedItem().toString()) {

            case "Autor":

                estrategia = new strategy.BusquedaPorAutor();
                break;

            case "Categoría":

                estrategia = new strategy.BusquedaPorCategoria();
                break;

            default:

                estrategia = new strategy.BusquedaPorTitulo();
        }

        java.util.ArrayList<modelo.Libro> lista =
                facade.buscarLibros(estrategia, dato);

        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel)
                tablaLibros.getModel();

        modelo.setRowCount(0);

        for (modelo.Libro libro : lista) {

            modelo.addRow(new Object[]{
                libro.getId(),
                libro.getTitulo(),
                libro.getAutor(),
                libro.getCategoria(),
                libro.getStock()
            });
        }
    }
}
