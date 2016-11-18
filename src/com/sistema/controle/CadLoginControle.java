package com.sistema.controle;

import com.sistema.bean.Login;
import com.sistema.cadastro.CadLoginJIF;

public class CadLoginControle {
    
  private Login cadModelo = new Login();
    
    public  CadLoginControle(CadLoginJIF frame) {
        cadModelo.setCodigo(Integer.parseInt( frame.getCod() ));
        cadModelo.setNome(frame.getNome());
        cadModelo.setLogin(frame.getLogin());
        cadModelo.setSenha(frame.getSenha());
    }
    
    
    public Login getStanceCadmodelo(){
        return cadModelo;
    }  
    
    
    
}
