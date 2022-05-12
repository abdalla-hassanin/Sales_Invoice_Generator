package Model;

public class InvoiceLine {

    private final int number;
    private final String item;
    private final double price;
    private final int count;
    private final InvoiceHeader Header;


    public InvoiceLine(int number, String item, double price, int count, InvoiceHeader header) {
        this.number = number;
        this.item = item;
        this.price = price;
        this.count = count;
        Header = header;
    }

    public int getNumber() {
        return number;
    }

    public String getItem() {
        return item;
    }


    public double getPrice() {
        return price;
    }


    public int getCount() {
        return count;
    }


    public InvoiceHeader getHeader() {
        return Header;
    }


    public double getLineTotal() {
        return price * count;
    }

    public String toString() {
        return "InvoiceLine{" + "item Name: " + item + ", item Price: " + price + ", item Count: " + count + '}';
    }


    public String getDataAsCSV() {
        return "" + getNumber() + "," + getItem() + "," + getPrice() + "," + getCount();
    }


}
