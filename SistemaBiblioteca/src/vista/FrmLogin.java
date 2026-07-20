package vista;
import dao.UsuarioDAO;
import javax.swing.JOptionPane;
import javax.swing.*;

public class FrmLogin extends JFrame {

    private JLabel lblTitulo;

    public FrmLogin() {

    setTitle("Sistema Biblioteca");
    setSize(500,300);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(null);

    lblTitulo = new JLabel("LOGIN BIBLIOTECA");
    lblTitulo.setBounds(170,20,200,30);

    lblUsuario = new JLabel("Usuario:");
    lblUsuario.setBounds(80,80,100,25);

    txtUsuario = new JTextField();
    txtUsuario.setBounds(180,80,180,25);

    lblPassword = new JLabel("Contraseña:");
    lblPassword.setBounds(80,120,100,25);

    txtPassword = new JPasswordField();
    txtPassword.setBounds(180,120,180,25);

    btnIngresar = new JButton("Ingresar");
    btnIngresar.setBounds(180,170,120,30);

    add(lblTitulo);
    add(lblUsuario);
    add(txtUsuario);
    add(lblPassword);
    add(txtPassword);
    add(btnIngresar);

    btnIngresar.addActionListener(e -> ingresar());

    setVisible(true);
}
    JLabel lblUsuario;
    JLabel lblPassword;

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIngresar;
private void ingresar() {

    String usuario =
            txtUsuario.getText();

    String password =
            String.valueOf(
                    txtPassword.getPassword());

    UsuarioDAO dao = new UsuarioDAO();

    if (dao.validarLogin(usuario, password)) {

        JOptionPane.showMessageDialog(
                this,
                "Bienvenido"
        );

        new FrmMenuPrincipal();

        dispose();

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Usuario o contraseña incorrectos"
        );
    }
}

}