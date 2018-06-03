package io.cristaling.iss.reddrop.core;

import io.cristaling.iss.reddrop.utils.enums.BloodRequestStatus;
import io.cristaling.iss.reddrop.utils.enums.Importance;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "requests")
public class BloodRequest {

    @Id
    UUID uuid;
    @Column
    String patientCnp;
    @Column
    String patientFullName;
    @Column
    UUID doctor;
    @Column
    Importance importance;
    @Column
    BloodRequestStatus status;
    @Column
    UUID bloodType;
    @Column
    UUID bloodBagType;
    @Column
    Date date;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getPatientCnp() {
        return patientCnp;
    }

    public void setPatientCnp(String patientCnp) {
        this.patientCnp = patientCnp;
    }

    public String getPatientFullName() {
        return patientFullName;
    }

    public void setPatientFullName(String patientFullName) {
        this.patientFullName = patientFullName;
    }

    public UUID getDoctor() {
        return doctor;
    }

    public void setDoctor(UUID doctor) {
        this.doctor = doctor;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    public BloodRequestStatus getStatus() {
        return status;
    }

    public void setStatus(BloodRequestStatus status) {
        this.status = status;
    }

    public UUID getBloodType() {
        return bloodType;
    }

    public void setBloodType(UUID bloodType) {
        this.bloodType = bloodType;
    }

    public UUID getBloodBagType() {
        return bloodBagType;
    }

    public void setBloodBagType(UUID bloodBagType) {
        this.bloodBagType = bloodBagType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
