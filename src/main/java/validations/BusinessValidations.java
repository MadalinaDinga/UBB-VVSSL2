package validations;

import exceptions.BusinessException;
import model.Client;
import model.Invoice;

import java.util.ArrayList;

public class BusinessValidations {

    public static void isClientUnique(ArrayList<Client> clients, String name, String address) throws BusinessException{
        for(int j=0; j<clients.size(); j++){
            if(clients.get(j).getName().equals(name) && clients.get(j).getAddress().equals(address)){
                String message = "Client already exists!";
                throw new BusinessException(message);
            }
        }
    }

    public static void isInvoiceUnique(ArrayList<Invoice> invoices, Invoice newInvoice) throws BusinessException{
        for(int j=0; j<invoices.size(); j++){
            if(invoices.get(j).equals(newInvoice)){
                String message = "Invoice already exists!";
                throw new BusinessException(message);
            }
        }
    }

    public static void existsClient(ArrayList<Client> clients, String name, String address) throws BusinessException{
        for(int j=0; j<clients.size(); j++){
            if(clients.get(j).getName().equals(name) && clients.get(j).getAddress().equals(address)){
                return;
            }
        }
        String message = "Client does not exist!";
        throw new BusinessException(message);
    }
}
