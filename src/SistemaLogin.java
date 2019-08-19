import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class SistemaLogin {
    Scanner leia = new Scanner(System.in);
    JFrame tela = new JFrame();
    JPanel panelDoFrame = new JPanel();
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
        tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tela.setSize(300,300);
        tela.setResizable(false);
        tela.setLocationRelativeTo(null);

        //Panel do Frame
        panelDoFrame.setLayout(new GridBagLayout());
        panelDoFrame.setPreferredSize(tela.getSize());
        panelDoFrame.setBackground(Color.BLUE);

        //Fonte das Labels
        Font fonte = new Font("Georgia",Font.PLAIN,20);

        //Label do Bem Vindo
        lblBemVindo.setText("Sistema de Login!! :D");

        //Label do Login
        lblLogin.setText("Login: ");
        lblLogin.setFont(fonte);
        lblLogin.setForeground(Color.BLUE);

        //Label da Senha
        lblSenha.setText("Senha: ");
        lblSenha.setFont(fonte);
        lblSenha.setForeground(Color.BLUE);

        //Txf do Login
        txfLogin.setColumns(10);
        txfLogin.setToolTipText("Campo para digitar o Login");

        //Psf da Senha
        psfSenha.setColumns(10);
        psfSenha.setToolTipText("Campo para digitar a Senha");

        //Botão Cadastrar
        btnCadastrar.setText("Cadastrar");
        btnLogar.setSize(30,30);
        btnCadastrar.addActionListener(actionEvent -> {
            String nome = txfLogin.getText();
            String senha = String.valueOf(psfSenha.getPassword());
            cadastro(nome,senha);
        });

        //Botão Logar
        btnLogar.setText("Logar!");
        btnLogar.setSize(30,30);
        btnLogar.addActionListener(actionEvent -> {
            String nome = txfLogin.getText();
            String senha = String.valueOf(psfSenha.getPassword());
            if(!logando(nome, senha)){
                JOptionPane.showMessageDialog(tela,"Login ou Senha Incorretos");
            }else{
                JOptionPane.showMessageDialog(tela,"Login realizado com sucesso!");
            }
        });

        //Grids do GridBagLayout
        GridBagConstraints quebraLinha = new GridBagConstraints();
        quebraLinha.gridwidth = GridBagConstraints.REMAINDER;
        GridBagConstraints fillGrid = new GridBagConstraints();
        fillGrid.fill = GridBagConstraints.HORIZONTAL;

        //Panel
        panel.setBackground(Color.green);
        panel.setLayout(new GridBagLayout());
        //panel.add(lblBemVindo);
        panel.add(lblLogin,fillGrid);
        panel.add(txfLogin,quebraLinha);
        panel.add(lblSenha,fillGrid);
        panel.add(psfSenha,quebraLinha);
        panel.add(btnCadastrar,fillGrid);
        panel.add(btnLogar,fillGrid);
        panel.setPreferredSize(new Dimension(230,200));
        panel.setBorder(new LineBorder(new Color(0,0,0),3));

        //Montagem dos Componentes
        panelDoFrame.add(panel);
        tela.add(panelDoFrame);
        //tela.pack();
        tela.setVisible(true);
    }

    //Método do Cadastro
    void cadastro(String nome, String senha){
        String login = "";
        try {
            if(nome.isBlank() || senha.isBlank()){
                throw new IllegalArgumentException("Erro! Não é possível deixar Login ou Senha em branco!");
            }
            if (nome.matches("([a-zA-Z0-9._-]{3,})") && senha.matches("([a-zA-Z0-9._-]{3,})")) {
                PrintStream escrever = new PrintStream(new FileOutputStream("logins.txt", true));
                Scanner txtNoScanner = new Scanner(new FileInputStream("logins.txt"));
                while (txtNoScanner.hasNextLine()) {
                    login = txtNoScanner.next();
                    if (login.equals(nome)) {
                        throw new IllegalArgumentException("Erro! Esse usuário já foi cadastrado!");
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
