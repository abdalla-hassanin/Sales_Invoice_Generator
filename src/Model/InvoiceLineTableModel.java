package Model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class InvoiceLineTableModel extends AbstractTableModel {
    private final List<InvoiceLine> invoiceLine;

    public InvoiceLineTableModel(List<InvoiceLine> InvoiceList) {
        this.invoiceLine = InvoiceList;
    }


    public List<InvoiceLine> getInvoiceLine() {
        return invoiceLine;
    }

    @Override
    public int getRowCount() {
        return invoiceLine.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "No.";
            case 1:
                return "Item Name";
            case 2:
                return "Item Price";
            case 3:
                return "Count";
            case 4:
                return "item Total";

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
                return Double.class;
            case 3:
                return Integer.class;
            case 4:
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
        InvoiceLine row = invoiceLine.get(rowIndex);


        switch (columnIndex) {
            case 0:
                return row.getNumber();
            case 1:
                return row.getItem();
            case 2:
                return row.getPrice();
            case 3:
                return row.getCount();
            case 4:
                return row.getLineTotal();
            default:
                return "";
        }


    }

}
