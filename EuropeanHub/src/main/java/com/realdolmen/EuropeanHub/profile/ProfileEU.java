package com.realdolmen.EuropeanHub.profile;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class ProfileEU {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private License license;
    @OneToMany
    private List<Vehicle> vehicles;

    private String firstName;
    private String lastName;
    private String email;

}
