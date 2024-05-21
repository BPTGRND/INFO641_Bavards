public class Main {
    public static void main(String[] args) {
        Batiment batiment = new Batiment();

        new InterfaceBatiment(batiment);
        new InterfaceConcierge(batiment);
    }
}
