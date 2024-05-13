public class Main {
    public static void main(String[] args) {
        Batiment batiment = new Batiment();
        Bavard bavard1 = batiment.creerBavard("Bavard1");
        Bavard bavard2 = batiment.creerBavard("Bavard2");
        Bavard bavard3 = batiment.creerBavard("Bavard3");

        batiment.connecterBavard(bavard2);
        batiment.connecterBavard(bavard3);

        bavard1.envoyerMessage(batiment.getConcierge(), "Important", "C'est cool !");
        bavard2.envoyerMessage(batiment.getConcierge(), "Réunion", "Le 05/10");

        new InterfaceBatiment(batiment);
    }
}
