import java.util.ArrayList;
import java.util.List;

public class Concierge implements PapotageListener {
    // ATTRIBUT
    private final List<PapotageListener> listeners = new ArrayList<>();

    // EVENEMENT
    @Override
    public void onPapotageEventReceived(PapotageEvent event) {
        for (PapotageListener listener : listeners) {
            listener.onPapotageEventReceived(event);
        }
    }

    // METHODES
    public void ajouterPapotageListener(PapotageListener l) {
        listeners.add(l);
    }

    public void enleverPapotageListener(PapotageListener l) {
        listeners.remove(l);
    }

}
