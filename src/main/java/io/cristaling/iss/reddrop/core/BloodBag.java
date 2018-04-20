package io.cristaling.iss.reddrop.core;

import io.cristaling.iss.reddrop.utils.BloodBagType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "bags")
public class BloodBag {
    @Id
    UUID uuid;
    @Column
    Donation donation;
    @Column
    boolean tested;
    @Column
    Date expireDate;
    @Column
    BloodBagType type;
}
