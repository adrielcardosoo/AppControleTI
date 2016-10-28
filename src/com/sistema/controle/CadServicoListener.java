package com.sistema.controle;

import com.sistema.bean.Servico;
import com.sistema.cadastro.CadServicoJIF;
import com.sistema.master.AppControleTI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            
            if (cod.length() == 0 || cod == null) {
                JOptionPane.showMessageDialog(null, "Codigo invalido!");
                return;
            }
            
            for (int i=1; i<=AppControleTI.tservico; i++){
                if (cod.equals(AppControleTI.servico[i].getCodigo())){
                    JaCadastrado = true;
                }

                if (JaCadastrado){
                    JOptionPane.showMessageDialog(null, "Codigo do Serviço já cadastrado!");
                    break;
                }
            }

            frame.inserirLogin( AppControleTI.tservico++ );
            
            log = new Log(ultimoLogin.lerArquivo() + " Cadastrou um serviço");
            frame.LimpaForm();
        }
        
        if ("Excluir".equals(evento.getActionCommand())) {
            if (frame.getCod().length() == 0 || frame.getCod() == null) {
                JOptionPane.showMessageDialog(null, "Codigo invalido!");
                return;
            } else {
                log = new Log(ultimoLogin.lerArquivo() + " Excluiu um serviço");
            }
            
        if ("Pesquisar".equals(evento.getActionCommand())){
            procuraElemento( Integer.parseInt( frame.getCod() ) );
        }

        }
    }
    
    private void removeItem(Servico[] array) {
        AppControleTI.servico = pegaNovoArray(array, array.length);
    }
    
    private Servico[] pegaNovoArray(Servico[] array, int tamanho) {
        Servico[] novoArray = new Servico[tamanho - 1];
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
        for( int i = 0; i < AppControleTI.servico.length;  i++ ){
            if( AppControleTI.servico[i].getCodigo() == elemento ){
                frame.SetConsulta(AppControleTI.servico[i].getDescricao(), String.valueOf(AppControleTI.servico[i].getTempoMedio()), String.valueOf(AppControleTI.servico[i].getValor()) );
            }
        }   
    }

}
