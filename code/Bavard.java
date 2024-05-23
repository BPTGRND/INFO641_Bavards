import java.util.ArrayList;
import java.util.List;

class Bavard implements PapotageListener {
    // ATTRIBUTS
    private final String nom;
    private final List<PapotageEvent> messages = new ArrayList<>();

    // CONSTRUCTEUR
    public Bavard(String nom) {
        this.nom = nom;
    }

    // METHODES
    public void envoyerMessage(Concierge concierge, String sujet, String corps) {
        concierge.transmettreMessage(new PapotageEvent(sujet, corps, this));
    }

    // METHODES LISTENER
    @Override
    public void onPapotageEventReceived(PapotageEvent event) {
        if (!event.getSource().equals(this)) {
            messages.add(event);
        }
    }

    // GETTERS
    public String getNom() {
        return nom;
    }

    public List<PapotageEvent> getMessages() {
        return messages;
    }
}