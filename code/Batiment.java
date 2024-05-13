import java.util.ArrayList;
import java.util.List;

public class Batiment {
    // ATTRIBUTS
    private final Concierge concierge;
    private final List<Bavard> bavards = new ArrayList<>();

    // CONSTRUCTEUR
    public Batiment() {
        concierge = new Concierge();
    }

    // METHODES
    public void ajouterBavard(Bavard b) {
        bavards.add(b);
        concierge.ajouterPapotageListener(b);
    }
}
