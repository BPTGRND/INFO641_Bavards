import java.util.ArrayList;
import java.util.List;

class Bavard implements PapotageListener {
    private final String nom;
    private final List<PapotageEvent> messages = new ArrayList<>();

    public Bavard(String nom) {
        this.nom = nom;
    }

    public void envoyerMessage(Concierge concierge, String sujet, String corps) {
        concierge.transmettreMessage(new PapotageEvent(sujet, corps, this));
    }

    @Override
    public void onPapotageEventReceived(PapotageEvent event) {
        if (!event.getSource().equals(this)) {
            messages.add(event);
        }
    }

    public String getNom() {
        return nom;
    }

    public List<PapotageEvent> getMessages() {
        return messages;
    }
}