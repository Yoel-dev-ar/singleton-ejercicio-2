public class ControlAccesoBaseDatos {
    private static ControlAccesoBaseDatos instancia;

    private final int limiteConexiones = 3;
    private int conexionesOcupadas;

    private ControlAccesoBaseDatos() {
        this.conexionesOcupadas = 0;
    }

    public static ControlAccesoBaseDatos getInstancia() {
        if (instancia == null) {
            instancia = new ControlAccesoBaseDatos();
        }
        return instancia;
    }

    public void entrarABaseDatos(String sistema) {
        if (conexionesOcupadas < limiteConexiones) {
            conexionesOcupadas++;
            System.out.println("[+] Entró '" + sistema + "'. Ocupadas: " + conexionesOcupadas + "/" + limiteConexiones);
        } else {
            System.out.println("[!] ESPERE: Base de datos llena para '" + sistema + "'");
        }
    }

    public void salirDeBaseDatos(String sistema) {
        if (conexionesOcupadas > 0) {
            conexionesOcupadas--;
            System.out.println("[-] Salió '" + sistema + "'. Ocupadas: " + conexionesOcupadas + "/" + limiteConexiones);
        }
    }
}