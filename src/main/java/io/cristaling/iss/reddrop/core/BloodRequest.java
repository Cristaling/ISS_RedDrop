package io.cristaling.iss.reddrop.core;

import javax.persistence.*;
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
    String importance;

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

    public String getImportance() {
        return importance;
    }

    public void setImportance(String importance) {
        this.importance = importance;
    }
}
