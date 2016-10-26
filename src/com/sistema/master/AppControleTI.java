
package com.sistema.master;

import com.sistema.bean.Login;
import com.sistema.bean.Servico;
import com.sistema.bean.Cliente;
import com.sistema.controle.Exceptions;

public class AppControleTI {
    public static Login login[]     = new Login[3];
    public static Cliente cliente[] = new Cliente[20];
    public static Servico servico[] = new Servico[10];

    public static int tlogins  = 0; //total login
    public static int tcliente = 0; //total cliente
    public static int tservico = 0; //total servico
    
    public static void main(String[] args) throws Exceptions {
        TelaLogin login = new TelaLogin();
        login.setVisible( true );
    }
}
