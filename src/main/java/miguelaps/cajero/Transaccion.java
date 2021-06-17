
package miguelaps.cajero;

import java.util.Date;

/**
 *
 * @author Miguel-maps
 */
public class Transaccion {
    
    private double cantidad;
    private Date timestamp;
    private String notaInfo;
    private Cuenta cuentaOriginaria;
    
    // Sobrecargar constructores por si una transacción viene con comentario o no.
    public Transaccion(double cantidad, Cuenta cuenta) {
        this.cantidad = cantidad;
        this.cuentaOriginaria = cuenta;
        this.timestamp = new Date();
        this.notaInfo = "";
    }
    
    public Transaccion(double cantidad, String notaInfo, Cuenta cuenta) {
       this(cantidad,cuenta);
       this.notaInfo = notaInfo;
    }
    
    public double getCantidad() {
        return this.cantidad;
    }
    
}
