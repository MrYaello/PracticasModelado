import java.util.Scanner;

public class Autos{
    String llantas;
        int simple = 1000;
        int deportivas = 1500;
        int offRoad = 2000;
        int orugaTanque = 2500;

    String motor;
        int deportivo = 1500;
        int diesel = 1000;
        int turbo= 2000;

    String carroceria;
        int casual = 1000;
        int camion = 2000;
        int tanque = 5000;

    String armas;
        int arpones = 1500;
        int lanzallamas = 2000;
        int caniones = 2500;
        int sierra = 3000;
        int metralleta = 3500;

    int dinero = 10000;
    int gasto = 0;

    public void AutosDefault(){
        System.out.println("Autos para elegir: ");
        int economico = simple+diesel+casual+arpones;
            System.out.println("-- OPCION 1 - ECONOMICO --");
            System.out.println("Nissan Versa. \n Llantas: Simple. \n Motor: Diesel. \n Carroceria: Casual. \n Arma: Arpon.");
            System.out.println("Costo: "+economico+" pesos.");
        int economico1 = deportivas+deportivo+camion+lanzallamas;
            System.out.println("-- OPCION 2 - DEPORTIVO --");
            System.out.println("Mazda 3 Deportivo. \n Llantas: Deportivas. \n Motor: Deportivo. \n Carroceria: Camion. \n Arma: Lanzallamas.");
            System.out.println("Costo: "+economico1+" pesos.");
        int economico2 = offRoad+turbo+camion+metralleta;
            System.out.println("-- OPCION 3 - LA BESTIA --");
            System.out.println("LA BESTIA. \n Llantas: offRoad. \n Motor: turbo. \n Carroceria: Camion. \n Arma: arpon.");
            System.out.println("Costo: "+economico2+" pesos.");

        Scanner sc = new Scanner(System.in);
        int op = sc.nextInt();
            
        if(op == 1){
            dinero = dinero-economico;
            System.out.println("Tienes: "+dinero+ " pesos.");
        }else if(op == 2){
            dinero = dinero -economico1;
            System.out.println("Tienes: "+dinero+ " pesos.");
        }else if(op == 3){
            dinero = dinero -economico2;
            System.out.println("Tienes: "+dinero+ " pesos.");
        }else{
            System.out.println("Puedes personalizar uno en la seccion de menu.");
            return;
        }



    }

}