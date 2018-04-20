package io.cristaling.iss.reddrop.core;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hospitals")
public class Hospital {
    @Id
    UUID uuid;
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    List<Doctor> doctors;
    @Column
    String name;
    @Column
    String city;
    @Column
    String county;

}
