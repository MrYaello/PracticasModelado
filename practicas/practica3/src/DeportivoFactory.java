public class DeportivoFactory implements AutoFactory {
    @Override
    public Auto crearAuto() {
        return new Deportivo();
    }
}
