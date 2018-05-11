package io.cristaling.iss.reddrop.core;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hospitals")
public class Hospital {
    @Id
    UUID uuid;
    @Column
    String name;
    @Column
    String city;
    @Column
    String county;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
