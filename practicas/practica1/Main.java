public class Main {
    public static void main(String[] args) {
        Mascota m1 = new Mascota("Bruno", 6, "Perro", "Salchicha", "Cafe", true, "Ser acariciado");
        Mascota m2 = new Mascota("Michi", 2, false, "Jugar");
        Mascota m3 = new Mascota("Poti", 2, "Perico", "Australiano", "Verde", false, "Ser alimentado");
        Mascota m4 = new Mascota("Pepo", 9, "Perro", "Chihuahua", "Negro", true, "Perseguir ratones");
        Mascota m5 = new Mascota("Suki", 6, false, "Ladrar");
        Mascota m6 = new Mascota("Bruno", 4, "Gato", "Siames", "Beige", true, "Odiar su existencia");
        try {
            m1.contar(m2);
            m1.contar(m3);
            m1.contar(m4);
            m1.contar(m5);
            m1.contar(m6);
            m2.contar(m1);
            m3.contar(m1);
            m4.contar(m1);
            m5.contar(m1);
            m1.contar(m1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(m1);
        System.out.println(m2);
    }
}
