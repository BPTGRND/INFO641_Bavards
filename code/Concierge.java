import java.util.*;

class Concierge implements PapotageListener {
    // ATTRIBUTS
    private final List<Bavard> bavards = new ArrayList<>();
    private final List<Bavard> connectedBavards = new ArrayList<>();
    private final List<PapotageEvent> messages = new ArrayList<>();
    private final List<PapotageListener> observers = new ArrayList<>();

    // METHODES
    public void ajouterBavard(Bavard bavard) {
        bavards.add(bavard);
    }

    public void connecterBavard(Bavard bavard) {
        connectedBavards.add(bavard);
        notifyOnlineEvent(bavard);
    }

    public void deconnecterBavard(Bavard bavard) {
        connectedBavards.remove(bavard);
        notifyOffLineEvent(bavard);
    }

    public void addObserver(PapotageListener observer) {
        observers.add(observer);
    }

    private void notifyObservers(PapotageEvent event) {
        for (PapotageListener observer : observers) {
            observer.onPapotageEventReceived(event);
        }
    }

    public void notifyOnlineEvent(Bavard bavard) {
        onPapotageEventReceived(new PapotageEvent("S'est connecté !", "", bavard));
        for (PapotageListener observer : observers) {
            if (observer instanceof OnLineBavardListener) {
                ((OnLineBavardListener) observer).onOnLineBavardEventReceived();
            }
        }
    }

    public void notifyOffLineEvent(Bavard bavard) {
        onPapotageEventReceived(new PapotageEvent("S'est déconnecté !", "", bavard));
        for (PapotageListener observer : observers) {
            if (observer instanceof OffLineBavardListener) {
                ((OffLineBavardListener) observer).onOffLineBavardEventReceived();
            }
        }
    }

    // GETTERS
    public List<Bavard> getBavards() {
        return bavards;
    }

    public List<Bavard> getConnectedBavards() {
        return connectedBavards;
    }

    public List<PapotageEvent> getMessages() {
        return messages;
    }

    // METHODES LISTENER
    @Override
    public void onPapotageEventReceived(PapotageEvent event) {
        messages.add(event);
        for (Bavard bavard : connectedBavards) {
            bavard.onPapotageEventReceived(event);
        }
        notifyObservers(event);
    }
}