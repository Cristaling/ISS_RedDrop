package io.cristaling.iss.reddrop.core;

import javax.persistence.*;
import javax.print.Doc;
import java.util.UUID;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    UUID uuid;
    @Column
    UUID hospital;
    @Column(unique = true)
    String cnp;
    @Column
    String fullName;
    @Column
    String password;

    public Doctor() { }

    public Doctor(String uuid) {
        //TODO investigate vulnerability of invalid uuids
        this.uuid = UUID.fromString(uuid);
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getHospital() {
        return hospital;
    }

    public void setHospital(UUID hospital) {
        this.hospital = hospital;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
