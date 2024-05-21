import java.util.*;

class Concierge implements PapotageListener {
    private final List<Bavard> bavards = new ArrayList<>();
    private final List<Bavard> connectedBavards = new ArrayList<>();
    private final List<PapotageEvent> messages = new ArrayList<>();

    public void ajouterBavard(Bavard bavard) {
        bavards.add(bavard);
    }

    public void connecterBavard(Bavard bavard) {
        connectedBavards.add(bavard);
    }

    public void deconnecterBavard(Bavard bavard) {
        connectedBavards.remove(bavard);
    }

    public void transmettreMessage(PapotageEvent event) {
        for (Bavard bavard : connectedBavards) {
            bavard.onPapotageEventReceived(event);
        }
    }

    @Override
    public void onPapotageEventReceived(PapotageEvent event) {

    }

    public List<Bavard> getBavards() {
        return bavards;
    }

    public List<Bavard> getConnectedBavards() {
        return connectedBavards;
    }

    public List<PapotageEvent> getMessages() {
        return messages;
    }

    public Bavard getBavardByName(String nom) {
        for (Bavard b : bavards) {
            if (Objects.equals(b.getNom(), nom)) {
                return b;
            }
        }
        return null;
    }
}