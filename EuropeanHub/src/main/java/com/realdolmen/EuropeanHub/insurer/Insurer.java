
package com.realdolmen.EuropeanHub.insurer;

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
public class Insurer {

    @Id
    @GeneratedValue
    private int id;

    private String country;

    private String name;

    public Insurer(String name, String country) {
        this.country = country;
        this.name = name;
    }

}
