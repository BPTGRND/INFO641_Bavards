import java.util.Objects;

class Batiment {
    // ATTRIBUTS
    private final Concierge concierge;
    private final InterfaceBatiment interfaceBatiment;

    // CONSTRUCTEUR
    public Batiment() {
        this.concierge = new Concierge();
        this.interfaceBatiment = new InterfaceBatiment(this);
    }

    // METHODES
    public Bavard creerBavard(String nom) {
        Bavard newBavard = new Bavard(nom);
        concierge.ajouterBavard(newBavard);
        return newBavard;
    }

    public void connecterBavard(Bavard bavard) {
        concierge.connecterBavard(bavard);
        interfaceBatiment.updateBavardList();
    }
    public void deconnecterBavard(Bavard bavard) {
        concierge.deconnecterBavard(bavard);
        interfaceBatiment.updateBavardList();
    }

    public Bavard getBavardByName(String nom) {
        for (Bavard b : concierge.getBavards()) {
            if (Objects.equals(b.getNom(), nom)) {
                return b;
            }
        }
        return null;
    }

    // GETTERS
    public Concierge getConcierge() {
        return concierge;
    }
}