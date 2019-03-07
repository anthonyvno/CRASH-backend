
package com.realdolmen.EuropeanHub.insurer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface InsurerRepository extends JpaRepository<Insurer, Integer> {

    List<Insurer> findByCountry(@Param("country") String country);

}
