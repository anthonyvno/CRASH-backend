/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.insurer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author SBZBN83
 */
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
    
}
