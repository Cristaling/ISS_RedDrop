package io.cristaling.iss.reddrop.utils;

public enum BloodType{
    ABP("AB+"),
    ABN("AB-"),
    AP("A+"),
    AN("A-"),
    BP("B+"),
    BN("B-"),
    OP("O+"),
    ON("O-"),
    UNKNOWN("Unknown");
    String name;

    public String getName() {
        return name;
    }

    BloodType(String name) {
        this.name = name;
    }
}
