import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class SistemaLogin {
    Scanner leia = new Scanner(System.in);
    JFrame tela = new JFrame("Sistema Login");
    JPanel panel = new JPanel();
    JLabel lblUsuario = new JLabel("Login: ");
    JTextField txfUsuario = new JTextField(20);
    JLabel lblSenha = new JLabel("Senha: ");
    JPasswordField psfSenha = new JPasswordField(20);
    JButton cadastrar = new JButton("Cadastrar");
    JButton logar = new JButton("Logar!");

    public SistemaLogin(){

        tela.setLocationRelativeTo(null);
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
            if(!cadastro(nome,senha)){
                JOptionPane.showMessageDialog(tela, "Erro! Cadastro não realizado!");
            }else{
                JOptionPane.showMessageDialog(tela,"Cadastro realizado com sucesso!");
            }
        });

        logar.addActionListener(actionEvent -> {
            String nome = txfUsuario.getText();
            String senha = String.valueOf(psfSenha.getPassword());
            if(!logando(nome, senha)){
                JOptionPane.showMessageDialog(tela,"Login ou Senha Incorretos");
            }else{
                JOptionPane.showMessageDialog(tela,"Login realizado com sucesso!");
            }
        });

        tela.add(panel,BorderLayout.CENTER);
        tela.setVisible(true);
    }

    boolean cadastro(String nome, String senha){
        try {
            if (nome.matches("([a-zA-Z0-9._-]{3,})") && senha.matches("([a-zA-Z0-9._-]{3,})")) {
                PrintStream escrever = new PrintStream(new FileOutputStream("logins.txt", true));
                Scanner txtNoScanner = new Scanner(new FileInputStream("logins.txt"));
                while (txtNoScanner.hasNextLine()) {
                    String login = txtNoScanner.next();
                    if (login.equals(nome)) {
                        return false;
                    }
                }
                escrever.println();
                escrever.print(nome + " " + senha);
                return true;
            } else {
                return false;
            }
        }catch(FileNotFoundException e){
            return false;
        }
    }

    boolean logando(String nome, String senha) {
        try {

            String tentativaLogin = nome + " " + senha;
            Scanner txtNoScanner = new Scanner(new FileInputStream("logins.txt"));

            while(txtNoScanner.hasNextLine()) {
                String loginRegistrado = txtNoScanner.nextLine();
                if (loginRegistrado.equals(tentativaLogin)) {
                    txtNoScanner.close();
                    return true;
                }
            }
            return false;

        } catch (FileNotFoundException e) {
            return false;
        }
    }


    public static void main(String[] args) {
        new SistemaLogin();
    }
}
