package io.cristaling.iss.reddrop.core;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "bloodbags")
public class BloodBag {

    @Id
    UUID uuid;
    @Column
    UUID donationVisit;
    @Column
    boolean tested;
    @Column
    Date expireDate;
    @Column
    UUID bloodBagType;
    @Column
    UUID bloodType;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getDonationVisit() {
        return donationVisit;
    }

    public void setDonationVisit(UUID donationVisit) {
        this.donationVisit = donationVisit;
    }

    public boolean isTested() {
        return tested;
    }

    public void setTested(boolean tested) {
        this.tested = tested;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public UUID getBloodBagType() {
        return bloodBagType;
    }

    public void setBloodBagType(UUID bloodBagType) {
        this.bloodBagType = bloodBagType;
    }

    public UUID getBloodType() {
        return bloodType;
    }

    public void setBloodType(UUID bloodType) {
        this.bloodType = bloodType;
    }
}
