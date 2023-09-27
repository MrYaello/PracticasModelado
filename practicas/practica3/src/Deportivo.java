public class Deportivo extends Auto {
    public Deportivo() {
        nombre = "Deportivo";
        llantas = "Deportivas";
        motor = "Deportivo";
        carroceria = "Camion";
        armas = "Metralleta";
        blindaje = "Reforzado";
        ataque = 0;
        defensa = 0;
        velocidad = 0;
        costo = 1500 + 2000 + 2000 + 2500;
    }

    @Override
    public void mostrarCaracteristicas() {
        System.out.println(nombre + ": Mazda 3.");
        System.out.println("Llantas: " + llantas);
        System.out.println("Motor: " + motor);
        System.out.println("Carroceria: " + carroceria);
        System.out.println("Arma: " + armas);
        System.out.println("Costo: " + obtenerCosto() + " pesos.");
    }
}
