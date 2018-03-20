package repository;

import model.Invoice;

import java.util.ArrayList;

public class InvoiceRepository {
    private DataManager dataManager;
    private ArrayList<Invoice> invoices;

    public InvoiceRepository() {
        dataManager = new DataManager();
        invoices = dataManager.getInvoicesList();
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(ArrayList<Invoice> invoices) {
        this.invoices = invoices;
    }

    public int getSize(){
        return invoices.size();
    }

    public void addInvoice(Invoice invoice) {
        dataManager.getInvoicesList().add(invoice);
        invoices= dataManager.getInvoicesList();
        dataManager.SaveChanges();
    }
}
