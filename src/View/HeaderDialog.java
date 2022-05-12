package View;


import java.awt.Dialog;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class HeaderDialog extends JDialog{
	
	private final JTextField customerNameTextField;
	private final JTextField invoiceDateTextField;

	public HeaderDialog(AppFrame appFrame) {

		JLabel nameLabel = new JLabel("Customer Name");
        customerNameTextField = new JTextField(20);
		JLabel dateLabel = new JLabel("Invoice Date");
        invoiceDateTextField = new JTextField(20);
		JButton saveButton = new JButton("Save");
        saveButton.setActionCommand("HeaderDialogSave");
        saveButton.addActionListener(appFrame.getListener());
		JButton cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("HeaderDialogCancel");
        cancelButton.addActionListener(appFrame.getListener());
        
        setLayout(new GridLayout(3, 2));
        
        add(nameLabel);
        add(customerNameTextField);
        add(dateLabel);
        add(invoiceDateTextField);
        add(saveButton);
        add(cancelButton);
		setLocationByPlatform(true);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        pack();
	}

	public JTextField getCustomerNameTextField() {
		return customerNameTextField;
	}

	public JTextField getInvoiceDateTextField() {
		return invoiceDateTextField;
	}

}
