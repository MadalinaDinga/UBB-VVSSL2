package model;

import java.util.Objects;

public class Invoice {
    private int year;
    private int month;
    private float toPay;
    private float paid;
    private int idClient;

    public Invoice(int _year, int _month, float _toPay, float _paid, int _idClient) {
        this.year = _year;
        this.month = _month;
        this.toPay = _toPay;
        this.paid = _paid;
        this.idClient = _idClient;
    }

    public Invoice(Invoice invoice){
        this.year = invoice.year;
        this.month = invoice.month;
        this.toPay = invoice.toPay;
        this.paid = invoice.paid;
        this.idClient = invoice.idClient;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public float getToPay() {
        return toPay;
    }

    public void setToPay(float toPay) {
        this.toPay = toPay;
    }

    public float getPaid() {
        return paid;
    }

    public void setPaid(float paid) {
        this.paid = paid;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public float getPenalty(){
        return toPay-paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return year == invoice.year &&
                month == invoice.month &&
                Float.compare(invoice.toPay, toPay) == 0 &&
                Float.compare(invoice.paid, paid) == 0 &&
                idClient == invoice.idClient;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, toPay, paid, idClient);
    }

    @Override
    public String toString() {
        return  year +
                "," + month +
                "," + toPay +
                "," + paid +
                "," + idClient;
    }
}
