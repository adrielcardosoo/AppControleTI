package com.sistema.controle;

import com.sistema.bean.Login;
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
                if (cod.equals(AppControleTI.login[i].getCodigo())){
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
                removeItem(AppControleTI.login);
                log = new Log(ultimoLogin.lerArquivo() + " Excluiu um usuario de acesso");
            }

        }
        
        if ("Pesquisar".equals(evento.getActionCommand())){
            procuraElemento( Integer.parseInt( frame.getCod() ) );
        }
    }
    
    private void removeItem(Login[] array) {
        AppControleTI.login = pegaNovoArray(array, array.length);
    }
    
    private Login[] pegaNovoArray(Login[] array, int tamanho) {
        Login[] novoArray = new Login[tamanho - 1];
        int j = 0;
        for(int i = 0; i < tamanho; i++) {
            if(array[i].getCodigo() != Integer.parseInt( frame.getCod() ) ) {
                novoArray[j] = array[i];
                ++j;
            }
        }
        return novoArray;
    }
    
    public void procuraElemento(int elemento){
        for( int i = 0; i < AppControleTI.login.length;  i++ ){
            if( AppControleTI.login[i].getCodigo() == elemento ){
                frame.SetConsulta(AppControleTI.login[i].getNome(), AppControleTI.login[i].getSenha(), AppControleTI.login[i].getLogin() );
            }
        }   
    }

}
