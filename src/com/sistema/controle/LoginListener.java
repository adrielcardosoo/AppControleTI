package com.sistema.controle;

import com.sistema.bean.Login;
import com.sistema.dao.CadLoginDao;
import com.sistema.master.AppControleTI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.sistema.master.TelaLogin;
import com.sistema.master.TelaPrincipal;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JOptionPane;

/**
 *
 * @author Adriel Cardoso
 */

public class LoginListener implements ActionListener {

    UltimoUsuarioLogin ultimoLogin = new UltimoUsuarioLogin();
    Log log;
    Login Login = new Login();
    private TelaLogin frame;

    public LoginListener(TelaLogin frame) {
        this.frame = frame; 
    }

    public void validar() throws Exceptions{
        String senha = Login.getSenha();
        String login = Login.getLogin();

        if (login.length() == 0) {
            throw new Exceptions("Informe o LOGIN!");
        } else if (senha.length() == 0) {
            throw new Exceptions("Informe a SENHA!");
        } else {
            CadLoginDao validaBanco = new CadLoginDao();
            boolean condicao = validaBanco.exists(Login);
            if(condicao == true){
                ultimoLogin.UltimoUsuarioLogin(frame.getUsuario());
                frame.dispose();
                
                TelaPrincipal telaPrincipal = new TelaPrincipal();

                telaPrincipal.setExtendedState( MAXIMIZED_BOTH );
                telaPrincipal.setVisible( true );
            }else{
                throw new Exceptions("Usuario e/ou Senha Invalido(s)!");
            }
           
        }
    }

    public void actionPerformed(ActionEvent evento) {
        if ("entrar".equals(evento.getActionCommand())) {
            Login.setSenha(frame.getSenha());
            Login.setLogin(frame.getUsuario());

            try {
                validar();
            } catch (Exceptions e) {
                JOptionPane.showMessageDialog(null, "Erro : " + e.getMessage(), "Erro ", JOptionPane.ERROR_MESSAGE);
            }
        }

        if ("sair".equals(evento.getActionCommand())) {
            frame.dispose();
        }
    }

}
