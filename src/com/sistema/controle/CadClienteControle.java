package com.sistema.controle;

import com.sistema.bean.Cliente;
import com.sistema.cadastro.CadClienteJIF;

public class CadClienteControle {
    
  private Cliente cadModelo = new Cliente();
    
    public  CadClienteControle(CadClienteJIF frame) {
        cadModelo.setCodigo(Integer.parseInt( frame.getCod()));
        cadModelo.setNome(frame.getNome());
        cadModelo.setEndereco(frame.getEndereco());
        cadModelo.setBairro(frame.getBairro());
        cadModelo.setCidade(frame.getCidade());
        cadModelo.setEstado(frame.getEstado());
    }
    
    
    public Cliente getStanceCadmodelo(){
        return cadModelo;
    }  
        
    
}
