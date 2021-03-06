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
        
        System.out.println("(NOTA: EL PIN DEL USUARIO ES 1234)");

        do {

            System.out.println("\nBienvenido a " + banco.getNombre());
            System.out.println("Introduce ID del usuario: ");
            usuarioID = scan.next();
            System.out.println("Introduce el pin: ");
            pin = scan.next();

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
            System.out.println("");
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
            case 5:
                Cajero.salir();
        }

        // Volver a mostrar el menú principal salvo que el usuario quiera salir.
        if (elegir != 5) {
            Cajero.imprimirMenuUsuario(usuario, scan);
        }
    }

    // Mostrar historial de transacciones de la cuenta de un usuario
    public static void mostrarHistorialTransacciones(Usuario usuario, Scanner scan) {
        int cuenta;

        // Encontrar la cuenta que se está buscando
        do {
            System.out.printf("Introduce el número (1-%d) de la cuenta"
                    + " cuyas transacciones quieras consultar.",
                    usuario.getCuentas());
            System.out.println("");
            cuenta = scan.nextInt() - 1;
            if (cuenta < 0 || cuenta >= usuario.getCuentas()) {
                System.out.println("Esta cuenta no existe. Inténtelo de nuevo.");
            }

        } while (cuenta < 0 || cuenta >= usuario.getCuentas());
        usuario.imprimirHistorialTransaccionesCuenta(cuenta);
    }

    public static void transferirDinero(Usuario usuario, Scanner scan) {
        int desdeCuenta;
        int aCuenta;
        double cantidad;
        double balanceCuenta;

        // Transferencia desde la cuenta emisora
        do {
            System.out.printf("Introduzca el número (1-%d) de la cuenta emisora:",
                    usuario.getCuentas());
            System.out.println("");
            desdeCuenta = scan.nextInt() - 1;

            if (desdeCuenta < 0 || desdeCuenta >= usuario.getCuentas()) {
                System.out.println("Esta cuenta no existe. Inténtelo de nuevo.");
            }

        } while (desdeCuenta < 0 || desdeCuenta >= usuario.getCuentas());

        balanceCuenta = usuario.getBalanceCuenta(desdeCuenta);

        // Cuenta que va a recibir la transferencia
        do {
            System.out.printf("Introduzca el número (1-%d) de la cuenta receptora:",
                    usuario.getCuentas());
            System.out.println("");
            aCuenta = scan.nextInt() - 1;

            if (aCuenta < 0 || aCuenta >= usuario.getCuentas()) {
                System.out.println("Esta cuenta no existe. Inténtelo de nuevo.");
            }

        } while (aCuenta < 0 || aCuenta >= usuario.getCuentas());

        // Elegir cantidad a transferir
        do {
            System.out.printf("Introduzca la cantidad a transferir (max %.02f €):", balanceCuenta);
            System.out.println("");
            cantidad = scan.nextDouble();
            if (cantidad < 0) {
                System.out.println("La cantidad debe ser mayor que cero");
            } else if (cantidad > balanceCuenta) {
                System.out.printf("La cantidad debe ser menor que %.02f €\n", balanceCuenta);
            }
        } while (cantidad < 0 || cantidad > balanceCuenta);

        // Realizar la transferencia
        usuario.addTransaccion(desdeCuenta, -1 * cantidad,
                String.format("Transferir a cuenta %s", usuario.getIdCuenta(aCuenta)));
        usuario.addTransaccion(aCuenta, cantidad,
                String.format("Transferir a cuenta %s", usuario.getIdCuenta(desdeCuenta)));

    }

    public static void retirarFondos(Usuario usuario, Scanner scan) {
        int desdeCuenta;
        double cantidad;
        double balanceCuenta;
        String notaInfo;

        // Elegir cuenta emisora
        do {
            System.out.printf("Introduzca el número (1-%d) de la cuenta que va a"
                    + " retirar los fondos:",
                    usuario.getCuentas());
            System.out.println("");
            desdeCuenta = scan.nextInt() - 1;

            if (desdeCuenta < 0 || desdeCuenta >= usuario.getCuentas()) {
                System.out.println("Esta cuenta no existe. Inténtelo de nuevo.");
            }

        } while (desdeCuenta < 0 || desdeCuenta >= usuario.getCuentas());

        balanceCuenta = usuario.getBalanceCuenta(desdeCuenta);

        // Elegir cantidad a transferir
        do {
            System.out.printf("Introduzca la cantidad a retirar (max %.02f €): €", balanceCuenta);
            System.out.println("");
            cantidad = scan.nextDouble();
            if (cantidad < 0) {
                System.out.println("La cantidad debe ser mayor que cero");
            } else if (cantidad > balanceCuenta) {
                System.out.printf("La cantidad debe ser menor que %.02f €\n", balanceCuenta);
            }
        } while (cantidad < 0 || cantidad > balanceCuenta);

        scan.nextLine();

        System.out.println("Escriba un comentario sobre esta transacción:");
        notaInfo = scan.nextLine();

        // Retiro de fondos
        usuario.addTransaccion(desdeCuenta, -1 * cantidad, notaInfo);
    }

    public static void ingresarFondos(Usuario usuario, Scanner scan) {
        int aCuenta;
        double cantidad;
        double balanceCuenta;
        String notaInfo;

        // Elegir cuenta emisora
        do {
            System.out.printf("Introduzca el ID (1-%d) de la cuenta que"
                    + " recibirá el dinero:", usuario.getCuentas());
            System.out.println("");
            aCuenta = scan.nextInt() - 1;

            if (aCuenta < 0 || aCuenta >= usuario.getCuentas()) {
                System.out.println("Esta cuenta no existe. Inténtelo de nuevo.");
            }

        } while (aCuenta < 0 || aCuenta >= usuario.getCuentas());

        balanceCuenta = usuario.getBalanceCuenta(aCuenta);

        // Elegir cantidad a transferir
        do {
            System.out.println("Introduzca la cantidad a transferir:");
            cantidad = scan.nextDouble();
            if (cantidad < 0) {
                System.out.println("La cantidad debe ser mayor que cero.");
            }
        } while (cantidad < 0);

        scan.nextLine();

        System.out.println("Escriba un comentario sobre esta transacción:");
        notaInfo = scan.nextLine();

        // Retiro de fondos
        usuario.addTransaccion(aCuenta, cantidad, notaInfo);
    }

    public static void salir() {
        System.out.println("");
        System.out.println("Gracias por utilizar nuestros servicios. Hasta pronto.");
        System.out.println("");
        System.exit(0);
    }

}
