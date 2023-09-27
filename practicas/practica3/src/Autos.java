import java.util.*;
public class Autos {
    int dinero;
    Scanner sc;

    public Autos(Scanner sc) {
        dinero = 10000;
        this.sc = sc;
    }

    public void AutosDefault() {
        System.out.println("Autos para elegir:");

        AutoFactory economicoFactory = new EconomicoFactory();
        AutoFactory deportivoFactory = new DeportivoFactory();
        AutoFactory laBestiaFactory = new LaBestiaFactory();

        Auto economico = economicoFactory.crearAuto();
        Auto deportivo = deportivoFactory.crearAuto();
        Auto laBestia = laBestiaFactory.crearAuto();

        System.out.println("-- OPCION 1 - ECONOMICO --");
        economico.mostrarCaracteristicas();

        System.out.println("-- OPCION 2 - DEPORTIVO --");
        deportivo.mostrarCaracteristicas();

        System.out.println("-- OPCION 3 - LA BESTIA --");
        laBestia.mostrarCaracteristicas();

        System.out.print("QUIERO EL CARRO DE LA OPCION: ");
        int op = sc.nextInt();

        if (op == 1) {
            dinero -= economico.calcularCosto();
        } else if (op == 2) {
            dinero -= deportivo.calcularCosto();
        } else if (op == 3) {
            dinero -= laBestia.calcularCosto();
        } 

        System.out.println("Tienes: " + dinero + " pesos.");
    }
}

