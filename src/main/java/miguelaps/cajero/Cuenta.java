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
        
        // Añadir la cuenta a las ArrayList del usuario y el banco en el momento en que se crean
        propietario.addCuenta(this);
        banco.addCuenta(this);
        
    }
    
    public String getID() {
        return this.uniqueID;
    }

}
