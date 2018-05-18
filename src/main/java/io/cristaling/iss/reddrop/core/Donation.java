package io.cristaling.iss.reddrop.core;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    UUID uuid;
    @Column
    UUID donator;
    @Column
    String diseases;
    @Column
    boolean result;
    @Column
    Date donationDate;

    public UUID getUuid() {
        return uuid;
    }

    public UUID getDonator() {
        return donator;
    }

    public void setDonator(UUID donator) {
        this.donator = donator;
    }

    public String getDiseases() {
        return diseases;
    }

    public void setDiseases(String diseases) {
        this.diseases = diseases;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }
}
