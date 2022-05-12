package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Model.InvoiceHeader;
import Model.InvoiceHeaderTableModel;
import Model.InvoiceLine;
import View.AppFrame;

public class FilesOperations {

    private final AppFrame appFrame;
    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public FilesOperations(AppFrame appFrame) {
        this.appFrame = appFrame;
    }

    void LoadFile() {
        int result;
        JOptionPane.showMessageDialog(appFrame, "Please, select a header file.", "Header File", JOptionPane.WARNING_MESSAGE);
        JFileChooser loadFile = new JFileChooser();
        result = loadFile.showOpenDialog(appFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = loadFile.getSelectedFile();

            try {

                try (BufferedReader headerBReader = new BufferedReader(new FileReader(headerFile))) {
                    String linesHeader;

                    while ((linesHeader = headerBReader.readLine()) != null) {
                        String[] headerParts = linesHeader.split(",");

                        InvoiceHeader invoiceHeader = new InvoiceHeader(Integer.parseInt(headerParts[0]), headerParts[2], dateFormat.parse(headerParts[1]));
                        appFrame.getInvoiceList().add(invoiceHeader);
                    }
                }
                JOptionPane.showMessageDialog(appFrame, "Please, select a lines file.", "Lines File", JOptionPane.WARNING_MESSAGE);
                result = loadFile.showOpenDialog(appFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = loadFile.getSelectedFile();


                    try (BufferedReader linesBReader = new BufferedReader(new FileReader(linesFile))) {
                        String linesLine;

                        while ((linesLine = linesBReader.readLine()) != null) {
                            String[] lineParts = linesLine.split(",");
                            int inNum = Integer.parseInt(lineParts[0]);
                            InvoiceHeader header = findInNum(inNum);
                            InvoiceLine inLine = new InvoiceLine(inNum, lineParts[1], Double.parseDouble(lineParts[2]), Integer.parseInt(lineParts[3]), header);
                            header.getLines().add(inLine);
                        }
                    }
                    appFrame.setInvoiceHeaderTableModel(new InvoiceHeaderTableModel(appFrame.getInvoiceList()));
                    appFrame.getInvoiceTable().setModel(appFrame.getInvoiceHeaderTableModel());
                    appFrame.getInvoiceTable().validate();
                    appFrame.getInvoiceTable().getSelectionModel().addListSelectionListener(appFrame.getListener());

                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(appFrame, "Error: " + ex.getMessage(), "Error: ", JOptionPane.ERROR_MESSAGE);
            }
        }
        displayInvoices();
    }


    private InvoiceHeader findInNum(int inNum) {
        InvoiceHeader header = null;
        for (InvoiceHeader invoiceHeader : appFrame.getInvoiceList()) {
            if (inNum == invoiceHeader.getNumber()) {
                header = invoiceHeader;
                break;
            }
        }
        return header;

    }


    void SaveFile() {
        StringBuilder headers = new StringBuilder();
        StringBuilder lines = new StringBuilder();
        for (InvoiceHeader header : appFrame.getInvoiceList()) {
            headers.append(header.getDataAsCSV());
            headers.append("\n");
            for (InvoiceLine line : header.getLines()) {
                lines.append(line.getDataAsCSV());
                lines.append("\n");
            }
        }

        JOptionPane.showMessageDialog(appFrame, "Please, select file to save header data.", "Header File", JOptionPane.WARNING_MESSAGE);
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(appFrame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = fileChooser.getSelectedFile();
            try {
                FileWriter fileWriterL = new FileWriter(headerFile);
                fileWriterL.write(headers.toString());
                fileWriterL.flush();
                fileWriterL.close();

                JOptionPane.showMessageDialog(appFrame, "Please, select file to save lines data.", "Lines File", JOptionPane.WARNING_MESSAGE);
                result = fileChooser.showSaveDialog(appFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = fileChooser.getSelectedFile();
                    FileWriter fileWriterH = new FileWriter(linesFile);
                    fileWriterH.write(lines.toString());
                    fileWriterH.flush();
                    fileWriterH.close();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(appFrame, "Error: " + ex.getMessage(), "Error: ", JOptionPane.ERROR_MESSAGE);
            }
        }


    }

    private void displayInvoices() {
        for (InvoiceHeader header : appFrame.getInvoiceList()) {
            System.out.println(header);
        }
    }


}
