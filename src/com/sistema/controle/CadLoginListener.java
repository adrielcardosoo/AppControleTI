package com.sistema.controle;

import com.sistema.bean.Login;
import com.sistema.cadastro.CadLoginJIF;
import com.sistema.dao.CadLoginDao;
import com.sistema.master.AppControleTI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            
            CadLoginControle ct = new CadLoginControle(frame);
            Login retorno = ct.getStanceCadmodelo();
            CadLoginDao insereBanco = new CadLoginDao();
            
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

            log = new Log(ultimoLogin.lerArquivo() + " Cadastrou um usuario de acesso");
            frame.LimpaForm();
        }
        
        if ("Excluir".equals(evento.getActionCommand())) {
            CadLoginDao exclui = new CadLoginDao();

            if (frame.getCod().length() == 0 || frame.getCod() == null) {
                JOptionPane.showMessageDialog(null, "Codigo invalido!");
            } else {
                try {
                    exclui.delete(frame.getCod());
                } catch (Exception ex) {
                    Logger.getLogger(CadLoginListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                log = new Log(ultimoLogin.lerArquivo() + " Excluiu um usuario de acesso");
            }

        }

    }
    
    public Login procuraElemento() throws Exceptions{
        CadLoginControle ct = new CadLoginControle(frame);
        Login retorno = ct.getStanceCadmodelo();
        CadLoginDao procuraBanco = new CadLoginDao();
        
        if( procuraBanco.select( retorno ) ){
            return retorno;
        }
        
        return null;
    }

}
