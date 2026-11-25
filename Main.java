public class Main {
    public static void main(String[] args) {
        ControlAccesoBaseDatos control1 = ControlAccesoBaseDatos.getInstancia();
        ControlAccesoBaseDatos control2 = ControlAccesoBaseDatos.getInstancia();

        control1.entrarABaseDatos("Transferencias");
        control2.entrarABaseDatos("Tarjetas");
        control1.entrarABaseDatos("Plazos Fijos");

        // El cuarto intento debe esperar porque el límite es 3
        control2.entrarABaseDatos("Préstamos");

        // Se libera un lugar y ahora sí puede entrar Préstamos
        control1.salirDeBaseDatos("Transferencias");
        control2.entrarABaseDatos("Préstamos");

        System.out.println("¿Es el mismo control?: " + (control1 == control2));
    }
}