public class Economico extends Auto {
    public Economico() {
        llantas = "Simple.";
        motor = "Diesel.";
        carroceria = "Casual.";
        armas = "Arpones.";
    }

    @Override
    public int calcularCosto() {
        return 1000 + 1000 + 1000 + 1500;
    }

    @Override
    public void mostrarCaracteristicas() {
        System.out.println("Nissan Versa.");
        System.out.println("Llantas: " + llantas);
        System.out.println("Motor: " + motor);
        System.out.println("Carroceria: " + carroceria);
        System.out.println("Arma: " + armas);
        System.out.println("Costo: " + calcularCosto() + " pesos.");
    }
}

