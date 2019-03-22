/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.realdolmen.EuropeanHub.profile;

import com.realdolmen.EuropeanHub.insurer.Insurer;
import com.realdolmen.EuropeanHub.insurer.InsurerDTO;
import org.springframework.stereotype.Component;

@Component
public class InsuranceMapper {

        public InsuranceDTO mapInsuranceToDTO(Insurance insurance){
        return new InsuranceDTO(insurance.getId(),insurance.getInsurer(),insurance.getInsuranceNumber(),insurance.getGreenCardNumber(),
                insurance.getEmailAgency(),insurance.getExpires(), insurance.getPhoneAgency());
    }
}
