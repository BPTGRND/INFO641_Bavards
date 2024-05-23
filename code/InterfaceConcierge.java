import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InterfaceConcierge extends JFrame implements PapotageListener {
    // ATTRIBUTS
    private final Batiment batiment;
    private final DefaultListModel<String> listModelMessages;

    // CONSTRUCTEUR
    public InterfaceConcierge(Batiment batiment) {
        this.batiment = batiment;

        // CONSTRUCTION DE L'INTERFACE
        setTitle("Interface Concierge");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModelMessages = new DefaultListModel<>();
        JList<String> listMessages = new JList<>(listModelMessages);
        JScrollPane scrollPane = new JScrollPane(listMessages);
        add(scrollPane, BorderLayout.CENTER);

        listModelMessages.addElement("Aucun message pour le moment...");

        setVisible(true);
    }

    @Override
    public void onPapotageEventReceived(PapotageEvent event) {
        listModelMessages.clear();
        List<PapotageEvent> messages = batiment.getConcierge().getMessages();
        for (PapotageEvent message : messages) {
            listModelMessages.addElement(message.getSource().getNom() + ": " + message.getSujet() + " - " + message.getCorps());
        }
    }
}
