package miguelaps.cajero;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Miguel-maps
 */
public class Usuario {

    private String nombre;
    private String apellidos;
    private String uniqueID;
    private byte pinHash[];
    private ArrayList<Cuenta> cuentas;

    public Usuario(String nombre, String apellidos, String pin, Banco banco) {
        this.nombre = nombre;
        this.apellidos = apellidos;

        // Almacenar el Hash MD5 del pin en lugar del valor original.
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Error, caught NoSuchAlgorithmException");
            ex.printStackTrace();
            System.exit(1);
        }

        // Crear un nuevo ID único para el usuario
        this.uniqueID = banco.getNuevaIdUser();

        this.cuentas = new ArrayList<Cuenta>();

        // Imprimir mensaje de log
        System.out.printf("Nuevo usuario %s, %s con ID %s creado.\n", apellidos, nombre, this.uniqueID);
    }

    public void addCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }

    public String getID() {
        return uniqueID;
    }

    public String getNombre() {
        return this.nombre;
    }

    public boolean validarPin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Error, caught NoSuchAlgorithmException");
            ex.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    public void imprimirResumenCuentas() {
        System.out.printf("\n\nResumen de la cuenta de %s ", this.nombre);
        for (int i = 0; i < this.cuentas.size(); i++) {
            System.out.printf("%d) %s\n", i + 1, this.cuentas.get(i).getResumen());
        }
        System.out.println("");
    }

    public int getCuentas() {
        return this.cuentas.size();
    }

    public void imprimirHistorialTransaccionesCuenta(int indexCuenta) {
        this.cuentas.get(indexCuenta).imprimirHistorialTransacciones();
    }

    public double getBalanceCuenta(int indexCuenta) {
        return this.cuentas.get(indexCuenta).getBalance();
    }
    
    public String getIdCuenta(int indexCuenta) {
        return this.cuentas.get(indexCuenta).getID();
    }
    
    public void addTransaccion(int indexCuenta, double cantidad, String notaInfo) {
        this.cuentas.get(indexCuenta).addTransaccion(cantidad, notaInfo);
    }

}
