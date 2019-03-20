/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.insurer;

import org.springframework.stereotype.Component;

@Component
public class InsurerMapper {
    
    public InsurerDTO mapInsurerToDTO(Insurer insurer){
        return new InsurerDTO(insurer.getId(),insurer.getCountry(),insurer.getName());
    }
    
}
