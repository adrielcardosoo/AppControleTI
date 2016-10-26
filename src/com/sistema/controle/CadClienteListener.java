package com.sistema.controle;

import com.sistema.cadastro.CadClienteJIF;
import com.sistema.master.AppControleTI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CadClienteListener implements ActionListener {

    UltimoUsuarioLogin ultimoLogin = new UltimoUsuarioLogin();
    Log log;

    public CadClienteJIF frame;

    public CadClienteListener(CadClienteJIF frame) {
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
            
            for (int i=1; i<=AppControleTI.tcliente; i++){
                if (cod.equals(AppControleTI.cliente[i].codigo)){
                    JaCadastrado = true;
                }

                if (JaCadastrado){
                    JOptionPane.showMessageDialog(null, "Codigo do Cliente já cadastrado!");
                    break;
                }
            }

            frame.inserirCliente( AppControleTI.tcliente++ );
            
            log = new Log(ultimoLogin.lerArquivo() + "Cadastrou um cliente");
            frame.LimpaForm();
        }
        
        if ("Excluir".equals(evento.getActionCommand())) {
            if (frame.getCod().length() == 0 || frame.getCod() == null) {
                JOptionPane.showMessageDialog(null, "Codigo invalido!");
                return;
            } else {
                log = new Log(ultimoLogin.lerArquivo() + " Excluiu um cliente");
            }

        }
    }

}
