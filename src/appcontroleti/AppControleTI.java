
package appcontroleti;

public class AppControleTI {
    
    static Login login[]     = new Login[3];
    static Cliente cliente[] = new Cliente[20];
    static Servico servico[] = new Servico[10];

    static int tlogins  = 0; //total login
    static int tcliente = 0; //total cliente
    static int tservico = 0; //total servico
    
    public static void main(String[] args) {
        TelaLogin login = new TelaLogin();
        login.setVisible( true );
    }
}
