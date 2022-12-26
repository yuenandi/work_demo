package org.example.utils;

public class NameData {
    public String name;
    private String idCardNumber;

    private final String friend = "liangLiang";

    public NameData(){

    }
    public NameData(String name, String idCardNumber) {
        this.name = name;
        this.idCardNumber = idCardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getFriend() {
        return friend;
    }



}
