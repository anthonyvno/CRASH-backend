/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.EuropeanHub.report;

import com.realdolmen.EuropeanHub.profile.ProfileEU;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Report {
    
    @Id
    @GeneratedValue
    private int id;
    
    @OneToMany
    private List<ProfileEU> profiles;
    
    private final LocalDate dateReportReceived = LocalDate.of(2018, 2, 28);
    
    
    
}
