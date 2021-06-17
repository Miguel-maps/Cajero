package miguelaps.cajero;

import java.util.ArrayList;

/**
 *
 * @author Miguel-maps
 */
public class Cuenta {

    private String nombre;
    private String uniqueID;
    private Usuario propietario;
    private ArrayList<Transaccion> transacciones;

    public Cuenta(String nombre, Usuario propietario, Banco banco) {
        this.nombre = nombre;
        this.propietario = propietario;

        // Crear un nuevo ID único para la cuenta
        this.uniqueID = banco.getNuevaIdCuenta();
        
        this.transacciones = new ArrayList<Transaccion>();
                
    }
    
    public String getID() {
        return this.uniqueID;
    }

}
