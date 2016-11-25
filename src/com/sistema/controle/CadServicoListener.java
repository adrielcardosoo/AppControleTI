package com.sistema.controle;

import com.sistema.bean.Servico;
import com.sistema.cadastro.CadServicoJIF;
import com.sistema.dao.CadServicoDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CadServicoListener implements ActionListener {

    UltimoUsuarioLogin ultimoLogin = new UltimoUsuarioLogin();
    Log log;

    public CadServicoJIF frame;

    public CadServicoListener(CadServicoJIF frame) {
        this.frame = frame;
    }

    public void actionPerformed(ActionEvent evento) {

        if ("Cadastrar".equals(evento.getActionCommand())) {
            boolean JaCadastrado = false;

            String cod = frame.getCod();
            
            CadServicoControle ct = new CadServicoControle(frame);
            Servico retorno = ct.getStanceCadmodelo();
            CadServicoDao insereBanco = new CadServicoDao();
            
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
            
            log = new Log(ultimoLogin.lerArquivo() + " Cadastrou um serviço");
            frame.LimpaForm();
        }
        
        if ("Excluir".equals(evento.getActionCommand())) {
            CadServicoDao exclui = new CadServicoDao();
            
            if (frame.getCod().length() == 0 || frame.getCod() == null) {
                JOptionPane.showMessageDialog(null, "Codigo invalido!");
            } else {
                try {
                    exclui.delete(frame.getCod());
                } catch (Exception ex) {
                    Logger.getLogger(CadLoginListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                log = new Log(ultimoLogin.lerArquivo() + " Excluiu um serviço");
            }
            
        }
    }
    
    public Servico procuraElemento() throws Exceptions{
        CadServicoControle ct = new CadServicoControle(frame);
        Servico retorno = ct.getStanceCadmodelo();
        CadServicoDao procuraBanco = new CadServicoDao();
        
        if( procuraBanco.select( retorno ) ){
            return retorno;
        }
        
        return null;
    }

}
