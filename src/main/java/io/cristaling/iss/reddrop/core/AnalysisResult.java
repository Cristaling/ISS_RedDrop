package io.cristaling.iss.reddrop.core;

import io.cristaling.iss.reddrop.utils.Diseases;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "analysisresults")
public class AnalysisResult {

    @Id
    UUID uuid;
    @Column
    UUID donationVisit;
    @Column
    int pulse;
    @Column
    int tension;
    @Column
    Diseases diseases;

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

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public int getTension() {
        return tension;
    }

    public void setTension(int tension) {
        this.tension = tension;
    }

    public Diseases getDiseases() {
        return diseases;
    }

    public void setDiseases(Diseases diseases) {
        this.diseases = diseases;
    }
}
