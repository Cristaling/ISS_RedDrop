package io.cristaling.iss.reddrop.core;


import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "donations")
public class Donation {
    @Id
    UUID uuid;
    @Column
    UUID bloodBag;
    @ManyToOne
    @JoinColumn(name="donator_uuid")
    Donator donator;
    @Column
    String diseases;
    @Column
    boolean result;
    @Column
    Date donationDate;
    @OneToOne
    @JoinColumn(name = "analysis_uuid")
    AnalysisResult analysisResult;

    public UUID getUuid() {
        return uuid;
    }

    public UUID getBloodBag() {
        return bloodBag;
    }

    public void setBloodBag(UUID bloodBag) {
        this.bloodBag = bloodBag;
    }


    public Donator getDonator() {
        return donator;
    }

    public void setDonator(Donator donator) {
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
