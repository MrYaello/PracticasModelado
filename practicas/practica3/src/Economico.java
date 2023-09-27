public class Economico extends Auto {
    public Economico() {
        nombre = "Economico";
        llantas = "Simple";
        motor = "Diesel";
        carroceria = "Casual";
        armas = "Arpones";
        blindaje = "Simple";
        ataque = 0;
        defensa = 0;
        velocidad = 0;
        costo = 1000 + 1000 + 1000 + 1500;
    }

    @Override
    public void mostrarCaracteristicas() {
        System.out.println(nombre + ": Nissan Versa.");
        System.out.println("Llantas: " + llantas);
        System.out.println("Motor: " + motor);
        System.out.println("Carroceria: " + carroceria);
        System.out.println("Arma: " + armas);
        System.out.println("Costo: " + obtenerCosto() + " pesos.");
    }
}

