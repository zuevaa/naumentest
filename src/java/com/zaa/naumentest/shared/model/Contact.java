package com.zaa.naumentest.shared.model;

import com.google.gwt.user.client.rpc.IsSerializable;

// Модель контакта 

public class Contact implements IsSerializable{
    private long key;
    private String name, phone;
    public Contact() {
    }
    public  Contact(long key, String name, String phone) {
        this.key = key;
        this.name = name;
        this.phone = phone;
    }
    public long getKey() {
        return this.key;
    }
    public void setKey(long key) {
        this.key = key;
    }    
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
