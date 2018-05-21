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
}
