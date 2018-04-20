package io.cristaling.iss.reddrop.core;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

public class AnalysisResult {
    @Id
    UUID uuid;
    @OneToOne
    @JoinColumn(name = "donation_uuid")
    Donation donation;
}
