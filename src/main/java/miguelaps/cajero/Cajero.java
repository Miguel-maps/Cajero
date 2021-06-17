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
            Cajero.imprimirMenuUsuario(usuario, scan);
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

        } while (authUsuario == null); // Loopear hasta que el login sea exitoso.

        return authUsuario;
    }

    // Imprimir datos bancarios
    public static void imprimirMenuUsuario(Usuario usuario, Scanner scan) {
        usuario.imprimirResumenCuentas();
        int elegir;

        do {
            System.out.printf("Bienvenido %s, ¿Qué quieres hacer?", usuario.getNombre());
            System.out.println("[1] Mostrar historial de transacciones");
            System.out.println("[2] Retirar fondos");
            System.out.println("[3] Ingresar fondos");
            System.out.println("[4] Transferir dinero");
            System.out.println("[5] Salir");
            System.out.println("");
            elegir = scan.nextInt();

            if (elegir < 1 || elegir > 5) {
                System.out.println("Comando incorrecto, elija un número del 1 al 5");
            }
        } while (elegir < 1 || elegir > 5);

        // Procesar elección del usuario
        switch (elegir) {

            case 1:
                Cajero.mostrarHistorialTransacciones(usuario, scan);
                break;
            case 2:
                Cajero.retirarFondos(usuario, scan);
                break;
            case 3:
                Cajero.ingresarFondos(usuario, scan);
                break;
            case 4:
                Cajero.transferirDinero(usuario, scan);
                break;
        }
        
        // Volver a mostrar el menú principal salvo que el usuario quiera salir.
        if (elegir != 5) {
            Cajero.imprimirMenuUsuario(usuario, scan);
        }
    }
    
}
