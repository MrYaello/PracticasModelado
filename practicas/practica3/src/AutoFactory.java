/**
 * Práctica 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */
public class AutoFactory {
    /**
     * 
     * @param auto
     * @return
     */
    public Auto crearAuto(String auto) {
        return switch (auto) {
            case "economico" -> new Economico();
            case "deportivo" -> new Deportivo();
            case "bestia" -> new LaBestia();
            case "custom" -> new AutoPersonalizado();
            default -> null;
        };
    }
}
