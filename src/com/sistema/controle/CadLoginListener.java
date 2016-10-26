package com.sistema.controle;

import com.sistema.cadastro.CadLoginJIF;
import com.sistema.master.AppControleTI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CadLoginListener implements ActionListener {

    UltimoUsuarioLogin ultimoLogin = new UltimoUsuarioLogin();
    Log log;

    public CadLoginJIF frame;

    public CadLoginListener(CadLoginJIF frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent evento) {

        if ("Cadastrar".equals(evento.getActionCommand())) {
            boolean JaCadastrado = false;

            String cod = frame.getCod();
            
            if (cod.length() == 0 || cod == null) {
                JOptionPane.showMessageDialog(null, "Codigo invalido!");
                return;
            }
            
            for (int i=1; i<=AppControleTI.tlogins; i++){
                if (cod.equals(AppControleTI.login[i].codigo)){
                    JaCadastrado = true;
                }

                if (JaCadastrado){
                    JOptionPane.showMessageDialog(null, "Codigo do Login já cadastrado!");
                    break;
                }
            }

            frame.inserirLogin( AppControleTI.tlogins++ );
            log = new Log(ultimoLogin.lerArquivo() + " Cadastrou um usuario de acesso");
            frame.LimpaForm();
        }
        
        if ("Excluir".equals(evento.getActionCommand())) {
            if (frame.getCod().length() == 0 || frame.getCod() == null) {
                JOptionPane.showMessageDialog(null, "Codigo invalido!");
                return;
            } else {
                log = new Log(ultimoLogin.lerArquivo() + " Excluiu um usuario de acesso");
            }

        }
    }

}
