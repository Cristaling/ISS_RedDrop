package io.cristaling.iss.reddrop.core;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "analysisresults")
public class AnalysisResult {

    @Id
    UUID uuid;
    @Column
    UUID donation;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getDonation() {
        return donation;
    }

    public void setDonation(UUID donation) {
        this.donation = donation;
    }
}
