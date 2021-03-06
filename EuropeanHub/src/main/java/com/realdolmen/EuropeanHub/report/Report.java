package com.realdolmen.EuropeanHub.report;

import com.realdolmen.EuropeanHub.profile.ProfileEU;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
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

    private Date dateCrash;
    private String street;
    private String streetNumber;
    private String postalCode;
    private String city;
    private String country;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String pdfReport;
    private boolean[][] circumstances;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String sketch;
    @Lob
    @Transient
    @Basic(fetch = FetchType.LAZY)
    private String[] signatures;
    @Lob
    @Transient
    @Basic(fetch = FetchType.LAZY)
    private String[] pictures;
    @Lob
    @Transient
    @Basic(fetch = FetchType.LAZY)
    private String[] damageIndications;
    @Lob
    @Transient
    @Basic(fetch = FetchType.LAZY)
    private String[] remarks;

}
