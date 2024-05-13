class Bavard implements PapotageListener {
    private final String nom;

    public Bavard(String nom) {
        this.nom = nom;
    }

    public void envoyerMessage(Concierge concierge, String sujet, String corps) {
        concierge.transmettreMessage(new PapotageEvent(sujet, corps));
    }

    @Override
    public void onPapotageEventReceived(PapotageEvent event) {
        System.out.println(nom + " a reçu un message : " + event.getSujet() + " - " + event.getCorps());
    }
}