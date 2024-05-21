import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InterfaceBavard extends JFrame implements MessageObserver, OnLineBavardListener, OffLineBavardListener {
    private final Bavard bavard;
    private final Batiment batiment;
    private final JTextField textFieldSujet;
    private final JTextArea textAreaMessage;
    private final DefaultListModel<String> listModelEvenements;
    private final DefaultListModel<String> listModelConnectedBavards;

    public InterfaceBavard(Bavard bavard, Batiment batiment) {
        this.bavard = bavard;
        this.batiment = batiment;

        setTitle("Interface Bavard - " + bavard.getNom());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelMessage = new JPanel(new BorderLayout());
        panelMessage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel labelSujet = new JLabel("Sujet :");
        textFieldSujet = new JTextField();
        panelMessage.add(labelSujet, BorderLayout.WEST);
        panelMessage.add(textFieldSujet, BorderLayout.CENTER);

        textAreaMessage = new JTextArea(10, 40);
        textAreaMessage.setLineWrap(true);
        JScrollPane scrollPaneMessage = new JScrollPane(textAreaMessage);
        panelMessage.add(scrollPaneMessage, BorderLayout.SOUTH);

        add(panelMessage, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new BorderLayout());

        JButton buttonEnvoyer = new JButton("Envoyer");
        panelButtons.add(buttonEnvoyer, BorderLayout.CENTER);

        JButton buttonDeconnecter = new JButton("Déconnecter");
        panelButtons.add(buttonDeconnecter, BorderLayout.EAST);

        add(panelButtons, BorderLayout.SOUTH);

        JPanel panelEvenements = new JPanel(new BorderLayout());
        panelEvenements.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        listModelEvenements = new DefaultListModel<>();
        JList<String> listEvenements = new JList<>(listModelEvenements);
        JScrollPane scrollPaneEvenements = new JScrollPane(listEvenements);
        panelEvenements.add(scrollPaneEvenements, BorderLayout.CENTER);

        add(panelEvenements, BorderLayout.EAST);

        JPanel panelConnectedBavards = new JPanel(new BorderLayout());
        panelConnectedBavards.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        listModelConnectedBavards = new DefaultListModel<>();
        JList<String> listConnectedBavards = new JList<>(listModelConnectedBavards);
        JScrollPane scrollPaneConnectedBavards = new JScrollPane(listConnectedBavards);
        panelConnectedBavards.add(scrollPaneConnectedBavards, BorderLayout.CENTER);

        add(panelConnectedBavards, BorderLayout.WEST);

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

        buttonDeconnecter.addActionListener(e -> {
            batiment.deconnecterBavard(bavard);
            batiment.getConcierge().OffLineBavardEvent(bavard);
            batiment.updateBavardListInterface();
            dispose();
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

    public void onOnLineBavardEventReceived() {
        updateConnectedBavardList();
    }

    public void onOffLineBavardEventReceived() {
        updateConnectedBavardList();
    }

    public void updateConnectedBavardList() {
        listModelConnectedBavards.clear();
        if (listModelEvenements.isEmpty()) {
            listModelConnectedBavards.addElement("Aucun bavard connecté pour le moment...");
        } else {
            for (Bavard bavard : batiment.getConcierge().getConnectedBavards()) {
                listModelConnectedBavards.addElement(bavard.getNom());
            }
        }
    }
}
