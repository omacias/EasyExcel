/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salvador.test;

import com.salvador.easyexcel.annotations.Exclude;
import com.salvador.easyexcel.annotations.Header;
import com.salvador.easyexcel.annotations.Row;
import com.salvador.easyexcel.enums.Colors;
import com.salvador.easyexcel.enums.TextStyle;
import java.util.Date;

/**
 * Person class
 * @author Oscar Salvador Macias Tiscareño
 */
public class Person {
    
    protected Person(String name, int age, Date graduatedDate) {
        this.name = name;
        this.age = age;
        this.graduatedDate = graduatedDate;
        this.address = new Address("AGS", "JESUS MARIA", "MIGUEL DE LA MADRID", "203");
        this.active = true;
    }

    @Header(title = "Nombre", style = TextStyle.BOLD, backgroundColor = Colors.AQUA)
    @Row(backgroundColor = Colors.WHITE, style = TextStyle.NORMAL)
    private String name;

    @Header(title = "Edad", style = TextStyle.BOLD, backgroundColor = Colors.AQUA)
    @Row(backgroundColor = Colors.WHITE, style = TextStyle.NORMAL)
    private int age;
    
    @Header(title = "Fecha de titulación", style = TextStyle.BOLD, backgroundColor = Colors.AQUA)
    @Row(backgroundColor = Colors.WHITE, style = TextStyle.NORMAL)
    @Exclude
    private Date graduatedDate;
    
    @Header(title = "Dirección", autoSizeColumn = true, style = TextStyle.BOLD, backgroundColor = Colors.AQUA)
    @Row(backgroundColor = Colors.WHITE, style = TextStyle.NORMAL)
    private Address address;
    
    @Header(title = "Activo", style = TextStyle.BOLD, backgroundColor = Colors.AQUA)
    @Row(backgroundColor = Colors.WHITE, style = TextStyle.NORMAL)
    private boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public Date getGraduatedDate() {
        return graduatedDate;
    }

    public void setGraduatedOn(Date graduatedDate) {
        this.graduatedDate = graduatedDate;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

class Address {
    
    private String state;
    
    private String county;
    
    private String street;
    
    private String externalNumber;

    public Address(String state, String county, String street, String externalNumber) {
        this.state = state;
        this.county = county;
        this.street = street;
        this.externalNumber = externalNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getExternalNumber() {
        return externalNumber;
    }

    public void setExternalNumber(String externalNumber) {
        this.externalNumber = externalNumber;
    }

    @Override
    public String toString() {
        return "Address{" + "state=" + state + ", county=" + county + ", street=" + street + ", externalNumber=" + externalNumber + '}';
    }
    
}
