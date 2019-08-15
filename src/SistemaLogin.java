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
        tela.setSize(250,100); //Original
        //tela.setSize(800,600);
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

    void cadastro(String nome, String senha){
        String login = "";
        try {
            if(nome.isBlank() || senha.isBlank()){
                throw new IllegalArgumentException("Erro! não é possível deixar login ou senha em branco!");
            }
            if (nome.matches("([a-zA-Z0-9._-]{3,})") && senha.matches("([a-zA-Z0-9._-]{3,})")) {
                PrintStream escrever = new PrintStream(new FileOutputStream("logins.txt", true));
                Scanner txtNoScanner = new Scanner(new FileInputStream("logins.txt"));
                while (txtNoScanner.hasNextLine()) {
                    login = txtNoScanner.next();
                    if (login.equals(nome)) {
                        throw new IllegalArgumentException("Erro! esse usuário já foi cadastrado!");
                    }
                }
                if(login.isBlank()){
                    escrever.print(nome + " " + senha);
                }else {
                    escrever.println();
                    escrever.print(nome + " " + senha);
                }
                JOptionPane.showMessageDialog(tela, "Sucesso! Login cadastrado com sucesso!");
            } else {
                throw new IllegalArgumentException("Erro! Voce pode usar letras, números ou .-_ a partir de 3 digitos");
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(tela,e.getMessage());
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(tela,e.getMessage());
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
