public class Main {
    public static void main(String[] args) {
        Batiment batiment = new Batiment();
        Bavard bavard1 = batiment.creerBavard("Bavard1");
        Bavard bavard2 = batiment.creerBavard("Bavard2");
        Bavard bavard3 = batiment.creerBavard("Bavard3");

        batiment.connecterBavard(bavard2);
        batiment.connecterBavard(bavard3);

        InterfaceBatiment interfaceBatiment = new InterfaceBatiment(batiment);
        InterfaceSelectionBavard interfaceSelectionBavard = new InterfaceSelectionBavard(batiment);
        interfaceBatiment.addObserver(interfaceSelectionBavard);
    }
}
