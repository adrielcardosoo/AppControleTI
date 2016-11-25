package com.sistema.controle;

import com.sistema.cadastro.CadClienteJIF;
import com.sistema.master.AppControleTI;
import com.sistema.bean.Cliente;
import com.sistema.dao.CadClienteDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            
            CadClienteControle ct = new CadClienteControle(frame);
            Cliente retorno = ct.getStanceCadmodelo();
            CadClienteDao insereBanco = new CadClienteDao();
            
            if (cod.length() == 0 || cod == null) {
                JOptionPane.showMessageDialog(null, "Codigo invalido!");
                return;
            }
  
            try {
                if (insereBanco.exists( retorno )){
                    JaCadastrado = true;
                }
            } catch (Exceptions ex) {
                Logger.getLogger(CadLoginListener.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (JaCadastrado){
                try {
                    insereBanco.update( retorno );
                } catch (Exceptions ex) {
                    Logger.getLogger(CadLoginListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    insereBanco.insert( retorno );
                } catch (Exception ex) {
                    Logger.getLogger(CadLoginListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            log = new Log(ultimoLogin.lerArquivo() + " Cadastrou um cliente");
            frame.LimpaForm();
        }
        
        if ("Excluir".equals(evento.getActionCommand())) {
            CadClienteDao exclui = new CadClienteDao();

            if (frame.getCod().length() == 0 || frame.getCod() == null) {
                JOptionPane.showMessageDialog(null, "Codigo invalido!");
            } else {
                try {
                    exclui.delete(frame.getCod());
                } catch (Exception ex) {
                    Logger.getLogger(CadLoginListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                log = new Log(ultimoLogin.lerArquivo() + " Excluiu um cliente");
            }
        }

    }
    
    public Cliente procuraElemento() throws Exceptions{
        CadClienteControle ct = new CadClienteControle(frame);
        Cliente retorno = ct.getStanceCadmodelo();
        CadClienteDao procuraBanco = new CadClienteDao();
        
        if( procuraBanco.select( retorno ) ){
            return retorno;
        }
        
        return null;
    }

}
