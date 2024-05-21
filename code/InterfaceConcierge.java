import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InterfaceConcierge extends JFrame implements MessageObserver {
    private final Batiment batiment;
    private final DefaultListModel<String> listModelMessages;
    private final JList<String> listMessages;

    public InterfaceConcierge(Batiment batiment) {
        this.batiment = batiment;

        setTitle("Interface Concierge");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModelMessages = new DefaultListModel<>();
        listMessages = new JList<>(listModelMessages);
        JScrollPane scrollPane = new JScrollPane(listMessages);
        add(scrollPane, BorderLayout.CENTER);

        batiment.getConcierge().addObserver(this);
        updateMessages();

        setVisible(true);
    }

    public void updateMessages() {
        listModelMessages.clear();
        List<PapotageEvent> messages = batiment.getConcierge().getMessages();
        for (PapotageEvent message : messages) {
            listModelMessages.addElement(message.getSource().getNom() + ": " + message.getSujet() + " - " + message.getCorps());
        }
    }
}
