package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.InvoiceHeader;
import Model.InvoiceHeaderTableModel;
import Model.InvoiceLine;
import Model.InvoiceLineTableModel;
import View.AppFrame;
import View.HeaderDialog;
import View.LineDialog;

public class AppListener implements ActionListener, ListSelectionListener {

    private final AppFrame appFrame;
    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    FilesOperations filesOperations;

    public AppListener(AppFrame appFrame) {
        this.appFrame = appFrame;
        this.filesOperations = new FilesOperations(appFrame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "CreateInvoice":
                displayHeaderDialog();
                break;

            case "DeleteInvoice":
                deleteInvoice();
                break;

            case "SaveLine":
                displayLineDialog();
                break;

            case "CancelLine":
                cancelLine();
                break;

            case "LoadFile":
                filesOperations.LoadFile();
                break;

            case "SaveFile":
                filesOperations.SaveFile();
                break;

            case "HeaderDialogSave":
                headerDialogSave();
                break;
            case "HeaderDialogCancel":
                headerDialogCancel();
                break;

            case "LineDialogSave":
                lineDialogSave();
                break;
            case "LineDialogCancel":
                lineDialogCancel();
                break;

        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        invoiceTableRowSelection();

    }

    private void invoiceTableRowSelection() {

        int rowIndexSelection = appFrame.getInvoiceTable().getSelectedRow();
        if (rowIndexSelection >= 0) {
            InvoiceHeader row = appFrame.getInvoiceHeaderTableModel().getInvoicesList().get(rowIndexSelection);
            appFrame.getCustomerNameTextField().setText(row.getCustomer());
            appFrame.getInvoiceDateTextField().setText(dateFormat.format(row.getDate()));
            appFrame.getInvoiceNumberTextField().setText("" + row.getNumber());
            appFrame.getInvoiceTotalTextField().setText("" + row.getInvoiceTotal());
            ArrayList<InvoiceLine> lines = row.getLines();
            appFrame.setInvoiceLineTableModel(new InvoiceLineTableModel(lines));
            appFrame.getInvoiceItemTable().setModel(appFrame.getInvoiceLineTableModel());
            appFrame.getInvoiceLineTableModel().fireTableDataChanged();

        }
    }


    private void displayHeaderDialog() {
        appFrame.setHeaderDialog(new HeaderDialog(appFrame));
        appFrame.getHeaderDialog().setVisible(true);

    }

    private void displayLineDialog() {
        appFrame.setLineDialog(new LineDialog(appFrame));
        appFrame.getLineDialog().setVisible(true);
    }


    private void deleteInvoice() {
        int invoiceIndex = appFrame.getInvoiceTable().getSelectedRow();
        appFrame.getInvoiceHeaderTableModel().getInvoicesList().remove(invoiceIndex);
        appFrame.getInvoiceHeaderTableModel().fireTableDataChanged();
        appFrame.setInvoiceLineTableModel(new InvoiceLineTableModel(new ArrayList<>()));
        appFrame.getInvoiceItemTable().setModel(appFrame.getInvoiceLineTableModel());
        appFrame.getInvoiceLineTableModel().fireTableDataChanged();
        appFrame.getCustomerNameTextField().setText("");
        appFrame.getInvoiceDateTextField().setText("");
        appFrame.getInvoiceNumberTextField().setText("");
        appFrame.getInvoiceTotalTextField().setText("");

    }

    private void cancelLine() {
        int lineIndex = appFrame.getInvoiceItemTable().getSelectedRow();
        InvoiceLine line = appFrame.getInvoiceLineTableModel().getInvoiceLine().get(lineIndex);
        appFrame.getInvoiceLineTableModel().getInvoiceLine().remove(lineIndex);
        appFrame.getInvoiceLineTableModel().fireTableDataChanged();
        appFrame.getInvoiceHeaderTableModel().fireTableDataChanged();
        appFrame.getInvoiceTotalTextField().setText("" + line.getHeader().getInvoiceTotal());

    }


    private void headerDialogSave() {
        String dialogDate = appFrame.getHeaderDialog().getInvoiceDateTextField().getText();
        appFrame.getHeaderDialog().setVisible(false);
        appFrame.getHeaderDialog().dispose();
        appFrame.setHeaderDialog(null);
        try {
            InvoiceHeader invoiceHeader = new InvoiceHeader(checkInvoiceNumbers(), appFrame.getHeaderDialog().getCustomerNameTextField().getText(), dateFormat.parse(dialogDate));
            appFrame.getInvoiceList().add(invoiceHeader);
            appFrame.getInvoiceHeaderTableModel().fireTableDataChanged();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(appFrame, "Wrong date format", "Date Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void lineDialogSave() {

        appFrame.getLineDialog().setVisible(false);
        appFrame.getLineDialog().dispose();
        appFrame.setLineDialog(null);

        int headerIndex = appFrame.getInvoiceTable().getSelectedRow();

        InvoiceHeader header = appFrame.getInvoiceHeaderTableModel().getInvoicesList().get(headerIndex);
        InvoiceLine invoiceLine = new InvoiceLine(InvoiceHeaderTableModel.inItemNo,
                appFrame.getLineDialog().getItemNameTextField().getText(),
                Double.parseDouble(appFrame.getLineDialog().getItemPriceTextField().getText()),
                Integer.parseInt(appFrame.getLineDialog().getItemCountTextField().getText()),
                header);
        header.addInvoiceLine(invoiceLine);
        appFrame.getInvoiceLineTableModel().fireTableDataChanged();
        appFrame.getInvoiceHeaderTableModel().fireTableRowsUpdated(0, headerIndex);
        appFrame.getInvoiceTotalTextField().setText("" + header.getInvoiceTotal());
        displayInvoices();
    }


    private int checkInvoiceNumbers() {
        int max = 0;
        for (InvoiceHeader header : appFrame.getInvoiceList()) {
            if (header.getNumber() > max) {
                max = header.getNumber();
            }
        }
        return max + 1;
    }

    private void headerDialogCancel() {
        appFrame.getHeaderDialog().setVisible(false);
        appFrame.getHeaderDialog().dispose();
        appFrame.setHeaderDialog(null);
    }


    private void lineDialogCancel() {
        appFrame.getLineDialog().setVisible(false);
        appFrame.getLineDialog().dispose();
        appFrame.setLineDialog(null);

    }


    private void displayInvoices() {
        for (InvoiceHeader header : appFrame.getInvoiceList()) {
            System.out.println(header);
        }
    }

}