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

    private final Date dateReportReceived = new Date();

}
