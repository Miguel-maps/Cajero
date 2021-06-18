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
    
    public Banco (String nombre) {
        this.nombre = nombre;
        this.usuarios = new ArrayList<Usuario>();
        this.cuentas = new ArrayList<Cuenta>();
    }

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
        int length = 3;
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

    public Usuario addUsuario(String nombre, String apellidos, String pin) {
        
        // Crear usuario y añadirlo a la lista de usuarios del banco
        Usuario nuevoUsuario = new Usuario(nombre, apellidos, pin, this);
        this.usuarios.add(nuevoUsuario);
        
        // Crear una cuenta de ahorros para el usuario y añadirla tanto
        // al usuario como a la lista de cuentas del banco
        Cuenta nuevaCuenta = new Cuenta("Ahorros", nuevoUsuario, this);
        nuevoUsuario.addCuenta(nuevaCuenta);
        this.cuentas.add(nuevaCuenta);
        
        return nuevoUsuario;
    }
    
    public Usuario userLogin(String usuarioID, String pin) {
        for (Usuario u : this.usuarios) {
            if (u.getID().compareTo(usuarioID) == 0 && u.validarPin(pin)) {
                return u;
            }
        }
        // Si no encontramos al usuario o si el pin es incorrecto
        return null;
    }
    
    public String getNombre() {
        return this.nombre;
    }

}
