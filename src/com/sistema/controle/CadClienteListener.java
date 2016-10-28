package com.sistema.controle;

import com.sistema.cadastro.CadClienteJIF;
import com.sistema.master.AppControleTI;
import com.sistema.bean.Cliente;
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
                if (cod.equals(AppControleTI.cliente[i].getCodigo())){
                    JaCadastrado = true;
                }

                if (JaCadastrado){
                    JOptionPane.showMessageDialog(null, "Codigo do Cliente já cadastrado!");
                    break;
                }
            }

            frame.inserirCliente( AppControleTI.tcliente++ );
            
            log = new Log(ultimoLogin.lerArquivo() + " Cadastrou um cliente");
            frame.LimpaForm();
        }
        
        if ("Excluir".equals(evento.getActionCommand())) {
            if (frame.getCod().length() == 0 || frame.getCod() == null) {
                JOptionPane.showMessageDialog(null, "Codigo invalido!");
            } else {
                removeItem( AppControleTI.cliente );
                log = new Log(ultimoLogin.lerArquivo() + " Excluiu um cliente");
            }
        }
        
        if ("Pesquisar".equals(evento.getActionCommand())){
            procuraElemento( Integer.parseInt( frame.getCod() ) );
        }
    }
    
    private void removeItem(Cliente[] array) {
        AppControleTI.cliente = pegaNovoArray(array, array.length);
    }
    
    private Cliente[] pegaNovoArray(Cliente[] array, int tamanho) {
        Cliente[] novoArray = new Cliente[tamanho - 1];
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
        for( int i = 0; i < AppControleTI.cliente.length;  i++ ){
            if( AppControleTI.cliente[i].getCodigo() == elemento ){
                frame.SetConsulta(AppControleTI.cliente[i].getNome(), AppControleTI.cliente[i].getBairro(), AppControleTI.cliente[i].getCidade(), AppControleTI.cliente[i].getEndereco(), AppControleTI.cliente[i].getEstado() );
            }
        }   
    }

}
