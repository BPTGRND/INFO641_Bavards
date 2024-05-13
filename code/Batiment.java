class Batiment {
    private final Concierge concierge;

    public Batiment() {
        this.concierge = new Concierge();
    }

    public Bavard creerBavard(String nom) {
        Bavard bavard = new Bavard(nom);
        concierge.ajouterBavard(bavard);
        return bavard;
    }

    public void connecterBavard(Bavard bavard) {
        concierge.ajouterBavard(bavard);
    }

    public Concierge getConcierge() {
        return concierge;
    }
}