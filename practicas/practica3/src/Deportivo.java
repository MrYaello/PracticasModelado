public class Deportivo extends Auto {
    public Deportivo() {
        llantas = "Deportivas.";
        motor = "Deporivo.";
        carroceria = "Camion";
        armas = "Metralleta";
    }

    @Override
    public int calcularCosto() {
        return 1500 + 2000 + 2000 + 2500;
    }

    @Override
    public void mostrarCaracteristicas() {
        System.out.println("Manzda 3. Deportivo.");
        System.out.println("Llantas: " + llantas);
        System.out.println("Motor: " + motor);
        System.out.println("Carroceria: " + carroceria);
        System.out.println("Arma: " + armas);
        System.out.println("Costo: " + calcularCosto() + " pesos.");
    }
}
