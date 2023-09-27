public class Economico extends Auto {
    public Economico() {
        nombre = "Economico";
        llantas = "Simple";
        motor = "Diesel";
        carroceria = "Casual";
        armas = "Arpones";
        blindaje = "Simple";
        ataque = 100 + 180 + 80 + 80 + 100;
        defensa = 50 + 50 + 60 + 80 + 50;
        velocidad = 20 + 100 + 30 + 40 + 40;
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

