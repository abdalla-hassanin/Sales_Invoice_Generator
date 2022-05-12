package Model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class InvoiceHeaderTableModel extends AbstractTableModel {

    private final List<InvoiceHeader> invoiceList;
    private final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public static int inItemNo;


    public InvoiceHeaderTableModel(List<InvoiceHeader> InvoiceList) {
        this.invoiceList = InvoiceList;
    }


    public List<InvoiceHeader> getInvoicesList() {
        return invoiceList;
    }

    @Override
    public int getRowCount() {
        return invoiceList.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "No.";
            case 1:
                return "Date";
            case 2:
                return "Customer";
            case 3:
                return "Total";
            default:
                return "";
        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Double.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader row = invoiceList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                inItemNo = row.getNumber();
                return row.getNumber();
            case 1:
                return dateFormat.format(row.getDate());
            case 2:
                return row.getCustomer();
            case 3:
                return row.getInvoiceTotal();
            default:
                return "";
        }


    }

}
