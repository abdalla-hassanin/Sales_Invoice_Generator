package View;


import java.awt.Dimension;
import java.awt.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import Controller.AppListener;
import Model.InvoiceHeader;
import Model.InvoiceHeaderTableModel;
import Model.InvoiceLineTableModel;

public class AppFrame extends JFrame {


    private  JTable invoiceTable;
    private JTable invoiceItemTable;
    private  JTextField customerNameTextField;
    private  JTextField invoiceDateTextField;
    private  JTextPane invoiceNumberTextField;
    private JTextPane invoiceTotalTextField;

    private final List<InvoiceHeader> InvoiceList = new ArrayList<>();
    private InvoiceHeaderTableModel invoiceHeaderTableModel;
    private InvoiceLineTableModel invoiceLineTableModel;
    private HeaderDialog headerDialog;
    private LineDialog lineDialog;
    private final AppListener listener = new AppListener(this);

	public static void main(String[] args) {

		new AppFrame().setVisible(true);
	}


    public AppFrame() {

        setJMenuBar(getThisMenuBar());


        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.add(getLeftSide());
        jPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        jPanel.add(getRightSide());


        setSize(1400, 800);
        setLocation(50, 20);
        add(jPanel);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }



	JMenuBar getThisMenuBar(){

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		JMenuItem loadMenuItem = new JMenuItem("Load File");
		loadMenuItem.setActionCommand("LoadFile");
		loadMenuItem.addActionListener(listener);

		JMenuItem saveMenuItem = new JMenuItem("Save File");
		saveMenuItem.setActionCommand("SaveFile");
		saveMenuItem.addActionListener(listener);


		menuBar.add(fileMenu);
		fileMenu.add(loadMenuItem);
		fileMenu.add(saveMenuItem);
		return menuBar;
	}

	JPanel getLeftSide(){

		JPanel topSidePanel = new JPanel();
		JLabel tableInvoiceTitle = new JLabel("Invoices Table");
		invoiceTable = new JTable();

		topSidePanel.setLayout(new BoxLayout(topSidePanel, BoxLayout.Y_AXIS));
		topSidePanel.setPreferredSize(new Dimension(500, 650));
		topSidePanel.add(Box.createRigidArea(new Dimension(0, 13)));
		topSidePanel.add(tableInvoiceTitle);
		topSidePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		topSidePanel.add(new JScrollPane(invoiceTable));



		JPanel bodySidePanel = new JPanel();
		JButton createButton = new JButton("Create New Invoice");
		createButton.setActionCommand("CreateInvoice");
		createButton.addActionListener(listener);
		createButton.setPreferredSize(new Dimension(150, 30));

		JButton deleteButton = new JButton("Delete Invoice");
		deleteButton.setPreferredSize(new Dimension(150, 30));
		deleteButton.setActionCommand("DeleteInvoice");
		deleteButton.addActionListener(listener);

		bodySidePanel.setLayout(new FlowLayout());
		bodySidePanel.setPreferredSize(new Dimension(200, 200));
		bodySidePanel.add(createButton);
		bodySidePanel.add(Box.createRigidArea(new Dimension(50, 0)));
		bodySidePanel.add(deleteButton);


		// Merge Left side
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
		jPanel.add(topSidePanel);
		jPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		jPanel.add(bodySidePanel);

		return jPanel;
	}

	JPanel getRightSide(){

		JPanel topSidePanel = new JPanel();

		JPanel numPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel numLabel = new JLabel("Invoice Number");
		invoiceNumberTextField = new JTextPane();

		JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel dateLabel = new JLabel("Invoice Date");
		invoiceDateTextField = new JTextField(35);

		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel nameLabel = new JLabel("Customer Name");
		customerNameTextField = new JTextField(35);

		JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel totalLabel = new JLabel("Invoice Total");
		invoiceTotalTextField = new JTextPane();

		JPanel itemPanel = new JPanel();
		itemPanel.setPreferredSize(new Dimension(500, 400));
		itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
		JLabel tableInvoiceItemTitle = new JLabel("Invoice Items");
		invoiceItemTable = new JTable();

		topSidePanel.setLayout(new BoxLayout(topSidePanel, BoxLayout.Y_AXIS));
		topSidePanel.setPreferredSize(new Dimension(500, 650));
		topSidePanel.add(Box.createRigidArea(new Dimension(0, 10)));

		numPanel.setPreferredSize(new Dimension(30, 10));
		numPanel.add(numLabel);
		numPanel.add(invoiceNumberTextField);
		topSidePanel.add(numPanel);

		datePanel.setPreferredSize(new Dimension(30, 10));
		datePanel.add(dateLabel);
		datePanel.add(Box.createRigidArea(new Dimension(18, 0)));
		datePanel.add(invoiceDateTextField);
		topSidePanel.add(datePanel);

		namePanel.setPreferredSize(new Dimension(30, 10));
		namePanel.add(nameLabel);
		namePanel.add(customerNameTextField);
		topSidePanel.add(namePanel);

		totalPanel.setPreferredSize(new Dimension(30, 10));
		totalPanel.add(totalLabel);
		totalPanel.add(Box.createRigidArea(new Dimension(15, 0)));
		totalPanel.add(invoiceTotalTextField);
		topSidePanel.add(totalPanel);

		itemPanel.add(tableInvoiceItemTitle);
		itemPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		itemPanel.add(new JScrollPane(invoiceItemTable));
		topSidePanel.add(itemPanel);


		JPanel bodySidePanel = new JPanel();

		JButton saveButton = new JButton("Save");
		saveButton.setPreferredSize(new Dimension(100, 30));
		saveButton.setActionCommand("SaveLine");
		saveButton.addActionListener(listener);


		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("CancelLine");
		cancelButton.addActionListener(listener);
		cancelButton.setPreferredSize(new Dimension(100, 30));


		bodySidePanel.setLayout(new FlowLayout());
		bodySidePanel.setPreferredSize(new Dimension(200, 200));

		bodySidePanel.add(saveButton);
		bodySidePanel.add(Box.createRigidArea(new Dimension(50, 0)));
		bodySidePanel.add(cancelButton);



		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
		jPanel.add(topSidePanel);
		jPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		jPanel.add(bodySidePanel);

		return  jPanel;
	}

    public JTable getInvoiceTable() {
        return invoiceTable;
    }

    public JTable getInvoiceItemTable() {
        return invoiceItemTable;
    }


    public JTextField getCustomerNameTextField() {
        return customerNameTextField;
    }

    public JTextField getInvoiceDateTextField() {
        return invoiceDateTextField;
    }

    public JTextPane getInvoiceNumberTextField() {
        return invoiceNumberTextField;
    }

    public JTextPane getInvoiceTotalTextField() {
        return invoiceTotalTextField;
    }

    public List<InvoiceHeader> getInvoiceList() {
        return InvoiceList;
    }

    public InvoiceHeaderTableModel getInvoiceHeaderTableModel() {
        return invoiceHeaderTableModel;
    }

    public void setInvoiceHeaderTableModel(InvoiceHeaderTableModel invoiceHeaderTableModel) {
        this.invoiceHeaderTableModel = invoiceHeaderTableModel;
    }

    public void setInvoiceLineTableModel(InvoiceLineTableModel invoiceLineTableModel) {
        this.invoiceLineTableModel = invoiceLineTableModel;
    }

    public InvoiceLineTableModel getInvoiceLineTableModel() {
        return invoiceLineTableModel;
    }


    public HeaderDialog getHeaderDialog() {
        return headerDialog;
    }

    public void setHeaderDialog(HeaderDialog headerDialog) {
        this.headerDialog = headerDialog;
    }

    public void setLineDialog(LineDialog lineDialog) {
        this.lineDialog = lineDialog;
    }

    public LineDialog getLineDialog() {
        return lineDialog;
    }

    public AppListener getListener() {
        return listener;
    }


}

	            


