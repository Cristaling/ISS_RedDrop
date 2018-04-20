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
    @OneToMany(mappedBy = "donation", cascade = CascadeType.ALL)
    List<BloodBag> bloodBag;
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

    public List<BloodBag> getBloodBag() {
        return bloodBag;
    }

    public void setBloodBag(List<BloodBag> bloodBag) {
        this.bloodBag = bloodBag;
    }

    public AnalysisResult getAnalysisResult() {
        return analysisResult;
    }

    public void setAnalysisResult(AnalysisResult analysisResult) {
        this.analysisResult = analysisResult;
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
