import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Concierge implements PapotageListener {
    private final List<Bavard> bavards = new ArrayList<>();

    public void ajouterBavard(Bavard bavard) {
        bavards.add(bavard);
    }

    public void supprimerBavard(Bavard bavard) {
        bavards.remove(bavard);
    }

    public void transmettreMessage(PapotageEvent event) {
        for (Bavard bavard : bavards) {
            bavard.onPapotageEventReceived(event);
        }
    }

    @Override
    public void onPapotageEventReceived(PapotageEvent event) {

    }
}