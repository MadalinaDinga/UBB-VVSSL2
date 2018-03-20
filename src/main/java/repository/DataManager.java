package repository;

import model.Client;
import model.Invoice;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DataManager {
	private final static String fileClient = "client.txt";
    private final static String fileInvoice = "invoice.txt";

    private ArrayList<Client> clients;
    private ArrayList<Invoice> invoices;
    
    public DataManager(){
        clients = new ArrayList<>();
        invoices = new ArrayList<>();

        LoadClient();
        LoadInvoices();
    }
    
    private void LoadClient(){
        try ( BufferedReader br = new BufferedReader(new FileReader(fileClient))){
            String line;
            String[] clientInfo;
            while((line = br.readLine()) != null){
                clientInfo = line.split(",");

                if (clientInfo.length == 3) {
                    String name = clientInfo[0];
                    String address = clientInfo[1];
                    int id = Integer.parseInt(clientInfo[2]);
                    clients.add(new Client(name, address, id));
                }
            }
        }catch(IOException e){
            System.out.println("Could not read data");
        }
    }
    
    private void LoadInvoices() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileInvoice))){
            String line;
            String[] invoiceInfo;
            while((line = br.readLine()) != null){
                invoiceInfo = line.split(",");

                if (invoiceInfo.length == 5) {
                    int sYear = Integer.parseInt(invoiceInfo[0]);
                    int sMonth = Integer.parseInt(invoiceInfo[1]);
                    float sToPay = Float.parseFloat(invoiceInfo[2]);
                    float sPaid = Float.parseFloat(invoiceInfo[3]);
                    int idClient = Integer.parseInt(invoiceInfo[4]);

                    invoices.add(new Invoice( sYear, sMonth, sToPay, sPaid, idClient));
                }
            }
        }catch(IOException e){
            System.out.println("Could not read data");
        }
    }
    
    public void SaveChanges(){
        try{
            File fClient = new File(fileClient);
            File fIssue = new File(fileInvoice);
            FileWriter pwClient = new FileWriter(fClient.getAbsolutePath(), true);
            FileWriter pwIssue = new FileWriter(fIssue.getAbsolutePath(), true);
            String s;
            try (BufferedWriter bwc = new BufferedWriter(pwClient)) {
                s = "";
                for(Iterator<Client> i = clients.iterator(); i.hasNext();){
                    Client c = i.next();
                    s = c.toString() + System.getProperty("line.separator");
                }
                bwc.write(s);
            }
            try (BufferedWriter bwi = new BufferedWriter(pwIssue)) {
                s = "";
                for(Iterator<Invoice> i = invoices.iterator(); i.hasNext();){
                    Invoice invoice = i.next();
                    s = invoice.toString() + System.getProperty("line.separator");
                }
                bwi.write(s);
            }
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
    }
    
    public ArrayList<Invoice> getInvoicesList() {
          return invoices;
	}

    public ArrayList<Client> getClientsList() {
		return clients;
	}

    @Override
    public String toString() {
        return "DataManager{" +
                "clients=" + clients +
                ", invoices=" + invoices +
                '}';
    }
}
