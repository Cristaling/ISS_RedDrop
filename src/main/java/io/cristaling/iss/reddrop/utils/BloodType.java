package io.cristaling.iss.reddrop.utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = BloodTypeSerializer.class)
public enum BloodType {

    UNKNOWN("Unknown"),
    ABP("AB+"),
    ABN("AB-"),
    AP("A+"),
    AN("A-"),
    BP("B+"),
    BN("B-"),
    OP("O+"),
    ON("O-");

    public String name;

    public String getName() {
        return name;
    }

    BloodType(String name) {
        this.name = name;
    }
}
