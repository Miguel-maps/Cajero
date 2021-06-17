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
    
    public String getResumen() {
        double balance = this.getBalance();
        
        // Hacer formato del resumen si el balance es negativo
        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uniqueID, balance, this.nombre);
        } else {
            return String.format("%s : $(%.02f) : %s", this.uniqueID, balance, this.nombre);
        }
    }
    
    public double getBalance() {
        double balance = 0;
        for (Transaccion t : this.transacciones) {
            balance += t.getCantidad();
        }
        return balance;
    }

}
