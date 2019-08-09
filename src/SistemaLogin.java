import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class SistemaLogin {
    public SistemaLogin(){
        JFrame tela = new JFrame("Sistema Login");
        JPanel panel = new JPanel();
        JLabel lblUsuario = new JLabel("Login: ");
        JTextField txfUsuario = new JTextField(20);
        JLabel lblSenha = new JLabel("Senha: ");
        JPasswordField psfSenha = new JPasswordField(20);
        JButton cadastrar = new JButton("Cadastrar");
        JButton logar = new JButton("Logar!");


        tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tela.setSize(250,100);

        panel.setLayout(new GridLayout(3,1));
        panel.add(lblUsuario);
        panel.add(txfUsuario);
        panel.add(lblSenha);
        panel.add(psfSenha);
        panel.add(cadastrar);
        panel.add(logar);


        tela.add(panel,BorderLayout.CENTER);
        tela.setVisible(true);
    }
    public static void main(String[] args) {
        new SistemaLogin();
    }
}
