package vista;

import javax.swing.JFrame;
import javax.swing.*;
import controlador.UsuarioController;
import factory.UsuarioFactory;
import modelo.Usuario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class FrmUsuarios extends JFrame {
    private JLabel lblTitulo;

    private JLabel lblNombre;
    private JLabel lblDni;
    private JLabel lblCorreo;
    private JLabel lblUsuario;
    private JLabel lblPassword;
    private JLabel lblRol;

    private JTextField txtNombre;
    private JTextField txtDni;
    private JTextField txtCorreo;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    private JComboBox<String> cboRol;

    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;

    private JTable tablaUsuarios;
    private JScrollPane scroll;
    
    public FrmUsuarios() {

    setTitle("Gestión de Usuarios");
    setSize(1000,600);
    setLocationRelativeTo(null);
    setLayout(null);

    lblTitulo = new JLabel("GESTIÓN DE USUARIOS");
    lblTitulo.setBounds(380,20,250,30);

    lblNombre = new JLabel("Nombre:");
    lblNombre.setBounds(40,80,100,25);

    lblDni = new JLabel("DNI:");
    lblDni.setBounds(40,120,100,25);

    lblCorreo = new JLabel("Correo:");
    lblCorreo.setBounds(40,160,100,25);

    lblUsuario = new JLabel("Usuario:");
    lblUsuario.setBounds(40,200,100,25);

    lblPassword = new JLabel("Password:");
    lblPassword.setBounds(40,240,100,25);

    lblRol = new JLabel("Rol:");
    lblRol.setBounds(40,280,100,25);

    txtNombre = new JTextField();
    txtNombre.setBounds(140,80,250,25);

    txtDni = new JTextField();
    txtDni.setBounds(140,120,250,25);

    txtCorreo = new JTextField();
    txtCorreo.setBounds(140,160,250,25);

    txtUsuario = new JTextField();
    txtUsuario.setBounds(140,200,250,25);

    txtPassword = new JPasswordField();
    txtPassword.setBounds(140,240,250,25);

    cboRol = new JComboBox<>();

    cboRol.addItem("ALUMNO");
    cboRol.addItem("DOCENTE");

    cboRol.setBounds(140,280,250,25);

    btnRegistrar = new JButton("Registrar");
    btnRegistrar.setBounds(40,340,120,35);

    btnActualizar = new JButton("Actualizar");
    btnActualizar.setBounds(180,340,120,35);

    btnEliminar = new JButton("Eliminar");
    btnEliminar.setBounds(320,340,120,35);

    tablaUsuarios = new JTable();

    scroll = new JScrollPane(tablaUsuarios);
    scroll.setBounds(450,80,500,350);

    add(lblTitulo);

    add(lblNombre);
    add(txtNombre);

    add(lblDni);
    add(txtDni);

    add(lblCorreo);
    add(txtCorreo);

    add(lblUsuario);
    add(txtUsuario);

    add(lblPassword);
    add(txtPassword);

    add(lblRol);
    add(cboRol);

    add(btnRegistrar);
    add(btnActualizar);
    add(btnEliminar);

    add(scroll);
    
    controller = new UsuarioController();
    btnRegistrar.addActionListener(
        e -> registrarUsuario());
    btnActualizar.addActionListener(
        e -> actualizarUsuario());

    btnEliminar.addActionListener(
        e -> eliminarUsuario());
    tablaUsuarios.getSelectionModel().addListSelectionListener(e -> {

    if (!e.getValueIsAdjusting()) {

        cargarUsuarioSeleccionado();
    }
    });
    listarUsuarios();
    setVisible(true);

    }
    private UsuarioController controller;
    private int idUsuario = 0;
    
    private void registrarUsuario() {

    try {

        String tipo =
                cboRol.getSelectedItem().toString();

        Usuario usuario =
                UsuarioFactory.crearUsuario(tipo);

        usuario.setNombre(
                txtNombre.getText());

        usuario.setDni(
                txtDni.getText());

        usuario.setCorreo(
                txtCorreo.getText());

        usuario.setUsuario(
                txtUsuario.getText());

        usuario.setPassword(
                String.valueOf(
                        txtPassword.getPassword()));

        usuario.setRol(tipo);

        if (controller.registrarUsuario(usuario)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario registrado correctamente");

            limpiar();
            listarUsuarios();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Error al registrar usuario");
        }

    } catch (Exception e) {

        e.printStackTrace();

        JOptionPane.showMessageDialog(
                this,
                "Error en los datos");
    }
    }
    
    private void actualizarUsuario() {

    try {

        if (idUsuario == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un usuario");

            return;
        }

        String tipo =
                cboRol.getSelectedItem().toString();

        Usuario usuario =
                UsuarioFactory.crearUsuario(tipo);

        usuario.setId(idUsuario);
        usuario.setNombre(txtNombre.getText());
        usuario.setDni(txtDni.getText());
        usuario.setCorreo(txtCorreo.getText());
        usuario.setUsuario(txtUsuario.getText());
        usuario.setPassword(
                String.valueOf(
                        txtPassword.getPassword()));
        usuario.setRol(tipo);

        if (controller.actualizarUsuario(usuario)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario actualizado");

            limpiar();
            listarUsuarios();

            idUsuario = 0;

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo actualizar");
        }

    } catch (Exception e) {

        e.printStackTrace();
    }
    }
    
    private void eliminarUsuario() {

    if (idUsuario == 0) {

        JOptionPane.showMessageDialog(
                this,
                "Seleccione un usuario");

        return;
    }

    int respuesta = JOptionPane.showConfirmDialog(
            this,
            "¿Desea eliminar este usuario?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION);

    if (respuesta == JOptionPane.YES_OPTION) {

        if (controller.eliminarUsuario(idUsuario)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Usuario eliminado");

            limpiar();
            listarUsuarios();

            idUsuario = 0;

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar");
        }
    }
}
    
    private void limpiar() {

    txtNombre.setText("");
    txtDni.setText("");
    txtCorreo.setText("");
    txtUsuario.setText("");
    txtPassword.setText("");

    cboRol.setSelectedIndex(0);
    idUsuario = 0;
    txtNombre.requestFocus();
    }
    
    private void listarUsuarios() {

    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("ID");
    modelo.addColumn("Nombre");
    modelo.addColumn("DNI");
    modelo.addColumn("Correo");
    modelo.addColumn("Usuario");
    modelo.addColumn("Rol");

    ArrayList<Usuario> lista =
            controller.listarUsuarios();

    for (Usuario u : lista) {

        modelo.addRow(new Object[]{
            u.getId(),
            u.getNombre(),
            u.getDni(),
            u.getCorreo(),
            u.getUsuario(),
            u.getRol()
        });
    }

    tablaUsuarios.setModel(modelo);
    }
    
    private void cargarUsuarioSeleccionado() {

    int fila = tablaUsuarios.getSelectedRow();

    if (fila != -1) {

        idUsuario = Integer.parseInt(
                tablaUsuarios.getValueAt(fila, 0).toString());

        txtNombre.setText(
                tablaUsuarios.getValueAt(fila, 1).toString());

        txtDni.setText(
                tablaUsuarios.getValueAt(fila, 2).toString());

        txtCorreo.setText(
                tablaUsuarios.getValueAt(fila, 3).toString());

        txtUsuario.setText(
                tablaUsuarios.getValueAt(fila, 4).toString());

        String rol =
                tablaUsuarios.getValueAt(fila, 5).toString();

        cboRol.setSelectedItem(rol);

        txtPassword.setText("");
        modelo.Usuario usuario =
            controller.listarUsuarios().get(fila);

        adapter.UsuarioAdapter adapter =
                new adapter.UsuarioAdapter(usuario);

        JOptionPane.showMessageDialog(
        this,
        adapter.mostrarInformacion());
    }
    }
    
}