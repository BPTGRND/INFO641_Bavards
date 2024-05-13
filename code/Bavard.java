import java.util.ArrayList;
import java.util.List;

public class Bavard implements PapotageListener {
    // ATTRIBUT
    private final List<PapotageListener> listeners = new ArrayList<>();

    // EVENEMENT
    @Override
    public void onPapotageEventReceived(PapotageEvent event) {

    }

    // METHODES
    public void envoyerPapotage(String sujet, String corps) {
        PapotageEvent event = new PapotageEvent(sujet, corps);
        for (PapotageListener listener : listeners) {
            listener.onPapotageEventReceived(event);
        }
    }

    public void ajouterPapotageListener(PapotageListener l) {
        listeners.add(l);
    }

    public void enleverPapotageListener(PapotageListener l) {
        listeners.remove(l);
    }
}
