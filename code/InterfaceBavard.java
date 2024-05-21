import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InterfaceBavard extends JFrame implements MessageObserver {
    private final Bavard bavard;
    private final Concierge concierge;
    private final JTextArea textAreaMessage;
    private final JTextField textFieldSujet;
    private final JButton buttonEnvoyer;
    private final DefaultListModel<String> listModelEvenements;
    private final JList<String> listEvenements;

    public InterfaceBavard(Bavard bavard, Concierge concierge) {
        this.bavard = bavard;
        this.concierge = concierge;

        concierge.addObserver(this);

        setTitle("Interface Bavard - " + bavard.getNom());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        textFieldSujet = new JTextField(20);
        textAreaMessage = new JTextArea(5, 20);
        JScrollPane scrollPaneMessage = new JScrollPane(textAreaMessage);

        JPanel panelMessage = new JPanel(new BorderLayout());
        panelMessage.add(textFieldSujet, BorderLayout.NORTH);
        panelMessage.add(scrollPaneMessage, BorderLayout.CENTER);

        add(panelMessage, BorderLayout.NORTH);

        buttonEnvoyer = new JButton("Envoyer");
        add(buttonEnvoyer, BorderLayout.CENTER);

        listModelEvenements = new DefaultListModel<>();
        listEvenements = new JList<>(listModelEvenements);
        JScrollPane scrollPaneEvenements = new JScrollPane(listEvenements);
        add(scrollPaneEvenements, BorderLayout.SOUTH);

        buttonEnvoyer.addActionListener(e -> {
            String sujet = textFieldSujet.getText();
            String messageTexte = textAreaMessage.getText();
            if (!sujet.isEmpty() && !messageTexte.isEmpty()) {
                bavard.envoyerMessage(concierge, sujet, messageTexte);
                textFieldSujet.setText("");
                textAreaMessage.setText("");
                JOptionPane.showMessageDialog(InterfaceBavard.this, "Message envoyé avec succès !");
                updateMessages();
            } else {
                JOptionPane.showMessageDialog(InterfaceBavard.this, "Veuillez saisir un sujet et un message avant d'envoyer.");
            }
        });

        updateMessages();
        setVisible(true);
    }

    public void updateMessages() {
        listModelEvenements.clear();
        List<PapotageEvent> evenements = bavard.getMessages();
        for (PapotageEvent event : evenements) {
            if (!event.getSource().equals(bavard)) {
                listModelEvenements.addElement(event.getSource().getNom() + ": " + event.getSujet() + " - " + event.getCorps());
            }
        }
    }
}
