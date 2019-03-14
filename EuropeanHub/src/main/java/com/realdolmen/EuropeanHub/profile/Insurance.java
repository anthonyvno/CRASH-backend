package com.realdolmen.EuropeanHub.profile;

import com.realdolmen.EuropeanHub.insurer.Insurer;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Insurance {

    @Id
    @GeneratedValue
    private int id;
    
    @ManyToOne
    private Insurer insurer;    
    private String insuranceNumber;    
    private String greenCardNumber;   
        private String emailAgency;
    private Date expires;
    private String phoneAgency;
    
    

}
