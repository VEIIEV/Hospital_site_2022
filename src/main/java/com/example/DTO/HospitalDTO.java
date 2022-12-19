package com.example.DTO;


public class HospitalDTO {

    private String name;
    private String address;
    private String mail;
    private String number;

    public HospitalDTO(String name, String address, String mail, String number) {
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.number = number;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
