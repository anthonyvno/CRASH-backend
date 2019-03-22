/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.realdolmen.EuropeanHub.profile;

import com.realdolmen.EuropeanHub.insurer.Insurer;
import java.util.Date;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class InsuranceDTO {

    private int id;
    private Insurer insurer;    
    private String insuranceNumber;    
    private String greenCardNumber;   
    private String emailAgency;
    private Date expires;
    private String phoneAgency;
}
