import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.concurrent.Flow;

public class SistemaLogin {
    Scanner leia = new Scanner(System.in);
    JFrame tela = new JFrame();
    JPanel panel = new JPanel();
    JLabel lblBemVindo = new JLabel();
    JLabel lblLogin = new JLabel();
    JLabel lblSenha = new JLabel();
    JTextField txfLogin = new JTextField();
    JPasswordField psfSenha = new JPasswordField();
    JButton btnCadastrar = new JButton();
    JButton btnLogar = new JButton();

    public SistemaLogin(){

        //Frame
        tela.setTitle("Sistema Login");
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tela.setSize(300,300);
        tela.setResizable(true);

        //Fonte das Labels
        Font fonte = new Font("Georgia",Font.PLAIN,15);

        //Label do Bem Vindo
        lblBemVindo.setText("Sistema de Login!! :D");

        //Label do Login
        lblLogin.setText("Login: ");
        lblLogin.setFont(fonte);
        lblLogin.setForeground(Color.BLUE);
        lblLogin.setHorizontalTextPosition(SwingConstants.CENTER);


        //Label da Senha
        lblSenha.setText("Senha: ");
        lblSenha.setFont(fonte);
        lblSenha.setForeground(Color.BLUE);

        //Txf do Login
        txfLogin.setColumns(10);
        //Psf da Senha
        psfSenha.setColumns(10);

        //Botão Cadastrar
        btnCadastrar.setText("Cadastrar");
        System.out.println(btnCadastrar.getSize());
        //btnCadastrar.setSize();
        btnCadastrar.addActionListener(actionEvent -> {
            String nome = txfLogin.getText();
            String senha = String.valueOf(psfSenha.getPassword());
            cadastro(nome,senha);
        });

        //Botão Logar
        btnLogar.setText("Logar!");
        btnLogar.addActionListener(actionEvent -> {
            String nome = txfLogin.getText();
            String senha = String.valueOf(psfSenha.getPassword());
            if(!logando(nome, senha)){
                JOptionPane.showMessageDialog(tela,"Login ou Senha Incorretos");
            }else{
                JOptionPane.showMessageDialog(tela,"Login realizado com sucesso!");
            }
        });

        //Panel
        panel.setBackground(Color.green);
        panel.setLayout(new GridLayout(3,1));
        //panel.add(lblBemVindo);
        panel.add(lblLogin);
        panel.add(txfLogin);
        panel.add(lblSenha);
        panel.add(psfSenha);
        panel.add(btnCadastrar);
        panel.add(btnLogar);

        tela.setContentPane(new JPanel());
        tela.add(panel);
        //tela.pack();
        tela.setVisible(true);
    }

    //Método do Cadastro
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

    //Metodo do login
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
