package View;

import java.awt.Dialog;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class LineDialog extends JDialog {


    private final JTextField itemNameTextField;
    private final JTextField itemCountTextField;
    private final JTextField itemPriceTextField;


    public LineDialog(AppFrame appFrame) {

        JLabel nameLabel = new JLabel("Item Name");
        itemNameTextField = new JTextField(20);
        JLabel countLabel = new JLabel("Item Count");
        itemCountTextField = new JTextField(20);
        JLabel priceLabel = new JLabel("Item Price");
        itemPriceTextField = new JTextField(20);
        JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("LineDialogSave");
        saveButton.addActionListener(appFrame.getListener());
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("LineDialogCancel");
        cancelButton.addActionListener(appFrame.getListener());

        setLayout(new GridLayout(4, 2));

        add(nameLabel);
        add(itemNameTextField);
        add(countLabel);
        add(itemCountTextField);
        add(priceLabel);
        add(itemPriceTextField);
        add(saveButton);
        add(cancelButton);
        setLocationByPlatform(true);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        pack();
    }

    public JTextField getItemNameTextField() {
        return itemNameTextField;
    }

    public JTextField getItemCountTextField() {
        return itemCountTextField;
    }

    public JTextField getItemPriceTextField() {
        return itemPriceTextField;
    }


}
