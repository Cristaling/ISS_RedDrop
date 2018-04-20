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
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    Doctor doctor;
    @Column
    String importance;



}
