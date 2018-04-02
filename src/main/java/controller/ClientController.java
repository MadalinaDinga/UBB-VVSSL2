package controller;

import exceptions.BadFormatException;
import exceptions.BusinessException;
import model.Client;
import model.Invoice;
import repository.ClientRepository;
import repository.InvoiceRepository;
import validations.BusinessValidations;
import validations.UIValidations;

import java.util.ArrayList;

public class ClientController {

	private ClientRepository clientRepository;
	private InvoiceRepository invoiceRepository;
    
    public ClientController(ClientRepository clientRepository, InvoiceRepository invoiceRepository){
        this.clientRepository = clientRepository;
        this.invoiceRepository = invoiceRepository;
    }
    
    public boolean AddClient(String name, String address){
        // check client uniqueness
        try {
            BusinessValidations.isClientUnique(clientRepository.getClients(), name, address);
            UIValidations.validateClient(name, address);

            int id =clientRepository.getSize();
            Client client = new Client(name, address, id);

            clientRepository.addClient(client);

            return true;
        }catch(BusinessException | BadFormatException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean AddSubscriberInvoice(String name, String address, int year, int month, float toPay, float paid){
        try {
            //check if client exists
            BusinessValidations.existsClient(clientRepository.getClients(), name, address);

            //check invoice uniqueness
            int idClient = this.findClientId(name,address);
            Invoice invoice = new Invoice(year, month, toPay, paid, idClient);
            BusinessValidations.isInvoiceUnique(invoiceRepository.getInvoices(), invoice);

            invoiceRepository.addInvoice(invoice);

            return true;
        }catch(BusinessException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String showInvoicesForClient(String name, String address){
        try{
            //check if client exists
            BusinessValidations.existsClient(clientRepository.getClients(), name, address);

            int idClient = this.findClientId(name, address);

            StringBuilder subscriberInvoices = new StringBuilder();
            for (Invoice invoice : invoiceRepository.getInvoices()){
                if(invoice.getIdClient() == idClient){
                    subscriberInvoices.append("Year: ");
                    subscriberInvoices.append(invoice.getYear());
                    subscriberInvoices.append(" ");
                    subscriberInvoices.append("Month: ");
                    subscriberInvoices.append(invoice.getMonth());
                    subscriberInvoices.append(" ");
                    subscriberInvoices.append("Penalty: ");
                    subscriberInvoices.append(invoice.getPenalty());
                    subscriberInvoices.append("\n");
                }
            }
            return subscriberInvoices.toString();
        }catch(BusinessException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private int findClientId(String name, String address){
        ArrayList<Client> clients = clientRepository.getClients();
        for(int j=0; j<clients.size(); j++){
            Client client = clients.get(j);
            if(client.getName().equals(name) && client.getAddress().equals(address)){
                return client.getId();
            }
        }
        return -1;
    }
}
