class Bavard implements PapotageListener {
    private final String nom;

    public Bavard(String nom) {
        this.nom = nom;
    }

    public void envoyerMessage(Concierge concierge, String sujet, String corps) {
        concierge.transmettreMessage(new PapotageEvent(sujet, corps, this));
    }

    @Override
    public void onPapotageEventReceived(PapotageEvent event) {
        if (!event.getSource().equals(this)) {
            System.out.println(nom + " a reçu un message de " + event.getSource().getNom() + " : " + event.getSujet() + " - " + event.getCorps());
        }
    }

    public String getNom() {
        return nom;
    }
}