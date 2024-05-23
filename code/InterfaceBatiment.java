import javax.swing.*;
import java.awt.*;

public class InterfaceBatiment extends JFrame {
    // ATTRIBUTS
    private final Batiment batiment;
    private final JTextField textFieldNom;
    private final JComboBox<String> comboBoxBavards;

    // CONSTRUCTEUR
    public InterfaceBatiment(Batiment batiment) {
        this.batiment = batiment;
        new InterfaceConcierge(batiment);

        // CONSTRUCTION DE L'INTERFACE
        setTitle("Interface Batiment");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel labelNom = new JLabel("Nom du Bavard:");
        add(labelNom);

        textFieldNom = new JTextField(15);
        add(textFieldNom);

        JButton buttonCreerBavard = new JButton("Créer Bavard");
        add(buttonCreerBavard);

        comboBoxBavards = new JComboBox<>();
        comboBoxBavards.setPreferredSize(new Dimension(200, 30));
        add(comboBoxBavards);

        JButton buttonConnecter = new JButton("Connecter");
        add(buttonConnecter);

        // BOUTON CREER BAVARD
        buttonCreerBavard.addActionListener(e -> {
            String nom = textFieldNom.getText().trim();
            if (!nom.isEmpty()) {
                String nomLowerCase = nom.toLowerCase();
                boolean bavardExists = false;
                for (Bavard bavard : batiment.getConcierge().getBavards()) {
                    if (bavard.getNom().equalsIgnoreCase(nomLowerCase)) {
                        bavardExists = true;
                        break;
                    }
                }
                if (!bavardExists) {
                    Bavard bavard = batiment.creerBavard(nom);
                    comboBoxBavards.addItem(bavard.getNom());
                    textFieldNom.setText("");
                    updateBavardList();
                    JOptionPane.showMessageDialog(InterfaceBatiment.this, "Le bavard " + nom + " à été créé avec succès !");
                } else {
                    textFieldNom.setText("");
                    JOptionPane.showMessageDialog(InterfaceBatiment.this, "Un bavard avec ce nom existe déjà. Veuillez choisir un autre nom.");
                }
            } else {
                JOptionPane.showMessageDialog(InterfaceBatiment.this, "Veuillez saisir un nom pour le bavard.");
            }
        });

        // BOUTON CONNECTER BAVARD
        buttonConnecter.addActionListener(e -> {
            String selectedBavard = (String) comboBoxBavards.getSelectedItem();
            if (selectedBavard != null) {
                Bavard bavard = batiment.getBavardByName(selectedBavard);
                batiment.connecterBavard(bavard);
                JOptionPane.showMessageDialog(InterfaceBatiment.this, "Le bavard " + selectedBavard + " à été connecté avec succès !");
                new InterfaceBavard(bavard, batiment);
            } else {
                JOptionPane.showMessageDialog(InterfaceBatiment.this, "Veuillez sélectionner un bavard à connecter.");
            }
        });

        updateBavardList();
        setVisible(true);
    }

    // METHODES
    public void updateBavardList() {
        comboBoxBavards.removeAllItems();
        for (Bavard bavard : batiment.getConcierge().getBavards()) {
            if (!batiment.getConcierge().getConnectedBavards().contains(bavard)) {
                comboBoxBavards.addItem(bavard.getNom());
            }
        }
    }
}