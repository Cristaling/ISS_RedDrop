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
    Double sodium;
    @Column
    Double potassium;
    @Column
    Double urea;
    @Column
    Double creatinine;
    @Column
    Double glucose;
    @Column
    UUID bloodtype;

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

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getPotassium() {
        return potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public Double getUrea() {
        return urea;
    }

    public void setUrea(Double urea) {
        this.urea = urea;
    }

    public Double getCreatinine() {
        return creatinine;
    }

    public void setCreatinine(Double creatinine) {
        this.creatinine = creatinine;
    }

    public Double getGlucose() {
        return glucose;
    }

    public void setGlucose(Double glucose) {
        this.glucose = glucose;
    }

    public UUID getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(UUID bloodtype) {
        this.bloodtype = bloodtype;
    }
}
