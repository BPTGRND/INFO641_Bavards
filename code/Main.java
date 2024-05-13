public class Main {
    public static void main(String[] args) {
        Batiment batiment = new Batiment();
        Bavard bavard1 = batiment.creerBavard("Bavard1");
        Bavard bavard2 = batiment.creerBavard("Bavard2");

        bavard1.envoyerMessage(batiment.getConcierge(), "Sujet du message", "Corps du message");
        bavard2.envoyerMessage(batiment.getConcierge(), "Sujet du message", "Corps du message");
    }
}
