package miguelaps.cajero;

/**
 *
 * @author Miguel-maps
 */
import java.util.ArrayList;

public class Banco {

    private String nombre;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Cuenta> cuentas;

    public String getNuevaIdUser() {
        return "";
    }

    public String getNuevaIdBanco() {
        return "";
    }

    public void addCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }

}
