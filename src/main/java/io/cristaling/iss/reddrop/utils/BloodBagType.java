package io.cristaling.iss.reddrop.utils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = BloodBagTypeSerializer.class)
public enum BloodBagType {
    ALL("All"),
    PLASMA("Plasma"),
    THROMBOCYTE("Thrombocyte"),
    HEMOGLOBIN("Hemoglobin");

    public String name;

    public String getName() {
        return name;
    }

    BloodBagType(String name) {
        this.name = name;
    }
}
