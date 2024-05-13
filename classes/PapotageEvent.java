public class PapotageEvent {
    // ATTRIBUTS
    private final String sujet;
    private final String corps;

    // CONSTRUCTEUR
    public PapotageEvent(String sujet, String corps) {
        this.sujet = sujet;
        this.corps = corps;
    }

    // GETTERS
    public String getSujet() {
        return sujet;
    }

    public String getCorps() {
        return corps;
    }
}
