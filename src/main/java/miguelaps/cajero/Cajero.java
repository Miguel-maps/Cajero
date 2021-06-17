
package miguelaps.cajero;

/**
 *
 * @author Miguel-maps
 */

import java.util.Scanner;

public class Cajero {


    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        // Crear banco, añadirle un usuario y crearle una cuenta
        Banco banco = new Banco("Banco de Ejemplo");
        Usuario usuario = banco.addUsuario("Juan", "Sánchez", "1234");
        Cuenta cuentaCorriente = new Cuenta("Cuenta Corriente", usuario, banco);
        usuario.addCuenta(cuentaCorriente);
        // banco.addCuenta(cuentaCorriente);
        
        Usuario usuarioActual;
        while (true) {
            usuarioActual = Cajero.menuPrincipal(banco, scan);
            // Cajero.imprimirMenuUsuario(banco, scan);
        }

    }
    
    public static Usuario menuPrincipal(Banco banco, Scanner scan) {
        String usuarioID;
        String pin;
        Usuario authUsuario;
        
        
        do {
            
            System.out.printf("\n\nBienvenido a %s\n\n", banco.getNombre());
            System.out.print("Introduce ID del usuario: ");
            usuarioID = scan.nextLine();
            System.out.printf("Introduce el pin: ");
            pin = scan.nextLine();
            
            // Comprobar que el ID y el pin coincidan
            
            authUsuario = banco.userLogin(usuarioID, pin);
            if (authUsuario == null) {
                System.out.println("Pin o ID incorrectos. Inténtelo de nuevo.");
            }
            
        } while(authUsuario == null); // Loopear hasta que el login sea exitoso.
        
        return authUsuario;
    }
    

    
}
