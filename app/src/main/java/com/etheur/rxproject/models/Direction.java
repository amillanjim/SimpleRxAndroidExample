package com.etheur.rxproject.models;

public class Direction {

    private String street;
    private String number;
    private String city;

    public Direction(String street, String number, String city){
        this.street = street;
        this.number = number;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
