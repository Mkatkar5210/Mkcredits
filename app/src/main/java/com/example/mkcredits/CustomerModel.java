package com.example.mkcredits;

public class CustomerModel {

    private  int id;
    private String Name;
    private String Email;
    private  double currentcredit;

    public CustomerModel(int id, String name, String email, double currentcredit) {
        this.id = id;
        Name = name;
        Email = email;
        this.currentcredit = currentcredit;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", currentcredit=" + currentcredit +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public double getCurrentcredit() {
        return currentcredit;
    }

    public void setCurrentcredit(double currentcredit) {
        this.currentcredit = currentcredit;
    }

}
