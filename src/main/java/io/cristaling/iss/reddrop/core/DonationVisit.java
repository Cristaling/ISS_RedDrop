package io.cristaling.iss.reddrop.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "donation_visits")
public class DonationVisit {

    @Id
    UUID uuid;
    @Column
    UUID donator;
    @Column
    String donatorName;
    @Column
    Date date;

    public String getDonatorName() {
        return donatorName;
    }

    public void setDonatorName(String donatorName) {
        this.donatorName = donatorName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getDonator() {
        return donator;
    }

    public void setDonator(UUID donator) {
        this.donator = donator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
