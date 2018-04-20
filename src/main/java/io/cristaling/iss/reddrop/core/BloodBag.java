package io.cristaling.iss.reddrop.core;

import io.cristaling.iss.reddrop.utils.BloodBagType;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "bloodbags")
public class BloodBag {

    @Id
    UUID uuid;
    @ManyToOne
    @JoinColumn(name = "donation_uuid")
    Donation donation;
    @Column
    boolean tested;
    @Column
    Date expireDate;
    @Column
    BloodBagType type;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
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

    public BloodBagType getType() {
        return type;
    }

    public void setType(BloodBagType type) {
        this.type = type;
    }
}
