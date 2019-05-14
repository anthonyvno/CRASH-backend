package com.realdolmen.EuropeanHub.report;
import org.springframework.data.repository.query.Param;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query("SELECT DISTINCT r FROM Report r "
            + "JOIN r.profiles p "
            + "JOIN p.vehicles v "
            + "JOIN v.insurance i "
            + "JOIN i.insurer i2 "
            + "WHERE LOWER(i2.name) = LOWER(:insurerName)")
    public List<Report> findByInsurerName(@Param("insurerName")String insurerName);
}
