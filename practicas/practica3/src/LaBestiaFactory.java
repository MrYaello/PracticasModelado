public class LaBestiaFactory implements AutoFactory {
    @Override
    public Auto crearAuto() {
        return new LaBestia();
    }
}

