package io.cristaling.iss.reddrop.core;

import io.cristaling.iss.reddrop.utils.BloodType;

import javax.persistence.*;
import java.util.List;
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
    @Column
    private String cnp;
    @Column
    private String nrTel;
    @Column
    private String password;

    @Column
    private String city;
    @Column
    private String county;
    @Column
    private String address;

    @Column
    private BloodType bloodType;

    @OneToMany(mappedBy = "donator", cascade = CascadeType.ALL)
    private List<Donation> donations;

    public UUID getUuid() {
        return uuid;
    }

    public String getNume() {

        return nume;
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

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
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

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
