import javax.swing.*;
import java.awt.*;

public class InterfaceBatiment extends JFrame {
    private final Batiment batiment;
    private final JLabel labelNom;
    private final JTextField textFieldNom;
    private final JButton buttonCreerBavard;
    private final JComboBox<String> comboBoxBavards; // Utiliser String ici
    private final JButton buttonConnecter; // Bouton Connecter

    public InterfaceBatiment(Batiment b) {
        this.batiment = b;

        setTitle("Interface Batiment");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        labelNom = new JLabel("Nom du Bavard:");
        add(labelNom);

        textFieldNom = new JTextField(15);
        add(textFieldNom);

        buttonCreerBavard = new JButton("Créer Bavard");
        add(buttonCreerBavard);

        comboBoxBavards = new JComboBox<>();
        comboBoxBavards.setPreferredSize(new Dimension(200, 30));
        updateComboBox();
        add(comboBoxBavards);

        buttonConnecter = new JButton("Connecter");
        add(buttonConnecter);

        buttonCreerBavard.addActionListener(e -> {
            String nom = textFieldNom.getText();
            if (!nom.isEmpty()) {
                Bavard bavard = b.creerBavard(nom);
                comboBoxBavards.addItem(bavard.getNom());
                textFieldNom.setText("");
                JOptionPane.showMessageDialog(InterfaceBatiment.this, "Le bavard " + nom + " à été créé avec succès !");
            } else {
                JOptionPane.showMessageDialog(InterfaceBatiment.this, "Veuillez saisir un nom pour le bavard.");
            }
        });

        buttonConnecter.addActionListener(e -> {
            String selectedBavard = (String) comboBoxBavards.getSelectedItem();
            if (selectedBavard != null) {
                Bavard bavard = b.getBavard(selectedBavard);
                b.connecterBavard(bavard);
                updateComboBox();
                JOptionPane.showMessageDialog(InterfaceBatiment.this, "Le bavard " + selectedBavard + " à été connecté avec succès !");
            } else {
                JOptionPane.showMessageDialog(InterfaceBatiment.this, "Veuillez sélectionner un bavard à connecter.");
            }
        });

        setVisible(true);
    }

    private void updateComboBox() {
        comboBoxBavards.removeAllItems();
        for (Bavard bavard : batiment.getConcierge().getBavards()) {
            if (!batiment.getConcierge().getConnectedBavards().contains(bavard)) {
                comboBoxBavards.addItem(bavard.getNom());
            }
        }
    }
}