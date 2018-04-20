package io.cristaling.iss.reddrop.core;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    UUID uuid;
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    Hospital hospital;
    @Column
    String cnp;
    @Column
    String fullName;
    @Column
    String password;


}
