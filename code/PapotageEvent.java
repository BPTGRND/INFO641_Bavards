public class PapotageEvent {
    // ATTRIBUTS
    private final String sujet;
    private final String corps;
    private final Bavard source;

    // CONSTRUCTEUR
    public PapotageEvent(String sujet, String corps, Bavard source) {
        this.source = source;
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

    public Bavard getSource() {
        return source;
    }

}
