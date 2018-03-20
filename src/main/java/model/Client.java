package model;

import java.util.Objects;

public class Client {
    private String name;
    private String address;
    private int id;

    public Client(){

    }

    public Client(Client client){
        this.name = client.name;
        this.address = client.address;
        this.id = client.id;
    }
    
    public Client(String _name, String _address, int _id){
        this.name = _name;
        this.address = _address;
        this.id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return id == client.id &&
                Objects.equals(name, client.name) &&
                Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, address, id);
    }

    @Override
    public String toString() {
        return name + ',' + address + ',' + id  + '\n';
    }
}
