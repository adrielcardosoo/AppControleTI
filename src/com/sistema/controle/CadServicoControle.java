package com.sistema.controle;

import com.sistema.bean.Servico;
import com.sistema.cadastro.CadServicoJIF;

public class CadServicoControle {
    
  private Servico cadModelo = new Servico();
    
    public  CadServicoControle(CadServicoJIF frame) {
        Double valor, tempo;
        
        valor = frame.getValor();
        tempo = frame.getTempo();

        cadModelo.setCodigo(Integer.parseInt( frame.getCod()));
        cadModelo.setDescricao(frame.getDescricao());
        cadModelo.setValor(valor);
        cadModelo.setTempoMedio(tempo);
    }
    
    
    public Servico getStanceCadmodelo(){
        return cadModelo;
    }  
        
    
}
