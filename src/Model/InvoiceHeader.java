package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class InvoiceHeader {

    private final int number;
    private final String customer;
    private final Date date;
    private ArrayList<InvoiceLine> lines = new ArrayList<>();


    public InvoiceHeader(int number, String customer, Date date) {
        this.number = number;
        this.customer = customer;
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public String getCustomer() {
        return customer;
    }

    public Date getDate() {
        return date;
    }

    public ArrayList<InvoiceLine> getLines() {
        if (lines == null)
            lines = new ArrayList<>();
        return lines;
    }


    public double getInvoiceTotal() {
        double total = 0.0;
        for (InvoiceLine line : lines) {
            total += line.getLineTotal();
        }
        return total;
    }


    public void addInvoiceLine(InvoiceLine inLine) {
        getLines().add(inLine);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("InvoiceHeader{" + "Invoice Number: " + number + ", Customer Name: " + customer + ", Date: " + date + '}');
        for (InvoiceLine line : getLines()) {
            str.append("\n\t").append(line);
        }
        return str.toString();
    }


    public String getDataAsCSV() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "" + getNumber() + "," + dateFormat.format(getDate()) + "," + getCustomer();
    }


}
