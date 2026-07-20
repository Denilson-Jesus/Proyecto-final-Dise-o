package vista;

import javax.swing.*;

public class FrmMenuPrincipal extends JFrame {

    public FrmMenuPrincipal() {

        setTitle("Menú Principal");
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo =
                new JLabel("SISTEMA DE BIBLIOTECA");

        lblTitulo.setBounds(200,30,250,30);

        add(lblTitulo);

        setVisible(true);
        btnLibros = new JButton("Gestión de Libros");
    btnLibros.setBounds(180,100,200,40);

    add(btnLibros);

    btnLibros.addActionListener(e -> {
    new FrmLibros();
    });
    
    btnUsuarios = new JButton("Gestión de Usuarios");
    btnUsuarios.setBounds(180,160,200,40);

    add(btnUsuarios);

    btnUsuarios.addActionListener(e -> {
        new FrmUsuarios();
    });
    
    btnPrestamos = new JButton("Gestión de Préstamos");
    btnPrestamos.setBounds(180,220,200,40);

    add(btnPrestamos);

    btnPrestamos.addActionListener(e -> {

        new FrmPrestamos();

    });
    
    }
    private JButton btnLibros;
    private JButton btnUsuarios;
    private JButton btnPrestamos;
}