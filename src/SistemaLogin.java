import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class SistemaLogin {
    Scanner leia = new Scanner(System.in);

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
        tela.setResizable(false);

        panel.setLayout(new GridLayout(3,1));
        panel.add(lblUsuario);
        panel.add(txfUsuario);
        panel.add(lblSenha);
        panel.add(psfSenha);
        panel.add(cadastrar);
        panel.add(logar);

        cadastrar.addActionListener(actionEvent -> {
            String nome = txfUsuario.getText();
            String senha = String.valueOf(psfSenha.getPassword());
            cadastro(nome,senha);
        });

        tela.add(panel,BorderLayout.CENTER);
        tela.setVisible(true);
    }

    void cadastro(String nome, String senha){
        try {
            PrintStream escrever = new PrintStream("logins.txt");
            escrever.print(nome + " " + senha);
        }catch(FileNotFoundException e){
            System.out.println("Erro, Arquivo nao encontrado!");
        }
    }

    public static void main(String[] args) {
        new SistemaLogin();
    }
}
