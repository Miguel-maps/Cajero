package miguelaps.cajero;

/**
 *
 * @author Miguel-maps
 */
import java.util.ArrayList;
import java.util.Random;

public class Banco {

    private String nombre;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Cuenta> cuentas;

    public String getNuevaIdUser() {
        String id;
        Random rng = new Random();
        int length = 6;
        boolean noUnico;

        // Loopear hasta conseguir un ID único
        do {

            // Generar el número
            id = "";
            for (int i = 0; i < length; i++) {
                id += ((Integer) rng.nextInt(10)).toString();
            }

            // Comprobar que sea único
            noUnico = false;
            for (Usuario u : this.usuarios) {
                if (id.compareTo(u.getID()) == 0) {
                    noUnico = true;
                    break;
                }
            }

        } while (noUnico);

        return id;
    }

    public String getNuevaIdCuenta() {
        String id;
        Random rng = new Random();
        int length = 8;
        boolean noUnico;

        // Loopear hasta conseguir un ID único
        do {

            // Generar el número
            id = "";
            for (int i = 0; i < length; i++) {
                id += ((Integer) rng.nextInt(10)).toString();
            }

            // Comprobar que sea único
            noUnico = false;
            for (Cuenta c : this.cuentas) {
                if (id.compareTo(c.getID()) == 0) {
                    noUnico = true;
                    break;
                }
            }

        } while (noUnico);

        return id;
    }

    public void addCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
    }

}
