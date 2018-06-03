package io.cristaling.iss.reddrop.core;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "donators")
public class Donator {

    @Id
    private UUID uuid;

    @Column
    private String nume;
    @Column
    private String prenume;
    @Column(unique = true)
    private String cnp;
    @Column
    private String nrTel;
    @Column
    private String password;

    @Column
    private String email;
    @Column
    private UUID verified;
    @Column
    private String city;
    @Column
    private String county;
    @Column
    private String address;

    @Column
    private UUID bloodType;

    public UUID getUuid() {
        return uuid;
    }

    public String getNume() {

        return nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getVerified() {
        return verified;
    }

    public void setVerified(UUID verified) {
        this.verified = verified;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public UUID getBloodType() {
        return bloodType;
    }

    public void setBloodType(UUID bloodType) {
        this.bloodType = bloodType;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String adresa) {
        address = adresa;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
