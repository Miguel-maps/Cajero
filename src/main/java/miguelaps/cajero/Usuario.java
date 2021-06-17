
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
    
}
