/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author AVOBN94
 */


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
