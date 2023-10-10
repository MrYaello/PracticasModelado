/**
 * Práctica 3 del curso de Modelado y Programación.
 * @author Yael Lozano Estrada - 319007095, Leslie Geronimo Soto - 320032848
 */

/**
 * Se define la clase AutoFactory que implementa el patron de diseño Factory. Solo servirá para crear objetos
 * de tipo auto.
 */
public class AutoFactory {
    /**
     * Crea un objeto de la clase auto según el tipo especificado.
     * @param auto tipo de auto a crear.
     * @return Auto - Objeto correspondiente al tipo recibido.
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
