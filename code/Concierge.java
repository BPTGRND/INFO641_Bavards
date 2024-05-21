import java.util.*;

class Concierge implements PapotageListener {
    private final List<Bavard> bavards = new ArrayList<>();
    private final List<Bavard> connectedBavards = new ArrayList<>();
    private final List<PapotageEvent> messages = new ArrayList<>();

    private final List<MessageObserver> observers = new ArrayList<>();

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
        messages.add(event);
        for (Bavard bavard : connectedBavards) {
            bavard.onPapotageEventReceived(event);
        }
        notifyObservers();
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

    public void addObserver(MessageObserver observer) {
        observers.add(observer);
    }

    public void onPapotageEventReceived(PapotageEvent event) {
    }

    private void notifyObservers() {
        for (MessageObserver observer : observers) {
            observer.updateMessages();
        }
    }

    public void notifyOnlineEvent(Bavard bavard) {
        transmettreMessage(new PapotageEvent("S'est connecté !", "", bavard));
        for (MessageObserver observer : observers) {
            if (observer instanceof OnLineBavardListener) {
                ((OnLineBavardListener) observer).onOnLineBavardEventReceived();
            }
        }
    }

    public void OffLineBavardEvent(Bavard bavard) {
        transmettreMessage(new PapotageEvent("S'est déconnecté !", "", bavard));
        for (MessageObserver observer : observers) {
            if (observer instanceof OffLineBavardListener) {
                ((OffLineBavardListener) observer).onOffLineBavardEventReceived();
            }
        }
    }
}

interface MessageObserver {
    void updateMessages();
}