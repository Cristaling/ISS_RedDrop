package io.cristaling.iss.reddrop.core;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "analysisresults")
public class AnalysisResult {

    @Id
    UUID uuid;
    @OneToOne
    @JoinColumn(name = "donation_uuid")
    Donation donation;

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
}
