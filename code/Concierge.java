import java.util.ArrayList;
import java.util.List;

class Concierge implements PapotageListener {
    private final List<Bavard> bavards = new ArrayList<>();

    public void ajouterBavard(Bavard bavard) {
        bavards.add(bavard);
    }

    public void transmettreMessage(PapotageEvent event) {
        for (PapotageListener listener : bavards) {
            listener.onPapotageEventReceived(event);
        }
    }

    @Override
    public void onPapotageEventReceived(PapotageEvent event) {

    }
}