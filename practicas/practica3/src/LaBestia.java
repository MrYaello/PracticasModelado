public class LaBestia extends Auto {
    public LaBestia() {
        llantas = "Oruga de Tanque.";
        motor = "Off road.";
        carroceria = "Camion.";
        armas = "Lanzallamas.";
    }

    @Override
    public int calcularCosto() {
        return 2500 + 2000 + 2000 + 3000;
    }

    @Override
    public void mostrarCaracteristicas() {
        System.out.println("La bestia.");
        System.out.println("Llantas: " + llantas);
        System.out.println("Motor: " + motor);
        System.out.println("Carroceria: " + carroceria);
        System.out.println("Arma: " + armas);
        System.out.println("Costo: " + calcularCosto() + " pesos.");
    }
}
