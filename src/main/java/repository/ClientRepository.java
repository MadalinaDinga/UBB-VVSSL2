package repository;

import model.Client;

import java.util.ArrayList;

public class ClientRepository {
    private DataManager dataManager;
    private ArrayList<Client> clients;

    public ClientRepository(DataManager dataManager) {
        this.dataManager = dataManager;
        clients = dataManager.getClientsList();
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public int getSize(){
        return clients.size();
    }

    public void addClient(Client client) {
        dataManager.getClientsList().add(client);
        clients = dataManager.getClientsList();
        dataManager.SaveChanges();
    }
}
