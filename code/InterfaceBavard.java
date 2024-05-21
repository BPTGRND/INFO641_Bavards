import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InterfaceBavard extends JFrame implements MessageObserver {
    private final Bavard bavard;
    private final JTextArea textAreaMessage;
    private final JTextField textFieldSujet;
    private final DefaultListModel<String> listModelEvenements;

    public InterfaceBavard(Bavard bavard, Batiment batiment) {
        this.bavard = bavard;

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

        JButton buttonEnvoyer = new JButton("Envoyer");
        add(buttonEnvoyer, BorderLayout.CENTER);

        listModelEvenements = new DefaultListModel<>();
        JList<String> listEvenements = new JList<>(listModelEvenements);
        JScrollPane scrollPaneEvenements = new JScrollPane(listEvenements);
        add(scrollPaneEvenements, BorderLayout.SOUTH);

        buttonEnvoyer.addActionListener(e -> {
            String sujet = textFieldSujet.getText();
            String messageTexte = textAreaMessage.getText();
            if (!sujet.isEmpty() && !messageTexte.isEmpty()) {
                bavard.envoyerMessage(batiment.getConcierge(), sujet, messageTexte);
                textFieldSujet.setText("");
                textAreaMessage.setText("");
                JOptionPane.showMessageDialog(InterfaceBavard.this, "Message envoyé avec succès !");
                updateMessages();
            } else {
                JOptionPane.showMessageDialog(InterfaceBavard.this, "Veuillez saisir un sujet et un message avant d'envoyer.");
            }
        });

        batiment.getConcierge().addObserver(this);
        updateMessages();
        setVisible(true);
    }

    public void updateMessages() {
        listModelEvenements.clear();
        List<PapotageEvent> messages = bavard.getMessages();
        if (messages.isEmpty()) {
            listModelEvenements.addElement("Aucun message reçu pour le moment...");
        } else {
            for (PapotageEvent message : messages) {
                if (!message.getSource().equals(bavard)) {
                    listModelEvenements.addElement(message.getSource().getNom() + ": " + message.getSujet() + " - " + message.getCorps());
                }
            }
        }
    }
}
