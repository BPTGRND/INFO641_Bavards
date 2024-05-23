import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InterfaceBavard extends JFrame implements MessageListener, OnLineBavardListener, OffLineBavardListener {
    // ATTRIBUTS
    private final Bavard bavard;
    private final Batiment batiment;
    private final JTextField textFieldSujet;
    private final JTextArea textAreaMessage;
    private final DefaultListModel<String> listModelEvenements;
    private final DefaultListModel<String> listModelConnectedBavards;

    // CONSTRUCTEUR
    public InterfaceBavard(Bavard bavard, Batiment batiment) {
        this.bavard = bavard;
        this.batiment = batiment;

        // CONSTRUCTION DE L'INTERFACE
        setTitle("Interface Bavard - " + bavard.getNom());
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelMain = new JPanel(new BorderLayout());

        JPanel panelMessage = new JPanel(new BorderLayout());
        panelMessage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelSujet = new JPanel(new BorderLayout());
        JLabel labelSujet = new JLabel("Sujet :");
        textFieldSujet = new JTextField();
        panelSujet.add(labelSujet, BorderLayout.WEST);
        panelSujet.add(textFieldSujet, BorderLayout.CENTER);
        panelMessage.add(panelSujet, BorderLayout.NORTH);

        JPanel panelMessageText = new JPanel(new BorderLayout());
        JLabel labelMessage = new JLabel("Message :");
        panelMessageText.add(labelMessage, BorderLayout.NORTH);

        textAreaMessage = new JTextArea(5, 40);
        textAreaMessage.setLineWrap(true);
        JScrollPane scrollPaneMessage = new JScrollPane(textAreaMessage);
        scrollPaneMessage.setPreferredSize(new Dimension(600, 100));
        panelMessageText.add(scrollPaneMessage, BorderLayout.CENTER);

        panelMessage.add(panelMessageText, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton buttonEnvoyer = new JButton("Envoyer");
        JButton buttonDeconnecter = new JButton("Déconnecter");
        panelButtons.add(buttonEnvoyer);
        panelButtons.add(buttonDeconnecter);
        panelMessage.add(panelButtons, BorderLayout.SOUTH);

        panelMain.add(panelMessage, BorderLayout.NORTH);

        JPanel panelLists = new JPanel(new GridLayout(1, 2));

        JPanel panelConnectedBavards = new JPanel(new BorderLayout());
        panelConnectedBavards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        listModelConnectedBavards = new DefaultListModel<>();
        JList<String> listConnectedBavards = new JList<>(listModelConnectedBavards);
        JScrollPane scrollPaneConnectedBavards = new JScrollPane(listConnectedBavards);
        panelConnectedBavards.add(scrollPaneConnectedBavards, BorderLayout.CENTER);

        panelLists.add(panelConnectedBavards);

        JPanel panelEvenements = new JPanel(new BorderLayout());
        panelEvenements.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        listModelEvenements = new DefaultListModel<>();
        JList<String> listEvenements = new JList<>(listModelEvenements);
        JScrollPane scrollPaneEvenements = new JScrollPane(listEvenements);
        panelEvenements.add(scrollPaneEvenements, BorderLayout.CENTER);

        panelLists.add(panelEvenements);

        panelMain.add(panelLists, BorderLayout.CENTER);

        add(panelMain);

        // BOUTON ENVOYER MESSAGE
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

        // BOUTON DECONNEXION
        buttonDeconnecter.addActionListener(e -> {
            batiment.deconnecterBavard(bavard);
            dispose();
        });

        batiment.getConcierge().addObserver(this);
        updateConnectedBavardList();
        updateMessages();
        setVisible(true);
    }

    // METHODES
    public void updateConnectedBavardList() {
        listModelConnectedBavards.clear();
        if (batiment.getConcierge().getConnectedBavards().isEmpty()) {
            listModelConnectedBavards.addElement("Aucun bavard connecté...");
        } else {
            for (Bavard bavard : batiment.getConcierge().getConnectedBavards()) {
                listModelConnectedBavards.addElement(bavard.getNom());
            }
        }
    }

    // METHODES LISTENER
    public void updateMessages() {
        listModelEvenements.clear();
        List<PapotageEvent> messages = bavard.getMessages();
        if (messages.isEmpty()) {
            listModelEvenements.addElement("Aucun message...");
        } else {
            for (PapotageEvent message : messages) {
                if (!message.getSource().equals(bavard)) {
                    listModelEvenements.addElement(message.getSource().getNom() + ": " + message.getSujet() + " - " + message.getCorps());
                }
            }
        }
    }

    public void onOnLineBavardEventReceived() {
        updateConnectedBavardList();
    }

    public void onOffLineBavardEventReceived() {
        updateConnectedBavardList();
    }
}
