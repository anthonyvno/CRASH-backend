package com.realdolmen.EuropeanHub.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class License {

    @Id
    @GeneratedValue
    private int id;

    private String category;
    private String licenseNumber;
    private String expires;

}
