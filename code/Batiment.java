import java.util.Objects;

class Batiment {
    private final Concierge concierge;
    private final InterfaceBatiment interfaceBatiment;

    public Batiment() {
        this.concierge = new Concierge();
        this.interfaceBatiment = new InterfaceBatiment(this);
    }

    public Bavard creerBavard(String nom) {
        Bavard newBavard = new Bavard(nom);
        concierge.ajouterBavard(newBavard);
        return newBavard;
    }

    public void connecterBavard(Bavard bavard) {
        concierge.connecterBavard(bavard);
    }
    public void deconnecterBavard(Bavard bavard) {
        concierge.deconnecterBavard(bavard);
    }

    public Concierge getConcierge() {
        return concierge;
    }

    public void updateBavardListInterface() {
        interfaceBatiment.updateBavardList();
    }

    public Bavard getBavard(String nom) {
        for (Bavard b : concierge.getBavards()) {
            if (Objects.equals(b.getNom(), nom)) {
                return b;
            }
        }
        return null;
    }
}