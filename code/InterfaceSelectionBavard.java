import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InterfaceSelectionBavard extends JFrame implements SelectionBavardObserver {
    private final Batiment batiment;
    private final JComboBox<String> comboBoxBavards;
    private final JButton buttonEnvoyerMessage;

    public InterfaceSelectionBavard(Batiment batiment) {
        this.batiment = batiment;

        setTitle("Sélectionner un Bavard pour Envoyer un Message");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        comboBoxBavards = new JComboBox<>();
        add(comboBoxBavards);

        updateBavardListConnected();

        buttonEnvoyerMessage = new JButton("Envoyer Message");
        add(buttonEnvoyerMessage);

        buttonEnvoyerMessage.addActionListener(e -> {
            String selectedBavardNom = (String) comboBoxBavards.getSelectedItem();
            if (selectedBavardNom != null) {
                Bavard selectedBavard = batiment.getConcierge().getBavardByName(selectedBavardNom);
                new InterfaceBavard(selectedBavard, batiment.getConcierge());
            } else {
                JOptionPane.showMessageDialog(InterfaceSelectionBavard.this, "Veuillez sélectionner un bavard.");
            }
        });

        setVisible(true);
    }

    public void updateBavardListConnected() {
        comboBoxBavards.removeAllItems();
        for (Bavard bavard : batiment.getConcierge().getBavards()) {
            if (batiment.getConcierge().getConnectedBavards().contains(bavard)) {
                comboBoxBavards.addItem(bavard.getNom());
            }
        }
    }
}
