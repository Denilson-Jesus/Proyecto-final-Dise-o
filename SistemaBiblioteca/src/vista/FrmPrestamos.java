package vista;

import javax.swing.*;
import servicio.BibliotecaFacade;

public class FrmPrestamos extends JFrame implements observer.Observer {
    private JLabel lblTitulo;

    private JLabel lblUsuario;
    private JLabel lblLibro;
    private JLabel lblFechaPrestamo;
    private JLabel lblFechaDevolucion;
    private JLabel lblEstado;

    private JComboBox<String> cboUsuarios;
    private JComboBox<String> cboLibros;
    private JComboBox<String> cboEstado;

    private JTextField txtFechaPrestamo;
    private JTextField txtFechaDevolucion;

    private JButton btnRegistrar;
    private JButton btnDevolver;

    private JTable tablaPrestamos;
    
    private int idPrestamo = 0;
private JScrollPane scroll;
private servicio.BibliotecaFacade facade;
private java.util.ArrayList<modelo.Usuario> listaUsuarios;
private java.util.ArrayList<modelo.Libro> listaLibros;

    public FrmPrestamos() {

        setTitle("Gestión de Préstamos");

        setSize(1050,600);

        setLocationRelativeTo(null);

        setLayout(null);

        lblTitulo = new JLabel("GESTIÓN DE PRÉSTAMOS");
        lblTitulo.setBounds(390,20,250,30);

        lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(40,80,120,25);

        lblLibro = new JLabel("Libro:");
        lblLibro.setBounds(40,120,120,25);

        lblFechaPrestamo = new JLabel("Fecha préstamo:");
        lblFechaPrestamo.setBounds(40,160,120,25);

        lblFechaDevolucion = new JLabel("Fecha devolución:");
        lblFechaDevolucion.setBounds(40,200,120,25);

        lblEstado = new JLabel("Estado:");
        lblEstado.setBounds(40,240,120,25);

        cboUsuarios = new JComboBox<>();
        cboUsuarios.setBounds(170,80,250,25);

        cboLibros = new JComboBox<>();
        cboLibros.setBounds(170,120,250,25);

        txtFechaPrestamo = new JTextField();
        txtFechaPrestamo.setBounds(170,160,250,25);

        txtFechaDevolucion = new JTextField();
        txtFechaDevolucion.setBounds(170,200,250,25);

        cboEstado = new JComboBox<>();

        cboEstado.addItem("PRESTADO");
        cboEstado.addItem("DEVUELTO");

        cboEstado.setBounds(170,240,250,25);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(40,310,150,35);

        btnDevolver = new JButton("Devolver");
        btnDevolver.setBounds(220,310,150,35);

        tablaPrestamos = new JTable();

        scroll = new JScrollPane(tablaPrestamos);
        scroll.setBounds(470,80,540,380);

        add(lblTitulo);

        add(lblUsuario);
        add(cboUsuarios);

        add(lblLibro);
        add(cboLibros);

        add(lblFechaPrestamo);
        add(txtFechaPrestamo);

        add(lblFechaDevolucion);
        add(txtFechaDevolucion);

        add(lblEstado);
        add(cboEstado);

        add(btnRegistrar);
        add(btnDevolver);

        add(scroll);
        facade = BibliotecaFacade.getInstancia();
        facade.agregarObserver(this);
        
        cargarUsuarios();
        cargarLibros();
        listarPrestamos();
        btnRegistrar.addActionListener(e -> registrarPrestamo());
        
        //btnDevolver.addActionListener(e -> devolverPrestamo());//
        
        tablaPrestamos.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                cargarPrestamoSeleccionado();

            }

        });
        btnDevolver.addActionListener(e -> devolverPrestamo());
        
        setVisible(true);
    }
    
    private void cargarUsuarios() {

        listaUsuarios = facade.listarUsuarios();

        cboUsuarios.removeAllItems();

        for (modelo.Usuario u : listaUsuarios) {

            cboUsuarios.addItem(
                    u.getId() + " - " + u.getNombre());
        }
    
    }
    
    private void cargarLibros() {

        listaLibros = facade.listarLibros();

        cboLibros.removeAllItems();

        for (modelo.Libro l : listaLibros) {

            cboLibros.addItem(
                    l.getId() + " - " + l.getTitulo());
        }

    }
    
    private void registrarPrestamo() {

        try {

            modelo.Usuario usuario =
                    listaUsuarios.get(cboUsuarios.getSelectedIndex());

            modelo.Libro libro =
                    listaLibros.get(cboLibros.getSelectedIndex());

            boolean ok =
                    facade.registrarPrestamo(
                            usuario.getId(),
                            libro.getId(),
                            txtFechaPrestamo.getText(),
                            txtFechaDevolucion.getText());

            if (ok) {

                JOptionPane.showMessageDialog(
                        this,
                        "Préstamo registrado correctamente");

                cargarLibros();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No fue posible registrar el préstamo");
            }

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Error al registrar préstamo");
        }

    }

    private void listarPrestamos() {

    javax.swing.table.DefaultTableModel modelo =
            new javax.swing.table.DefaultTableModel();

    modelo.addColumn("ID");
    modelo.addColumn("Usuario");
    modelo.addColumn("Libro");
    modelo.addColumn("F. Préstamo");
    modelo.addColumn("F. Devolución");
    modelo.addColumn("Estado");

    java.util.ArrayList<modelo.Prestamo> lista =
            facade.listarPrestamos();

    for (modelo.Prestamo p : lista) {

        modelo.addRow(new Object[]{
            p.getId(),
            p.getIdUsuario(),
            p.getIdLibro(),
            p.getFechaPrestamo(),
            p.getFechaDevolucion(),
            p.getEstado()
        });
    }

    tablaPrestamos.setModel(modelo);
    }
    
    private void cargarPrestamoSeleccionado() {

        int fila = tablaPrestamos.getSelectedRow();

        if (fila != -1) {

            idPrestamo = Integer.parseInt(
                    tablaPrestamos.getValueAt(fila, 0).toString());

        }

    }
    
    private void devolverPrestamo() {

        if (idPrestamo == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un préstamo");

            return;
        }

        if (facade.devolverPrestamo(idPrestamo)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Préstamo devuelto correctamente");

            listarPrestamos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo devolver el préstamo");

        }

    }
    
    @Override
    public void actualizar() {

        cargarLibros();
        listarPrestamos();

    }
    
}