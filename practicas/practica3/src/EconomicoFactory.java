public class EconomicoFactory implements AutoFactory {
    @Override
    public Auto crearAuto() {
        return new Economico();
    }
}
